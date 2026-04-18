package net.empire.ewmedieval.block.custom.cookingpot;

import net.empire.ewmedieval.block.entity.ModBlockEntities;
import net.empire.ewmedieval.block.entity.custom.CookingPotBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.empire.ewmedieval.particle.ModParticles;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class CookingPotBlock extends BlockWithEntity implements BlockEntityProvider {

    public enum Support implements StringIdentifiable {
        NONE("none"), TRAY("tray"), HANDLE("handle");

        private final String name;
        Support(String name) { this.name = name; }

        @Override
        public String asString() { return name; }
    }

    public static final DirectionProperty      FACING  = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<Support>  SUPPORT = EnumProperty.of("support", Support.class);

    private static final VoxelShape SHAPE =
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);

    public CookingPotBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(SUPPORT, Support.NONE));
    }

    // -------------------------------------------------------------------------
    // Block state
    // -------------------------------------------------------------------------

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SUPPORT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(SUPPORT, getSupportForPos(ctx.getWorld(), ctx.getBlockPos()));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction,
                                                BlockState neighborState, WorldAccess world,
                                                BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN) {
            return state.with(SUPPORT, getSupportForPos(world, pos));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private static Support getSupportForPos(WorldAccess world, BlockPos pos) {
        Block below = world.getBlockState(pos.down()).getBlock();
        if (below == Blocks.CAMPFIRE || below == Blocks.SOUL_CAMPFIRE
                || below == Blocks.FIRE   || below == Blocks.SOUL_FIRE) {
            return Support.TRAY;
        }
        return Support.NONE;
    }

    // -------------------------------------------------------------------------
    // Shape
    // -------------------------------------------------------------------------

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world,
                                      BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    // -------------------------------------------------------------------------
    // Rendering
    // -------------------------------------------------------------------------

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // -------------------------------------------------------------------------
    // Block entity
    // -------------------------------------------------------------------------

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CookingPotBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.COOKING_POT,
                (w, p, s, be) -> CookingPotBlockEntity.tick(w, p, s, be));
    }

    // -------------------------------------------------------------------------
    // Interaction
    // -------------------------------------------------------------------------

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                               PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory factory =
                    (NamedScreenHandlerFactory) world.getBlockEntity(pos);
            if (factory != null) {
                player.openHandledScreen(factory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos,
                                 BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof CookingPotBlockEntity) {
                ItemScatterer.spawn(world, pos, (CookingPotBlockEntity) be);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // -------------------------------------------------------------------------
    // Particles
    // -------------------------------------------------------------------------

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!hasHeatBelow(world, pos)) return;

        double cx = pos.getX() + 0.5;
        double top = pos.getY() + 0.65;
        double cz = pos.getZ() + 0.5;

        // Steam rising from the pot
        for (int i = 0; i < 2; i++) {
            double ox = (random.nextDouble() - 0.5) * 0.4;
            double oz = (random.nextDouble() - 0.5) * 0.4;
            world.addParticle(ModParticles.STEAM, cx + ox, top, cz + oz,
                    0, 0.05 + random.nextDouble() * 0.02, 0);
        }

        // Occasional bubble pop on the surface
        if (random.nextInt(4) == 0) {
            double ox = (random.nextDouble() - 0.5) * 0.4;
            double oz = (random.nextDouble() - 0.5) * 0.4;
            world.addParticle(ParticleTypes.BUBBLE_POP, cx + ox, top, cz + oz,
                    0, 0.04, 0);
        }
    }

    // -------------------------------------------------------------------------
    // Heat detection
    // -------------------------------------------------------------------------

    /**
     * Returns true when there is a valid heat source directly below the given position.
     * Accepted sources: fire, soul fire, magma block, or any block whose LIT property is true
     * (campfire, soul campfire, stove, …).
     */
    public static boolean hasHeatBelow(World world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.down());
        Block b = below.getBlock();
        if (b == Blocks.FIRE || b == Blocks.SOUL_FIRE || b == Blocks.MAGMA_BLOCK) {
            return true;
        }
        if (below.contains(Properties.LIT)) {
            return below.get(Properties.LIT);
        }
        return false;
    }
}
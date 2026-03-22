package net.empire.ewmedieval.block.custom.feastblocks;

import net.empire.ewmedieval.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class PieBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty BITES = IntProperty.of("bites", 0, 3);

    protected static final VoxelShape SHAPE =
            Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);

    public final Supplier<Item> pieSlice;

    public PieBlock(Settings settings, Supplier<Item> pieSlice) {
        super(settings);
        this.pieSlice = pieSlice;
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(BITES, 0));
    }

    public ItemStack getPieSliceItem() {
        return new ItemStack(this.pieSlice.get());
    }

    public int getMaxBites() {
        return 4;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getStackInHand(hand);

        if (world.isClient) {
            if (heldStack.isIn(ModTags.KNIVES)) {
                return cutSlice(world, pos, state, player);
            }
            if (this.consumeBite(world, pos, state, player) == ActionResult.SUCCESS) {
                return ActionResult.SUCCESS;
            }
            if (heldStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        if (heldStack.isIn(ModTags.KNIVES)) {
            return cutSlice(world, pos, state, player);
        }
        return this.consumeBite(world, pos, state, player);
    }

    protected ActionResult consumeBite(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        }

        ItemStack sliceStack = this.getPieSliceItem();
        FoodComponent sliceFood = sliceStack.getItem().getFoodComponent();

        player.getHungerManager().eat(sliceStack.getItem(), sliceStack);

        if (sliceStack.getItem().isFood() && sliceFood != null) {
            for (var pair : sliceFood.getStatusEffects()) {
                if (!world.isClient && pair.getFirst() != null
                        && world.random.nextFloat() < pair.getSecond()) {
                    player.addStatusEffect(new StatusEffectInstance(pair.getFirst()));
                }
            }
        }

        int bites = state.get(BITES);
        if (bites < getMaxBites() - 1) {
            world.setBlockState(pos, state.with(BITES, bites + 1), 3);
        } else {
            world.removeBlock(pos, false);
        }

        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT,
                SoundCategory.PLAYERS, 0.8F, 0.8F);
        return ActionResult.SUCCESS;
    }

    protected ActionResult cutSlice(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int bites = state.get(BITES);
        if (bites < getMaxBites() - 1) {
            world.setBlockState(pos, state.with(BITES, bites + 1), 3);
        } else {
            world.removeBlock(pos, false);
        }

        Direction direction = player.getHorizontalFacing().getOpposite();
        ItemStack slice = this.getPieSliceItem();
        var itemEntity = new net.minecraft.entity.ItemEntity(world,
                pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, slice);
        itemEntity.setVelocity(
                direction.getOffsetX() * 0.15,
                0.05,
                direction.getOffsetZ() * 0.15);
        world.spawnEntity(itemEntity);

        world.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK,
                SoundCategory.PLAYERS, 0.8F, 0.8F);
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction,
                                                BlockState neighborState, WorldAccess world,
                                                BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, BITES);
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getMaxBites() - state.get(BITES);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
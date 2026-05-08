package net.empire.ewmedieval.block.custom.cropblocks;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.item.ModItems;
import net.empire.ewmedieval.season.SeasonCropRegistry;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")
public class CornBlock extends PlantBlock implements Fertilizable {

    public static final IntProperty AGE = IntProperty.of("age", 0, 3);
    public static final BooleanProperty SUPPORTING = BooleanProperty.of("supporting");

    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(3, 0, 3, 13, 8, 13),
            Block.createCuboidShape(3, 0, 3, 13, 10, 13),
            Block.createCuboidShape(2, 0, 2, 14, 12, 14),
            Block.createCuboidShape(1, 0, 1, 15, 16, 15)
    };

    public CornBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0)
                .with(SUPPORTING, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, SUPPORTING);
    }


    public int getMaxAge() {
        return 3;
    }

    public int getAge(BlockState state) {
        return state.get(AGE);
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(AGE, age);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.CORN_KERNELS);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(AGE)];
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.down());
        return below.isOf(Blocks.FARMLAND);
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (direction == Direction.UP) {
            boolean isSupporting = neighborState.isOf(ModBlocks.CORN_UPPER_CROP);
            return state.with(SUPPORTING, isSupporting);
        }
        return state;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float seasonMod = net.empire.ewmedieval.season.SeasonCropRegistry.getModifier(this, world, pos);
        if (seasonMod <= 0f || (seasonMod < 1f && random.nextFloat() >= seasonMod)) return;
        if (world.getBaseLightLevel(pos, 0) < 6) return;

        int age = this.getAge(state);

        if (age < this.getMaxAge()) {

            if (random.nextFloat() < 0.25f) {
                world.setBlockState(pos, state.with(AGE, age + 1), Block.NOTIFY_LISTENERS);
            }
            return;
        }


        if (world.isAir(pos.up())) {
            BlockState upper = ModBlocks.CORN_UPPER_CROP.getDefaultState();
            if (upper.canPlaceAt(world, pos.up())) {
                world.setBlockState(pos.up(), upper, Block.NOTIFY_LISTENERS);
                world.setBlockState(pos, state.with(SUPPORTING, true), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        if (state.get(AGE) < this.getMaxAge()) return true;

        BlockState above = world.getBlockState(pos.up());
        if (above.getBlock() instanceof CornUpperBlock upper) {
            return above.get(CornUpperBlock.AGE) < upper.getMaxAge();
        }

        return true;
    }


    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        if (!SeasonCropRegistry.CREATIVE_BONEMEAL.get() &&
                world instanceof ServerWorld sw && SeasonCropRegistry.getModifier(this, sw, pos) <= 0f) return false;
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = this.getAge(state);

        if (age < this.getMaxAge()) {
            int newAge = Math.min(this.getMaxAge(), age + random.nextBetween(1, 2));
            world.setBlockState(pos, state.with(AGE, newAge), Block.NOTIFY_LISTENERS);
            return;
        }

        BlockState upper = world.getBlockState(pos.up());
        if (upper.getBlock() instanceof CornUpperBlock) {
            CornUpperBlock upperBlock = (CornUpperBlock) upper.getBlock();
            int upperAge = upper.get(CornUpperBlock.AGE);

            if (upperAge < upperBlock.getMaxAge()) {
                int newAge = Math.min(upperBlock.getMaxAge(), upperAge + 1);
                world.setBlockState(pos.up(), upper.with(CornUpperBlock.AGE, newAge), Block.NOTIFY_LISTENERS);
            }

        } else if (world.isAir(pos.up())) {
            world.setBlockState(pos.up(), ModBlocks.CORN_UPPER_CROP.getDefaultState(), Block.NOTIFY_LISTENERS);
        }
    }
}
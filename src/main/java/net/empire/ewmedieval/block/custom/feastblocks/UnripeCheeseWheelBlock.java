package net.empire.ewmedieval.block.custom.feastblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class UnripeCheeseWheelBlock extends Block {
    public static final IntProperty AGE = IntProperty.of("age", 0, 1);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D,
            14.0D, 6.0D, 14.0D);
    public final Supplier<Block> ripeCheeseBlock;

    public UnripeCheeseWheelBlock(Settings settings, Supplier<Block> ripeCheeseBlock) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
        this.ripeCheeseBlock = ripeCheeseBlock;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !this.canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos,
                           net.minecraft.util.math.random.Random random) {
        if (random.nextFloat() > 0.1F) return;

        int age = state.get(AGE);
        world.setBlockState(
                pos,
                age == 0
                        ? state.with(AGE, 1)
                        : ripeCheeseBlock.get().getDefaultState(),
                3);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}
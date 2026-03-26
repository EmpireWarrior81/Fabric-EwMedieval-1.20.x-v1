package net.empire.ewmedieval.block.custom.feastblocks;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")
public class PizzaBlock extends Block {

    public static final IntProperty SERVINGS = IntProperty.of("servings", 0, 3);

    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(0, 0, 0, 8, 2, 8),
            Block.createCuboidShape(0, 0, 0, 16, 2, 8),
            VoxelShapes.union(
                    Block.createCuboidShape(0, 0, 0, 16, 2, 8),
                    Block.createCuboidShape(0, 0, 8, 8, 2, 16)
            ),
            Block.createCuboidShape(0, 0, 0, 16, 2, 16)};


    public PizzaBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SERVINGS, 3));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(SERVINGS)];
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        return takeServing(world, pos, state, player, hand);
    }

    private ActionResult takeServing(World world, BlockPos pos,
                                     BlockState state, PlayerEntity player, Hand hand) {
        int servings = state.get(SERVINGS);
        ItemStack held = player.getStackInHand(hand);
        if (held.isEmpty() || held.isOf(ModItems.PIZZA_SLICE)) {
            if (held.isOf(ModItems.PIZZA_SLICE) && held.getCount() < held.getMaxCount()) {
                held.increment(1);
            } else {
                player.setStackInHand(hand, new ItemStack(ModItems.PIZZA_SLICE));
            }
        } else {
            dropStack(world, pos, new ItemStack(ModItems.PIZZA_SLICE));
        }
        world.playSound(
                null,
                pos,
                SoundEvents.BLOCK_SLIME_BLOCK_PLACE,
                SoundCategory.BLOCKS,
                1.0F,
                1.0F
        );
        if (servings <= 0) {
            world.breakBlock(pos, false);
        } else {
            world.setBlockState(pos, state.with(SERVINGS, servings - 1), Block.NOTIFY_ALL);
        }

        return ActionResult.SUCCESS;
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
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(SERVINGS);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SERVINGS);
    }
}

package net.empire.ewmedieval.block.custom.feastblocks;

import net.empire.ewmedieval.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
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

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class CheeseWheelBlock extends Block {
    public static final IntProperty SERVINGS = IntProperty.of("servings", 0, 3);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(2.0D, 0.0D, 2.0D, 8.0D, 6.0D, 8.0D),
            Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 8.0D),
            VoxelShapes.union(Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 8.0D),
                    Block.createCuboidShape(2.0D, 0.0D, 8.0D, 8.0D, 6.0D, 14.0D)),
            Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D)};


    public final Supplier<Item> cheeseWedgeType;

    public CheeseWheelBlock(Settings settings, Supplier<Item> cheeseWedgeType) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SERVINGS, 3));
        this.cheeseWedgeType = cheeseWedgeType;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(SERVINGS)];
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        int servings = state.get(SERVINGS);

        if (stack.isIn(ModTags.KNIVES)) {
            if (!world.isClient) {
                world.playSound(
                        null,
                        pos,
                        SoundEvents.BLOCK_WOOL_BREAK,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F
                );

                dropStack(world, pos, new ItemStack(cheeseWedgeType.get()));

                if (servings > 0) {
                    world.setBlockState(pos, state.with(SERVINGS, servings - 1), Block.NOTIFY_ALL);
                }
                if (servings == 0) {
                    world.breakBlock(pos, false);
                }
            }
            return ActionResult.SUCCESS;
        }
        if (world.isClient) {
            player.sendMessage(
                    Text.translatable("block.ewmedieval.cheese.feast.use_knife"),
                    true
            );
        }

        return ActionResult.PASS;
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
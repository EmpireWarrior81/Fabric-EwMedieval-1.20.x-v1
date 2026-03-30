package net.empire.ewmedieval.block.custom.feastblocks;

import net.empire.ewmedieval.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.effect.StatusEffectInstance;
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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class PancakeBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final int MAX_SERVINGS = 6;
    public static final IntProperty SERVINGS = IntProperty.of("servings", 0, MAX_SERVINGS - 1);

    public final Supplier<Item> servingItem;

    protected static final VoxelShape PLATE_SHAPE =
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);

    protected static final VoxelShape[] PANCAKES_SHAPES = new VoxelShape[]{
            VoxelShapes.union(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 8.0D, 13.0D)),
            VoxelShapes.union(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 7.0D, 13.0D)),
            VoxelShapes.union(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 6.0D, 13.0D)),
            VoxelShapes.union(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 5.0D, 13.0D)),
            VoxelShapes.union(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 4.0D, 13.0D)),
            VoxelShapes.union(PLATE_SHAPE, Block.createCuboidShape(3.0D, 2.0D, 3.0D, 13.0D, 3.0D, 13.0D))
    };

    public PancakeBlock(Supplier<Item> servingItem, Settings settings) {
        super(settings);
        this.servingItem = servingItem;
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(SERVINGS, 0));
    }

    public ItemStack getServingItem() {
        return new ItemStack(this.servingItem.get());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);


        if (held.isIn(ModTags.KNIVES)) {
            if (!world.isClient) {
                takeServing(world, pos, state, player);
            }
            return ActionResult.SUCCESS;
        }

        if (world.isClient) {
            if (consumeServing(world, pos, state, player) == ActionResult.SUCCESS) {
                return ActionResult.SUCCESS;
            }
            if (held.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return consumeServing(world, pos, state, player);
    }


    protected ActionResult takeServing(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Direction direction = player.getHorizontalFacing().getOpposite();
        ItemStack slice = getServingItem();

        var itemEntity = new net.minecraft.entity.ItemEntity(world,
                pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, slice);
        itemEntity.setVelocity(direction.getOffsetX() * 0.15, 0.05, direction.getOffsetZ() * 0.15);
        world.spawnEntity(itemEntity);

        removeServing(world, pos, state);

        world.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK,
                SoundCategory.PLAYERS, 0.8F, 0.8F);

        return ActionResult.SUCCESS;
    }


    protected ActionResult consumeServing(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        }

        ItemStack servingStack = getServingItem();
        FoodComponent food = servingStack.getItem().getFoodComponent();

        if (food != null) {
            player.getHungerManager().eat(servingStack.getItem(), servingStack);
            if (!world.isClient) {
                for (var pair : food.getStatusEffects()) {
                    if (pair.getFirst() != null && world.random.nextFloat() < pair.getSecond()) {
                        player.addStatusEffect(new StatusEffectInstance(pair.getFirst()));
                    }
                }
            }
        }

        removeServing(world, pos, state);
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT,
                SoundCategory.PLAYERS, 0.8F, 0.8F);

        return ActionResult.SUCCESS;
    }

    private void removeServing(World world, BlockPos pos, BlockState state) {
        int servingsTaken = state.get(SERVINGS);
        if (servingsTaken < MAX_SERVINGS - 1) {
            world.setBlockState(pos, state.with(SERVINGS, servingsTaken + 1), MAX_SERVINGS - 1);
        } else {
            world.breakBlock(pos, true);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return PANCAKES_SHAPES[state.get(SERVINGS)];
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing());
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
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(SERVINGS);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }
}
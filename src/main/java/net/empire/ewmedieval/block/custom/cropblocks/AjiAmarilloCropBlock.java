package net.empire.ewmedieval.block.custom.cropblocks;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")
public class AjiAmarilloCropBlock extends PlantBlock implements Fertilizable {

    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = Properties.AGE_7;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 7.0, 12.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 10.0, 13.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 13.0, 13.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 15.0, 14.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 15.0, 14.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 15.0, 14.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 15.0, 14.0),
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 15.0, 14.0)
    };

    public AjiAmarilloCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.FARMLAND);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = state.get(AGE);
        if (age < MAX_AGE && world.getBaseLightLevel(pos.up(), 0) >= 9 && random.nextInt(5) == 0) {
            world.setBlockState(pos, state.with(AGE, age + 1), 2);
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = state.get(AGE);
        int newAge = Math.min(MAX_AGE, age + 2 + random.nextInt(3));
        world.setBlockState(pos, state.with(AGE, newAge), 2);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_BY_AGE[state.get(AGE)];
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        int age = state.get(AGE);

        if (age == MAX_AGE) {
            if (!world.isClient) {
                int count = 2 + world.random.nextInt(2);
                Block.dropStack(world, pos, new ItemStack(ModItems.AJI_AMARILLO, count));
                world.playSound(null, pos,
                        SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                        SoundCategory.BLOCKS, 1.0F,
                        0.8F + world.random.nextFloat() * 0.4F);
                world.setBlockState(pos, state.with(AGE, 3), 2);
            }
            return ActionResult.success(world.isClient);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
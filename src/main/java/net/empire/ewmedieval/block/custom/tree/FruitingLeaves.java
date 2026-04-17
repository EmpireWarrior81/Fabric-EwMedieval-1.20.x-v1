package net.empire.ewmedieval.block.custom.tree;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.item.ItemStack;
import net.minecraft.world.event.GameEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;

@SuppressWarnings("deprecation")
public class FruitingLeaves extends LeavesBlock implements Fertilizable {
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);

    public FruitingLeaves(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0)
                .with(DISTANCE, 7)
                .with(PERSISTENT, false));
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE || super.hasRandomTicks(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(DISTANCE) == 7 && !state.get(PERSISTENT)) {
            this.dropStacks(state, world, pos);
            world.removeBlock(pos, false);
            return;
        }
        int age = state.get(AGE);

        if (age < MAX_AGE && random.nextFloat() < 0.25f) {
            world.setBlockState(pos, state.with(AGE, age + 1), 2);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int age = state.get(AGE);
        boolean ripe = age == MAX_AGE;
        if (ripe) {
            if (!world.isClient) {
                int count = 2 + world.random.nextInt(2); // 2 or 3 avocados
                ItemStack drop = new ItemStack(ModItems.AVOCADO, count);
                ItemScatterer.spawn((ServerWorld) world,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        drop
                );

                world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);

                BlockState next = state.with(AGE, 0);
                world.setBlockState(pos, next, 2);

                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, next));
            }
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int newAge = Math.min(MAX_AGE, state.get(AGE) + 1);
        world.setBlockState(pos, state.with(AGE, newAge), 2);
    }

    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }
}

package net.empire.ewmedieval.block.custom;

import net.empire.ewmedieval.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;


public class DandelionBlock extends FlowerBlock implements Fertilizable {

    public DandelionBlock(Settings settings) {
        super(StatusEffects.LUCK, 5, settings);
    }


    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }


    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }


    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        dropStack(world, pos, new ItemStack(ModBlocks.MATURE_DANDELION.asItem()));
    }
}
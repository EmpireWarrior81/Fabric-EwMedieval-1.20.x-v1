package net.empire.ewmedieval.block.custom.tree;

import net.empire.ewmedieval.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

/**
 * Avocado pit — place it on dirt, and it will eventually sprout into an avocado sapling.
 *
 * Two ways to progress:
 *   - Random tick:  ~25 % chance per tick to convert to the sapling automatically.
 *   - Bonemeal:     instantly converts to the sapling.
 *
 * The sapling then grows into the tree via the standard SaplingBlock mechanic.
 */

@SuppressWarnings("deprecation")
public class AvocadoPitBlock extends PlantBlock implements Fertilizable {

    private static final VoxelShape SHAPE =
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 3.0, 10.0);

    public AvocadoPitBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT);
    }


    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float seasonMod = net.empire.ewmedieval.season.SeasonCropRegistry.getModifier(this, world, pos);
        if (seasonMod <= 0f || (seasonMod < 1f && random.nextFloat() >= seasonMod)) return;
        if (random.nextFloat() < 0.25f) {
            world.setBlockState(pos, ModBlocks.AVOCADO_SAPLING.getDefaultState(), Block.NOTIFY_ALL);
        }
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
        world.setBlockState(pos, ModBlocks.AVOCADO_SAPLING.getDefaultState(), Block.NOTIFY_ALL);
    }
}
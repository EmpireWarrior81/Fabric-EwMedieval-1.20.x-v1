package net.empire.ewmedieval.block.custom.cropblocks;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class EggplantsBlock extends CropBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D, 1.0D, 0.130D, 1.0D),
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D, 1.0D, 0.120D, 1.0D),
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D),
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D),
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D),
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D),
            VoxelShapes.cuboid(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D)
    };

    public EggplantsBlock(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.EGGPLANT_SEEDS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_BY_AGE[state.get(getAgeProperty())];
    }
}
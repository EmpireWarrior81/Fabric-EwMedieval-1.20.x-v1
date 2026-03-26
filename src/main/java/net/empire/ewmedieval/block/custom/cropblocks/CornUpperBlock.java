package net.empire.ewmedieval.block.custom.cropblocks;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class CornUpperBlock extends CropBlock {

    public static final IntProperty AGE = IntProperty.of("age", 0, 3);

    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(3, 0, 3, 13, 8, 13),
            Block.createCuboidShape(3, 0, 3, 13, 10, 13),
            Block.createCuboidShape(2, 0, 2, 14, 12, 14),
            Block.createCuboidShape(1, 0, 1, 15, 16, 15)};

    public CornUpperBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(this.getAgeProperty(), 0));
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CORN_KERNELS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(ModBlocks.CORN_UPPER_CROP);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.down());
        return below.isOf(ModBlocks.CORN_CROP)
                && (world.getBaseLightLevel(pos, 0) >= 8 || world.isSkyVisible(pos));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(AGE)];
    }
}
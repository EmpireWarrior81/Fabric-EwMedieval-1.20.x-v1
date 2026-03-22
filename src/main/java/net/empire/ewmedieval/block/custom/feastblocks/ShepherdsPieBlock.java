package net.empire.ewmedieval.block.custom.feastblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.function.Supplier;

public class ShepherdsPieBlock extends FeastBlock {

    protected static final VoxelShape PLATE_SHAPE =
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    protected static final VoxelShape PIE_SHAPE = VoxelShapes.union(
            PLATE_SHAPE,
            Block.createCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 8.0D, 14.0D));

    public ShepherdsPieBlock(Settings settings, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(settings, servingItem, hasLeftovers);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos,
                                      ShapeContext context) {
        return state.get(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
    }
}
package net.empire.ewmedieval.block.custom.feastblocks;

import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class RiceRollRoyaleMedleyBlock extends FeastBlock {

    public static final IntProperty ROLL_SERVINGS = IntProperty.of("servings", 0, 8);
    public static final int MAX_SERVINGS = 8;

    protected static final VoxelShape PLATE_SHAPE =
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    protected static final VoxelShape FOOD_SHAPE = VoxelShapes.union(
            PLATE_SHAPE,
            Block.createCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 4.0D, 14.0D));

    public final List<Supplier<Item>> riceRollServings = Arrays.asList(
            () -> ModItems.CHERRY_BLOSSOM_ROLL,
            () -> ModItems.SLICED_CALAMARI_ROLL,
            () -> ModItems.BELL_PEPPER_ROLL_YELLOW,
            () -> ModItems.BELL_PEPPER_ROLL_RED,
            () -> ModItems.BELL_PEPPER_ROLL_GREEN,
            () -> ModItems.KELP_ROLL_SLICE,
            () -> ModItems.KELP_ROLL_SLICE,
            () -> ModItems.KELP_ROLL_SLICE
    );

    public RiceRollRoyaleMedleyBlock(Settings settings) {
        super(settings, () -> ModItems.CHERRY_BLOSSOM_ROLL, true);
    }

    @Override
    public IntProperty getServingsProperty() {
        return ROLL_SERVINGS;
    }

    @Override
    public int getMaxServings() {
        return MAX_SERVINGS;
    }

    @Override
    public ItemStack getServingItem(BlockState state) {
        int servings = state.get(getServingsProperty());
        if (servings <= 0) return ItemStack.EMPTY;
        return new ItemStack(riceRollServings.get(servings - 1).get());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos,
                                      ShapeContext context) {
        return state.get(getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROLL_SERVINGS);
    }
}
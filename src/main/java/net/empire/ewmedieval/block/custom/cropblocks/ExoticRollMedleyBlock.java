package net.empire.ewmedieval.block.custom.cropblocks;

import net.empire.ewmedieval.block.custom.feastblocks.FeastBlock;
import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ExoticRollMedleyBlock extends FeastBlock {
    public static final IntProperty ROLL_SERVINGS = IntProperty.of("servings", 0, 8);

    protected static final VoxelShape PLATE_SHAPE =
            VoxelShapes.cuboid(1f/16f, 0f, 1f/16f, 15f/16f, 2f/16f, 15f/16f);

    protected static final VoxelShape FOOD_SHAPE =
            VoxelShapes.union(PLATE_SHAPE,
                    VoxelShapes.cuboid(2f/16f, 2f/16f, 2f/16f, 14f/16f, 4f/16f, 14f/16f));

    public final List<Supplier<Item>> riceRollServings;

    public ExoticRollMedleyBlock(Settings settings, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(settings, servingItem, hasLeftovers);

        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(ROLL_SERVINGS, 8)
        );

        this.riceRollServings = Arrays.asList(
                () -> ModItems.PUFFERFISH_ROLL,
                () -> ModItems.PUFFERFISH_ROLL,
                () -> ModItems.TROPICAL_ROLL,
                () -> ModItems.TROPICAL_ROLL,
                () -> ModItems.TROPICAL_ROLL,
                () -> ModItems.CHICKEN_ROLL_SLICE,
                () -> ModItems.CHICKEN_ROLL_SLICE,
                () -> ModItems.CHICKEN_ROLL_SLICE
        );
    }

    public IntProperty getServingsProperty() {
        return ROLL_SERVINGS;
    }

    public int getMaxServings() {
        return 8;
    }


    @Override
    public ItemStack getServingItem(BlockState state) {
        int servings = state.get(ROLL_SERVINGS);
        if (servings <= 0) return ItemStack.EMPTY; // FIXED

        return new ItemStack(riceRollServings.get(servings - 1).get());
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(ROLL_SERVINGS) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROLL_SERVINGS);
    }


}
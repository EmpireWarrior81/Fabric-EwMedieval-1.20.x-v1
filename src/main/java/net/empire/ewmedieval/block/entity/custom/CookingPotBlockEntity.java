package net.empire.ewmedieval.block.entity.custom;

import net.empire.ewmedieval.block.custom.cookingpot.CookingPotBlock;
import net.empire.ewmedieval.block.entity.ImplementedInventory;
import net.empire.ewmedieval.block.entity.ModBlockEntities;
import net.empire.ewmedieval.block.entity.SyncedBlockEntity;
import net.empire.ewmedieval.gui.ModScreenHandlers;
import net.empire.ewmedieval.gui.cookingpot.CookingPotScreenHandler;
import net.empire.ewmedieval.recipe.CookingPotRecipe;
import net.empire.ewmedieval.recipe.ModRecipes;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookingPotBlockEntity extends SyncedBlockEntity
        implements ExtendedScreenHandlerFactory, ImplementedInventory {

    // Slot layout:
    //   0–5  ingredient slots (2 rows × 3 columns)
    //   6    container slot (bowl / bucket / etc.)
    //   7    output slot (player takes the result from here)
    public static final int INGREDIENT_SLOT_COUNT = 6;
    public static final int CONTAINER_SLOT        = 6;
    public static final int OUTPUT_SLOT           = 7;
    public static final int INVENTORY_SIZE        = 8;

    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

    private int cookingTime      = 0;
    private int cookingTimeTotal = 0;

    protected final PropertyDelegate propertyDelegate;

    public CookingPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COOKING_POT, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> cookingTime;
                    case 1 -> cookingTimeTotal;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> cookingTime      = value;
                    case 1 -> cookingTimeTotal = value;
                }
            }

            @Override
            public int size() { return 2; }
        };
    }

    // -------------------------------------------------------------------------
    // ImplementedInventory
    // -------------------------------------------------------------------------

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    // -------------------------------------------------------------------------
    // NBT serialisation
    // -------------------------------------------------------------------------

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        cookingTime      = nbt.getInt("CookingTime");
        cookingTimeTotal = nbt.getInt("CookingTimeTotal");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("CookingTime",      cookingTime);
        nbt.putInt("CookingTimeTotal", cookingTimeTotal);
    }

    // -------------------------------------------------------------------------
    // Screen handler factory
    // -------------------------------------------------------------------------

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.ewmedieval.cooking_pot");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CookingPotScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    // -------------------------------------------------------------------------
    // Tick
    // -------------------------------------------------------------------------

    public static void tick(World world, BlockPos pos, BlockState state,
                            CookingPotBlockEntity be) {
        if (world.isClient) return;

        boolean hasHeat = CookingPotBlock.hasHeatBelow(world, pos);

        if (!hasHeat) {
            if (be.cookingTime > 0) {
                be.cookingTime = Math.max(0, be.cookingTime - 2);
                be.inventoryChanged();
            }
            return;
        }

        // Build a view of the current inventory for recipe matching
        SimpleInventory recipeView = be.buildRecipeView();
        Optional<CookingPotRecipe> match = world.getRecipeManager()
                .getFirstMatch(ModRecipes.COOKING_TYPE, recipeView, world);

        if (match.isEmpty()) {
            // No matching recipe – reset progress
            if (be.cookingTime > 0) {
                be.cookingTime = 0;
                be.inventoryChanged();
            }
            return;
        }

        CookingPotRecipe recipe = match.get();
        ItemStack result = recipe.getOutput(world.getRegistryManager());

        // Check whether the result can fit into the output slot
        ItemStack outputStack = be.inventory.get(OUTPUT_SLOT);
        boolean canFit = outputStack.isEmpty()
                || (ItemStack.canCombine(outputStack, result)
                        && outputStack.getCount() + result.getCount() <= outputStack.getMaxCount());

        if (!canFit) {
            // Output slot is full – pause cooking but keep current progress
            return;
        }

        be.cookingTimeTotal = recipe.getCookingTime();
        be.cookingTime++;

        if (be.cookingTime >= be.cookingTimeTotal) {
            // Cooking complete
            be.cookingTime      = 0;
            be.cookingTimeTotal = 0;

            // Consume one of each required ingredient
            be.consumeIngredients(recipe);

            // Consume one container item
            be.inventory.get(CONTAINER_SLOT).decrement(1);

            // Place result in output slot
            if (outputStack.isEmpty()) {
                be.inventory.set(OUTPUT_SLOT, result.copy());
            } else {
                outputStack.increment(result.getCount());
            }

            be.inventoryChanged();
        }
    }

    /**
     * Builds a SimpleInventory of size 7 (slots 0-5 = ingredients, slot 6 = container)
     * for recipe matching.
     */
    private SimpleInventory buildRecipeView() {
        ItemStack[] stacks = new ItemStack[CookingPotRecipe.INGREDIENT_SLOTS + 1];
        for (int i = 0; i < CookingPotRecipe.INGREDIENT_SLOTS; i++) {
            stacks[i] = inventory.get(i);
        }
        stacks[CookingPotRecipe.INGREDIENT_SLOTS] = inventory.get(CONTAINER_SLOT);
        return new SimpleInventory(stacks);
    }

    /** Consumes one matching item per required ingredient from slots 0-5. */
    private void consumeIngredients(CookingPotRecipe recipe) {
        List<net.minecraft.recipe.Ingredient> toConsume =
                new ArrayList<>(recipe.getIngredients());
        for (net.minecraft.recipe.Ingredient ing : toConsume) {
            for (int i = 0; i < INGREDIENT_SLOT_COUNT; i++) {
                if (ing.test(inventory.get(i))) {
                    inventory.get(i).decrement(1);
                    break;
                }
            }
        }
    }

}
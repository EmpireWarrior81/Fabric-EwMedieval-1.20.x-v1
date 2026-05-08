package net.empire.ewmedieval.gui.knapping;

import net.empire.ewmedieval.gui.ModScreenHandlers;
import net.empire.ewmedieval.recipe.KnappingRecipe;
import net.empire.ewmedieval.recipe.ModRecipes;
import net.empire.ewmedieval.util.ModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class KnappingScreenHandler extends ScreenHandler {

    private final SimpleInventory craftingGrid = new SimpleInventory(9);
    private final SimpleInventory resultContainer = new SimpleInventory(1);
    private final World world;
    private final RecipeManager recipeManager;
    private final PlayerEntity player;
    private ItemStack inputRock;
    private boolean knappingFinished = false;
    private boolean resultCollected = false;
    private boolean rockConsumed = false;

    private static final int PLAYER_FIRST_SLOT_INDEX = 0;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = 36;
    private static final int PLAYER_LAST_SLOT_INDEX = PLAYER_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOT_COUNT - 1;
    private static final int GRID_FIRST_SLOT_INDEX = PLAYER_LAST_SLOT_INDEX + 1;
    private static final int GRID_LAST_SLOT_INDEX = GRID_FIRST_SLOT_INDEX + 8;
    private static final int RESULT_SLOT_INDEX = GRID_LAST_SLOT_INDEX + 1;

    // Client-side constructor (called via ScreenHandlerType)
    public KnappingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getRecipeManager());
    }

    // Server-side constructor
    public KnappingScreenHandler(int syncId, PlayerInventory playerInventory, RecipeManager recipeManager) {
        super(ModScreenHandlers.KNAPPING_SCREEN_HANDLER, syncId);
        this.world = playerInventory.player.getWorld();
        this.recipeManager = recipeManager;
        this.player = playerInventory.player;

        ItemStack mainHand = player.getMainHandStack();
        ItemStack offHand = player.getOffHandStack();
        this.inputRock = (mainHand.isIn(ModTags.KNAPPABLE) && offHand.isIn(ModTags.KNAPPABLE))
                ? mainHand.copy() : ItemStack.EMPTY;

        addPlayerHotbar(playerInventory);
        addPlayerInventory(playerInventory);

        // Grid slots placed off-screen (state-tracking only)
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(craftingGrid, i, -1000, -1000) {
                @Override
                public boolean canInsert(ItemStack stack) { return false; }
                @Override
                public boolean canTakeItems(PlayerEntity p) { return false; }
            });
        }

        // Result slot
        this.addSlot(new Slot(resultContainer, 0, 124, 35) {
            @Override
            public boolean canInsert(ItemStack stack) { return false; }

            @Override
            public boolean canTakeItems(PlayerEntity p) {
                return !this.getStack().isEmpty() && !knappingFinished;
            }

            @Override
            public void onTakeItem(PlayerEntity p, ItemStack stack) {
                super.onTakeItem(p, stack);
                knappingFinished = true;
                resultCollected = true;
            }
        });
    }

    public void setChip(int index) {
        if (knappingFinished || resultCollected) return;

        if (!rockConsumed) {
            consumeInputRock();
            rockConsumed = true;
        }

        // Toggle: if already chipped, unchip; if unchipped, chip
        if (!craftingGrid.getStack(index).isEmpty()) {
            craftingGrid.setStack(index, ItemStack.EMPTY);
        } else {
            craftingGrid.setStack(index, new ItemStack(inputRock.getItem()));
        }

        updateResult();
    }

    private void consumeInputRock() {
        if (world.isClient()) return;
        ItemStack mainHand = player.getMainHandStack();
        if (mainHand.getItem() == inputRock.getItem() && !mainHand.isEmpty()) {
            mainHand.decrement(1);
            player.getInventory().markDirty();
        }
    }

    private void updateResult() {
        if (world.isClient() || knappingFinished || resultCollected) return;

        KnappingRecipe match = recipeManager.listAllOfType(ModRecipes.KNAPPING_TYPE)
                .stream()
                .filter(r -> r.getIngredient().test(inputRock))
                .filter(r -> r.matches(craftingGrid, world))
                .findFirst()
                .orElse(null);

        resultContainer.setStack(0, match != null
                ? match.getOutput(world.getRegistryManager()).copy()
                : ItemStack.EMPTY);

        sendContentUpdates();
    }

    public boolean isChipped(int index) {
        return !craftingGrid.getStack(index).isEmpty();
    }

    public boolean isKnappingFinished() {
        return knappingFinished;
    }

    public boolean isResultCollected() {
        return resultCollected;
    }

    public ItemStack getInputRock() {
        return inputRock;
    }

    public SoundEvent getKnappingSound() {
        return SoundEvents.BLOCK_STONE_HIT;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        if (!rockConsumed) {
            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand = player.getOffHandStack();
            return mainHand.isIn(ModTags.KNAPPABLE) && offHand.isIn(ModTags.KNAPPABLE);
        }
        return true;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (!world.isClient()) {
            ItemStack result = resultContainer.getStack(0);
            if (!result.isEmpty() && !resultCollected) {
                if (!player.getInventory().insertStack(result.copy())) {
                    player.dropItem(result.copy(), false);
                }
                resultContainer.setStack(0, ItemStack.EMPTY);
            }
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot == RESULT_SLOT_INDEX) {
                if (!this.insertItem(originalStack, PLAYER_FIRST_SLOT_INDEX, PLAYER_LAST_SLOT_INDEX + 1, false)) {
                    return ItemStack.EMPTY;
                }
                if (originalStack.isEmpty()) {
                    slot.setStack(ItemStack.EMPTY);
                    knappingFinished = true;
                    resultCollected = true;
                } else {
                    slot.markDirty();
                }
                return newStack;
            } else if (invSlot >= PLAYER_FIRST_SLOT_INDEX && invSlot <= PLAYER_LAST_SLOT_INDEX) {
                if (invSlot < PLAYER_FIRST_SLOT_INDEX + 27) {
                    if (!this.insertItem(originalStack, PLAYER_FIRST_SLOT_INDEX + 27, PLAYER_LAST_SLOT_INDEX + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.insertItem(originalStack, PLAYER_FIRST_SLOT_INDEX, PLAYER_FIRST_SLOT_INDEX + 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (invSlot >= GRID_FIRST_SLOT_INDEX && invSlot <= GRID_LAST_SLOT_INDEX) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, originalStack);
        }

        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }
}

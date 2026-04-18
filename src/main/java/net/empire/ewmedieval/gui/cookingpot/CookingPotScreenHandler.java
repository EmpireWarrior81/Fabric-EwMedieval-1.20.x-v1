package net.empire.ewmedieval.gui.cookingpot;

import net.empire.ewmedieval.gui.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class CookingPotScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    private static final int BLOCK_INV_SIZE   = 8;
    private static final int INGREDIENT_START = 0;
    private static final int INGREDIENT_END   = 6; // exclusive
    private static final int CONTAINER_SLOT   = 6;
    private static final int OUTPUT_SLOT      = 7;

    // Client-side constructor (called from ExtendedScreenHandlerType via PacketByteBuf)
    public CookingPotScreenHandler(int syncId, PlayerInventory playerInventory,
                                   PacketByteBuf buf) {
        this(syncId, playerInventory,
                playerInventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    // Server-side constructor
    public CookingPotScreenHandler(int syncId, PlayerInventory playerInventory,
                                   @Nullable BlockEntity blockEntity,
                                   PropertyDelegate delegate) {
        super(ModScreenHandlers.COOKING_POT_SCREEN_HANDLER, syncId);

        Inventory inv = blockEntity instanceof Inventory
                ? (Inventory) blockEntity
                : new SimpleInventory(BLOCK_INV_SIZE);

        checkSize(inv, BLOCK_INV_SIZE);
        this.inventory = inv;
        this.inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;
        addProperties(delegate);

        // Ingredient slots 0–5: 2 rows × 3 columns starting at (30, 17)
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                int slot = row * 3 + col;
                this.addSlot(new Slot(this.inventory, slot,
                        30 + col * 18,   // x: 30, 48, 66
                        17 + row * 18)); // y: 17, 35
            }
        }

        // Container slot (bowl / bucket)
        this.addSlot(new Slot(this.inventory, CONTAINER_SLOT, 92, 55));

        // Output slot — cannot be inserted into manually
        this.addSlot(new Slot(this.inventory, OUTPUT_SLOT, 124, 55) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    // -------------------------------------------------------------------------
    // Progress helpers (read by the screen)
    // -------------------------------------------------------------------------

    public boolean isCooking() {
        return propertyDelegate.get(0) > 0;
    }

    /** Returns a value 0–arrowWidth proportional to current cooking progress. */
    public int getScaledProgress() {
        int time  = propertyDelegate.get(0);
        int total = propertyDelegate.get(1);
        int arrowWidth = 24;
        return total > 0 && time > 0 ? time * arrowWidth / total : 0;
    }

    // -------------------------------------------------------------------------
    // Shift-click
    // -------------------------------------------------------------------------

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot == null || !slot.hasStack()) return result;

        ItemStack original = slot.getStack();
        result = original.copy();

        int playerInvStart = BLOCK_INV_SIZE;
        int playerInvEnd   = playerInvStart + 27;
        int hotbarStart    = playerInvEnd;
        int hotbarEnd      = hotbarStart + 9;

        if (invSlot < BLOCK_INV_SIZE) {
            // From block → player
            if (!this.insertItem(original, playerInvStart, hotbarEnd, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // From player → block
            if (invSlot == OUTPUT_SLOT) {
                // Never quick-move into the output slot
                return ItemStack.EMPTY;
            }
            // Try container slot first, then ingredient slots
            if (!this.insertItem(original, CONTAINER_SLOT, CONTAINER_SLOT + 1, false)) {
                if (!this.insertItem(original, INGREDIENT_START, INGREDIENT_END, false)) {
                    // Fall back: move within player inventory (hotbar ↔ main)
                    if (invSlot >= playerInvStart && invSlot < playerInvEnd) {
                        if (!this.insertItem(original, hotbarStart, hotbarEnd, false))
                            return ItemStack.EMPTY;
                    } else if (invSlot >= hotbarStart && invSlot < hotbarEnd) {
                        if (!this.insertItem(original, playerInvStart, playerInvEnd, false))
                            return ItemStack.EMPTY;
                    } else {
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        if (original.isEmpty()) {
            slot.setStack(ItemStack.EMPTY);
        } else {
            slot.markDirty();
        }

        if (original.getCount() == result.getCount()) return ItemStack.EMPTY;

        slot.onTakeItem(player, original);
        return result;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // -------------------------------------------------------------------------
    // Player inventory helpers
    // -------------------------------------------------------------------------

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }
}
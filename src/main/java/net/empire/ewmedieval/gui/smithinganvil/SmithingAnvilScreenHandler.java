package net.empire.ewmedieval.gui.smithinganvil;

import net.empire.ewmedieval.block.entity.custom.SmithingAnvilBlockEntity;
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

public class SmithingAnvilScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final @Nullable SmithingAnvilBlockEntity blockEntity;

    private static final int BLOCK_SLOTS   = SmithingAnvilBlockEntity.INVENTORY_SIZE;
    private static final int INPUT_START   = SmithingAnvilBlockEntity.INPUT_START;
    private static final int INPUT_END     = SmithingAnvilBlockEntity.INPUT_END;
    private static final int OUTPUT_SLOT   = SmithingAnvilBlockEntity.OUTPUT_SLOT;
    private static final int BLUEPRINT_SLOT = SmithingAnvilBlockEntity.BLUEPRINT_SLOT;

    // Client constructor (from PacketByteBuf)
    public SmithingAnvilScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory,
                playerInventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(5));
    }

    // Server constructor (block entity passed directly)
    public SmithingAnvilScreenHandler(int syncId, PlayerInventory playerInventory,
                                      @Nullable BlockEntity blockEntity, PropertyDelegate delegate) {
        super(ModScreenHandlers.SMITHING_ANVIL_SCREEN_HANDLER, syncId);

        Inventory inv = blockEntity instanceof Inventory i ? i : new SimpleInventory(BLOCK_SLOTS);
        checkSize(inv, BLOCK_SLOTS);

        this.inventory = inv;
        this.inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;
        addProperties(this.propertyDelegate);
        this.blockEntity = blockEntity instanceof SmithingAnvilBlockEntity sa ? sa : null;

        // 3×3 input grid — positions match Overgeared texture (handler slots 0-8)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                addSlot(new Slot(inventory, row * 3 + col, 30 + col * 18, 17 + row * 18));
            }
        }

        // Hammer / reserved slot — hidden off-screen (handler slot 9)
        addSlot(new Slot(inventory, 9, -1000, -1000));

        // Output slot — no manual insert (handler slot 10), matches texture at (124, 35)
        addSlot(new Slot(inventory, OUTPUT_SLOT, 124, 35) {
            @Override public boolean canInsert(ItemStack stack) { return false; }
            // Disable hover highlight and interaction while the slot is empty (ghost preview mode)
            @Override public boolean isEnabled() { return hasStack(); }
        });

        // Blueprint slot — hidden off-screen (handler slot 11)
        addSlot(new Slot(inventory, BLUEPRINT_SLOT, -1000, -1000) {
            @Override public boolean canInsert(ItemStack stack) { return false; }
            @Override public boolean canTakeItems(PlayerEntity player) { return false; }
        });

        // Player inventory (handler slots 12-38)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // Hotbar (handler slots 39-47)
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    public boolean hasRecipe()        { return propertyDelegate.get(0) == 1; }
    public boolean isMinigameActive() { return propertyDelegate.get(1) == 1; }
    public boolean canGrind()         { return propertyDelegate.get(2) == 1; }
    public int getHitsRemaining()     { return propertyDelegate.get(3); }
    public int getTotalHits()         { return propertyDelegate.get(4); }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (!player.getWorld().isClient() && blockEntity != null) {
            blockEntity.cancelMinigame();
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot == null || !slot.hasStack()) return newStack;

        ItemStack original = slot.getStack();
        newStack = original.copy();

        // Handler slot indices: 0-8 input, 9 reserved, 10 output, 11 blueprint, 12-38 inv, 39-47 hotbar
        int playerInvStart = 12;
        int playerInvEnd   = 39;
        int hotbarStart    = 39;
        int hotbarEnd      = 48;

        if (invSlot >= 0 && invSlot < 9) {
            // Input → player
            if (!insertItem(original, playerInvStart, hotbarEnd, true)) return ItemStack.EMPTY;
        } else if (invSlot == 10) {
            // Output → player
            if (!insertItem(original, playerInvStart, hotbarEnd, true)) return ItemStack.EMPTY;
        } else if (invSlot >= playerInvStart) {
            // Player → input grid
            if (!insertItem(original, INPUT_START, INPUT_END, false)) {
                if (invSlot < playerInvEnd) {
                    if (!insertItem(original, hotbarStart, hotbarEnd, false)) return ItemStack.EMPTY;
                } else {
                    if (!insertItem(original, playerInvStart, playerInvEnd, false)) return ItemStack.EMPTY;
                }
            }
        }

        if (original.isEmpty()) slot.setStack(ItemStack.EMPTY);
        else slot.markDirty();
        if (original.getCount() == newStack.getCount()) return ItemStack.EMPTY;
        slot.onTakeItem(player, original);
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}

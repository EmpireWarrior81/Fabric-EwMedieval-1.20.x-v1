package net.empire.ewmedieval.item;

import net.empire.ewmedieval.item.toolitems.TongsItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeatedIngotItem extends Item {

    private static final int BURN_INTERVAL = 20;  // 1 second

    // One burn event per player per interval regardless of how many heated ingots they carry
    private static final Map<UUID, Long> lastBurnTimes = new HashMap<>();

    private final Item cooledItem;
    private final int coolTicks;

    public HeatedIngotItem(Item cooledItem, int coolTicks, Settings settings) {
        super(settings);
        this.cooledItem = cooledItem;
        this.coolTicks = coolTicks;
    }

    public Item getCooledItem() { return cooledItem; }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient()) return;
        if (!(entity instanceof PlayerEntity player)) return;

        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("heated_since")) {
            nbt.putLong("heated_since", world.getTime());
            nbt.putLong("next_check", world.getTime() + 40);
            return;
        }

        // One burn/protect event per player per interval
        UUID id = player.getUuid();
        if (world.getTime() - lastBurnTimes.getOrDefault(id, 0L) >= BURN_INTERVAL) {
            lastBurnTimes.put(id, world.getTime());

            ItemStack mainHand = player.getMainHandStack();
            ItemStack offHand  = player.getOffHandStack();
            boolean mainTongs  = mainHand.getItem() instanceof TongsItem;
            boolean offTongs   = offHand.getItem()  instanceof TongsItem;

            if (mainTongs || offTongs) {
                // Tongs absorb the heat — wear them down
                if (mainTongs) {
                    mainHand.damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                } else {
                    offHand.damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
                }
            } else {
                player.damage(world.getDamageSources().inFire(), 1.0f);
            }
        }

        // Throttled cooling check
        if (world.getTime() < nbt.getLong("next_check")) return;

        if (world.getTime() - nbt.getLong("heated_since") >= coolTicks) {
            ItemStack cooled = new ItemStack(cooledItem, stack.getCount());
            // Offhand slot index is always 0 within its list, so setStack(0) would wrongly target
            // hotbar slot 0 — detect offhand by reference and use the correct hand instead.
            if (player.getOffHandStack() == stack) {
                player.setStackInHand(Hand.OFF_HAND, cooled);
            } else {
                player.getInventory().setStack(slot, cooled);
            }
        } else {
            nbt.putLong("next_check", world.getTime() + 40 + (long) (Math.random() * 40));
        }
    }
}

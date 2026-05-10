package net.empire.ewmedieval.forging;

import net.empire.ewmedieval.item.HeatedIngotItem;
import net.empire.ewmedieval.item.toolitems.TongsItem;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class HotItemCoolingHandler {

    /** Ticks before a hot item auto-cools while in a player inventory (60 s). */
    private static final long COOLING_TICKS = 1200L;
    /** Full inventory cool-check interval (ticks). */
    private static final int COOL_CHECK_INTERVAL = 40;
    /** Held-item burn and entity water-check interval (ticks). */
    private static final int BURN_INTERVAL = 20;

    /** Thread-safe set of every heated ItemEntity currently loaded in any server world. */
    private static final Set<ItemEntity> heatedEntities = ConcurrentHashMap.newKeySet();

    private static int tickCounter = 0;

    private HotItemCoolingHandler() {}

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(HotItemCoolingHandler::onServerTick);

        // Track item entities as they're loaded so we can water-cool them
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ItemEntity ie && isHeatedStack(ie.getStack())) {
                heatedEntities.add(ie);
            }
        });
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            if (entity instanceof ItemEntity ie) {
                heatedEntities.remove(ie);
            }
        });

        // Cool heated items by right-clicking a water cauldron or water source block
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClient()) return ActionResult.PASS;

            ItemStack held = player.getStackInHand(hand);
            if (!isHeatedStack(held)) return ActionResult.PASS;

            BlockPos pos = hitResult.getBlockPos();
            var blockState = world.getBlockState(pos);

            // Water cauldron — consumes one level
            if (blockState.isOf(Blocks.WATER_CAULDRON)) {
                int level = blockState.get(LeveledCauldronBlock.LEVEL);
                if (level <= 0) return ActionResult.PASS;

                coolHeldStack(held, player);
                world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.6f, 1.0f);
                spawnSmoke(world, pos);

                world.setBlockState(pos, level == 1
                        ? Blocks.CAULDRON.getDefaultState()
                        : blockState.with(LeveledCauldronBlock.LEVEL, level - 1));
                return ActionResult.SUCCESS;
            }

            // Water source block — free to use
            if (blockState.getFluidState().isOf(Fluids.WATER) && blockState.getFluidState().isStill()) {
                coolHeldStack(held, player);
                world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.6f, 1.0f);
                spawnSmoke(world, pos);
                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }

    // ── Server tick ───────────────────────────────────────────────────────────

    private static void onServerTick(MinecraftServer server) {
        tickCounter++;
        if (tickCounter % BURN_INTERVAL != 0) return;

        long worldTime = server.getOverworld().getTime();
        boolean doCoolCheck = (tickCounter % COOL_CHECK_INTERVAL == 0);

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if (doCoolCheck) {
                for (int i = 0; i < player.getInventory().size(); i++) {
                    inventoryCoolIfReady(player.getInventory().getStack(i), worldTime);
                }
            }
            checkHeldItemBurn(player);
        }

        // Cool heated item entities touching water
        Iterator<ItemEntity> it = heatedEntities.iterator();
        while (it.hasNext()) {
            ItemEntity entity = it.next();
            if (!entity.isAlive()) { it.remove(); continue; }

            ItemStack stack = entity.getStack();
            if (!isHeatedStack(stack)) { it.remove(); continue; }

            if (entity.isTouchingWater()) {
                coolEntityStack(entity, stack);
                it.remove();
            } else {
                net.minecraft.world.World ew = entity.getWorld();
                BlockPos ep = entity.getBlockPos();
                var bs = ew.getBlockState(ep);
                if (bs.isOf(Blocks.WATER_CAULDRON)) {
                    int lvl = bs.get(LeveledCauldronBlock.LEVEL);
                    if (lvl > 0) {
                        coolEntityStack(entity, stack);
                        ew.setBlockState(ep, lvl == 1
                                ? Blocks.CAULDRON.getDefaultState()
                                : bs.with(LeveledCauldronBlock.LEVEL, lvl - 1));
                        it.remove();
                    }
                }
            }
        }
    }

    // ── Cooling logic ─────────────────────────────────────────────────────────

    /**
     * Cools a stack held in the player's hand.
     * HeatedIngotItem stacks are replaced with their cooled item variant;
     * forged items just have the Heated NBT stripped.
     */
    private static void coolHeldStack(ItemStack held, net.minecraft.entity.player.PlayerEntity player) {
        if (held.getItem() instanceof HeatedIngotItem hi) {
            ItemStack cooled = new ItemStack(hi.getCooledItem(), held.getCount());
            copyNbtMinus(held, cooled, "heated_since", "next_check");
            // Replace the stack in whatever hand or slot it's in
            if (player.getOffHandStack() == held) {
                player.setStackInHand(net.minecraft.util.Hand.OFF_HAND, cooled);
            } else {
                // Replace in main hand; inventory slot index is managed by vanilla
                player.setStackInHand(net.minecraft.util.Hand.MAIN_HAND, cooled);
            }
        } else {
            ForgingQualityHelper.setHeated(held, false, 0);
        }
    }

    /**
     * Cools a dropped item entity touching water.
     * HeatedIngotItem entities become their cooled variant; others just lose the Heated tag.
     */
    private static void coolEntityStack(ItemEntity entity, ItemStack stack) {
        if (stack.getItem() instanceof HeatedIngotItem hi) {
            ItemStack cooled = new ItemStack(hi.getCooledItem(), stack.getCount());
            copyNbtMinus(stack, cooled, "heated_since", "next_check");
            entity.setStack(cooled);
        } else {
            ForgingQualityHelper.setHeated(stack, false, 0);
        }

        if (entity.getWorld() instanceof ServerWorld sw) {
            sw.spawnParticles(ParticleTypes.SMOKE,
                    entity.getX(), entity.getY() + 0.3, entity.getZ(),
                    5, 0.15, 0.15, 0.15, 0.02);
            sw.playSound(null, entity.getBlockPos(),
                    SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 1.5f);
        }
    }

    /** Copies all NBT from src to dst, excluding the listed keys. */
    private static void copyNbtMinus(ItemStack src, ItemStack dst, String... excludeKeys) {
        if (!src.hasNbt()) return;
        NbtCompound tag = src.getNbt().copy();
        for (String key : excludeKeys) tag.remove(key);
        if (!tag.isEmpty()) dst.setNbt(tag);
    }

    // ── Burn check ────────────────────────────────────────────────────────────

    private static void checkHeldItemBurn(ServerPlayerEntity player) {
        boolean hasHotForgedItem = false;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack s = player.getInventory().getStack(i);
            if (!s.isEmpty() && ForgingQualityHelper.isHeated(s) && !(s.getItem() instanceof HeatedIngotItem)) {
                hasHotForgedItem = true;
                break;
            }
        }
        if (!hasHotForgedItem) return;

        ItemStack main = player.getMainHandStack();
        ItemStack off  = player.getOffHandStack();
        boolean mainTongs = main.getItem() instanceof TongsItem;
        boolean offTongs  = off.getItem()  instanceof TongsItem;

        if (mainTongs || offTongs) {
            if (mainTongs) {
                main.damage(1, player, p -> p.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            } else {
                off.damage(1, player, p -> p.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
            }
        } else {
            player.damage(player.getDamageSources().inFire(), 1.0f);
        }
    }

    /** Auto-cool items sitting in a player's inventory after the cooling duration. */
    private static void inventoryCoolIfReady(ItemStack stack, long worldTime) {
        if (!ForgingQualityHelper.isHeated(stack)) return;
        long since = ForgingQualityHelper.getHeatedSince(stack);
        if (since >= 0 && worldTime - since >= COOLING_TICKS) {
            ForgingQualityHelper.setHeated(stack, false, 0);
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /** True for both HeatedIngotItem stacks and items with Heated=true NBT. */
    private static boolean isHeatedStack(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return (stack.getItem() instanceof HeatedIngotItem) || ForgingQualityHelper.isHeated(stack);
    }

    private static void spawnSmoke(World world, BlockPos pos) {
        if (!(world instanceof ServerWorld sw)) return;
        sw.spawnParticles(ParticleTypes.SMOKE,
                pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                8, 0.2, 0.2, 0.2, 0.02);
    }
}

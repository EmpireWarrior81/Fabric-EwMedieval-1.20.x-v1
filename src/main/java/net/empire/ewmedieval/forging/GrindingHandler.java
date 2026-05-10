package net.empire.ewmedieval.forging;

import net.empire.ewmedieval.network.AnvilStartMinigamePacket;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handles two grindstone mechanics:
 *
 *  A) Quality-upgrade minigame — triggered when the held item has NeedsGrinding=true.
 *     A timing-zone minigame runs; ≥75% perfect hits upgrades quality one tier.
 *
 *  B) Instant durability repair — triggered when the held item is damaged.
 *     Restores 20% of max durability per grind, but permanently reduces max
 *     durability by 10% each time (tracked in ReducedMaxDurability NBT).
 */
public final class GrindingHandler {

    private static final int GRIND_HITS = 6;
    private static final Map<UUID, GrindSession> sessions = new ConcurrentHashMap<>();

    private GrindingHandler() {}

    public static void init() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClient()) return ActionResult.PASS;
            if (!(player instanceof ServerPlayerEntity serverPlayer)) return ActionResult.PASS;

            BlockPos pos = hitResult.getBlockPos();
            if (!world.getBlockState(pos).isOf(Blocks.GRINDSTONE)) return ActionResult.PASS;

            // If quality-upgrade minigame is running, strike comes via AnvilStrikePacket
            if (sessions.containsKey(serverPlayer.getUuid())) return ActionResult.SUCCESS;

            // ── Path A: quality-upgrade minigame ───────────────────────────────
            // Only items explicitly marked as needing grinding trigger this
            ItemStack forGrind = player.getMainHandStack();
            if (!ForgingQualityHelper.needsGrinding(forGrind)) {
                forGrind = player.getOffHandStack();
            }
            if (ForgingQualityHelper.needsGrinding(forGrind)) {
                sessions.put(serverPlayer.getUuid(), new GrindSession());
                world.playSound(null, pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1f, 1f);
                // Tighter zones than forging — grinding demands more precision
                AnvilStartMinigamePacket.send(serverPlayer, pos, GRIND_HITS,
                        1.0f, 40f, 14f, 0.88f, 0.25f, 4.5f);
                return ActionResult.SUCCESS;
            }

            // ── Path B: instant durability repair (sneak + right-click) ──────
            // Require sneaking so vanilla grindstone GUI still opens on normal click
            if (serverPlayer.isSneaking()) {
                ItemStack forDura = player.getMainHandStack();
                if (forDura.isEmpty() || !forDura.isDamageable() || forDura.getDamage() <= 0) {
                    forDura = player.getOffHandStack();
                }
                if (!forDura.isEmpty() && forDura.isDamageable() && forDura.getDamage() > 0) {
                    if (world instanceof ServerWorld serverWorld) {
                        doInstantDurabilityGrind(forDura, pos, serverPlayer, serverWorld);
                    }
                    return ActionResult.SUCCESS;
                }
            }

            return ActionResult.PASS;
        });
    }

    // ── Quality-upgrade minigame ───────────────────────────────────────────────

    /** Called from AnvilStrikePacket when the hit pos is a grindstone. */
    public static void recordGrindHit(int hitType, ServerPlayerEntity player, BlockPos pos) {
        GrindSession session = sessions.get(player.getUuid());
        if (session == null) return;

        session.record(hitType);

        // Spark particles at the grindstone top
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            net.minecraft.util.math.random.Random rng = serverWorld.getRandom();
            for (int i = 0; i < 10; i++) {
                double ox = (rng.nextFloat() - 0.5) * 0.4;
                double oz = (rng.nextFloat() - 0.5) * 0.4;
                serverWorld.spawnParticles(ParticleTypes.CRIT,
                        pos.getX() + 0.5 + ox, pos.getY() + 1.0, pos.getZ() + 0.5 + oz,
                        1, 0.0, 0.1, 0.0, 0.1);
            }
        }

        if (session.hitsRemaining <= 0) {
            completeQualityGrind(player, session);
        }
    }

    private static void completeQualityGrind(ServerPlayerEntity player, GrindSession session) {
        sessions.remove(player.getUuid());

        ItemStack target = player.getMainHandStack();
        if (!ForgingQualityHelper.hasQuality(target)) {
            target = player.getOffHandStack();
            if (!ForgingQualityHelper.hasQuality(target)) return;
        }

        float perfectFraction = (float) session.perfectHits / GRIND_HITS;
        if (perfectFraction >= 0.75f) {
            ForgingQuality current = ForgingQualityHelper.getQuality(target);
            ForgingQualityHelper.setQuality(target, ForgingQualityHelper.upgradeQuality(current));
        }
        ForgingQualityHelper.setNeedsGrinding(target, false);
    }

    // ── Instant durability repair ──────────────────────────────────────────────

    /**
     * Repairs 20% of max durability per use, but permanently reduces the
     * effective max durability by 10% each time (floor = lost portion).
     */
    private static void doInstantDurabilityGrind(ItemStack stack, BlockPos pos,
                                                  ServerPlayerEntity player, ServerWorld world) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int timesGround = nbt.getInt("ReducedMaxDurability");

        int origMax = stack.getItem().getMaxDamage();
        float qualityBonus = ForgingQualityHelper.hasQuality(stack)
                ? ForgingQualityHelper.getDurabilityMultiplier(stack) : 0f;
        int scaledMax = Math.max(1, (int)(origMax * (1f + qualityBonus)));

        // Each prior grind permanently removes 10% of max; minimum is 10%
        float penaltyMult = Math.max(0.1f, 1.0f - timesGround * 0.10f);
        int effectiveMax  = Math.max(1, (int)(scaledMax * penaltyMult));
        // Permanently-consumed durability that can never be repaired back
        int floor = scaledMax - effectiveMax;
        int currentDamage = stack.getDamage();

        nbt.putInt("ReducedMaxDurability", timesGround + 1);

        if (currentDamage <= floor) {
            // Item is already at or within the floor (e.g. repaired by other means).
            // Do a full repair as a bonus and apply the next penalty increment.
            stack.setDamage(0);
        } else {
            // Repair 20% of scaledMax, but never go below the permanent floor.
            int repair = Math.max(1, (int)(scaledMax * 0.20f));
            stack.setDamage(Math.max(floor, currentDamage - repair));
        }

        // Sound + sparks
        world.playSound(null, pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1f, 1.2f);
        net.minecraft.util.math.random.Random rng = world.getRandom();
        for (int i = 0; i < 8; i++) {
            double ox = (rng.nextFloat() - 0.5) * 0.4;
            double oz = (rng.nextFloat() - 0.5) * 0.4;
            world.spawnParticles(ParticleTypes.CRIT,
                    pos.getX() + 0.5 + ox, pos.getY() + 1.0, pos.getZ() + 0.5 + oz,
                    1, 0.0, 0.1, 0.0, 0.1);
        }

        // Action-bar message: show what % of max the item can reach after this grind
        float nextPenalty = Math.max(0.1f, 1.0f - (timesGround + 1) * 0.10f);
        int pct = (int)(nextPenalty * 100);
        player.sendMessage(
                Text.literal("Repaired  (max durability now " + pct + "%)").formatted(Formatting.GREEN),
                true);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    public static boolean hasSession(UUID playerUuid) {
        return sessions.containsKey(playerUuid);
    }

    public static void cancelSession(UUID playerUuid) {
        sessions.remove(playerUuid);
    }

    private static final class GrindSession {
        int hitsRemaining = GRIND_HITS;
        int perfectHits, goodHits, missedHits;

        void record(int hitType) {
            switch (hitType) {
                case 2 -> perfectHits++;
                case 1 -> goodHits++;
                default -> missedHits++;
            }
            hitsRemaining--;
        }
    }
}

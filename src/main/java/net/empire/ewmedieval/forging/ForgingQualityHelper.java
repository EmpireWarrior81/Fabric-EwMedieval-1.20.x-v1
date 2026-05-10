package net.empire.ewmedieval.forging;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public final class ForgingQualityHelper {

    public static final String QUALITY_KEY              = "ForgingQuality";
    public static final String HEATED_KEY               = "Heated";
    public static final String HEATED_SINCE_KEY         = "HeatedSince";
    public static final String REDUCED_DURABILITY_KEY   = "ReducedMaxDurability";
    public static final String NEEDS_GRINDING_KEY       = "NeedsGrinding";

    private ForgingQualityHelper() {}

    // ── Quality ──────────────────────────────────────────────────────────────

    public static ForgingQuality getQuality(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(QUALITY_KEY)) return ForgingQuality.NONE;
        return ForgingQuality.fromId(nbt.getString(QUALITY_KEY));
    }

    public static void setQuality(ItemStack stack, ForgingQuality quality) {
        if (quality == ForgingQuality.NONE) {
            NbtCompound nbt = stack.getNbt();
            if (nbt != null) nbt.remove(QUALITY_KEY);
        } else {
            stack.getOrCreateNbt().putString(QUALITY_KEY, quality.getId());
        }
    }

    public static boolean hasQuality(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.contains(QUALITY_KEY);
    }

    // ── Heat ─────────────────────────────────────────────────────────────────

    public static boolean isHeated(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.getBoolean(HEATED_KEY);
    }

    public static void setHeated(ItemStack stack, boolean heated, long worldTime) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (heated) {
            nbt.putBoolean(HEATED_KEY, true);
            nbt.putLong(HEATED_SINCE_KEY, worldTime);
        } else {
            nbt.remove(HEATED_KEY);
            nbt.remove(HEATED_SINCE_KEY);
        }
    }

    public static long getHeatedSince(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null ? nbt.getLong(HEATED_SINCE_KEY) : -1L;
    }

    // ── Grinding state ────────────────────────────────────────────────────────

    public static boolean needsGrinding(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.getBoolean(NEEDS_GRINDING_KEY);
    }

    public static void setNeedsGrinding(ItemStack stack, boolean value) {
        if (value) {
            stack.getOrCreateNbt().putBoolean(NEEDS_GRINDING_KEY, true);
        } else {
            NbtCompound nbt = stack.getNbt();
            if (nbt != null) nbt.remove(NEEDS_GRINDING_KEY);
        }
    }

    // ── Grinding upgrade ──────────────────────────────────────────────────────

    /** Returns the quality one tier above the given quality (capped at MASTER). */
    public static ForgingQuality upgradeQuality(ForgingQuality current) {
        return switch (current) {
            case POOR   -> ForgingQuality.WELL;
            case WELL   -> ForgingQuality.EXPERT;
            case EXPERT -> ForgingQuality.PERFECT;
            default     -> ForgingQuality.MASTER; // PERFECT → MASTER
        };
    }

    // ── Old grinding penalty (durability) ─────────────────────────────────────

    public static int getReducedMaxDurability(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null ? nbt.getInt(REDUCED_DURABILITY_KEY) : 0;
    }

    public static void incrementReducedMaxDurability(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(REDUCED_DURABILITY_KEY, getReducedMaxDurability(stack) + 1);
    }

    // ── Stat multipliers ─────────────────────────────────────────────────────

    public static float getDurabilityMultiplier(ItemStack stack) {
        return getQuality(stack).getDurabilityMultiplier();
    }

    public static float getDamageMultiplier(ItemStack stack) {
        return getQuality(stack).getDamageMultiplier();
    }
}

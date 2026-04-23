package net.empire.ewmedieval.nutrition;

import net.empire.ewmedieval.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;

public final class NutritionEffects {

    public static void apply(ServerPlayerEntity player, NutritionData data) {
        if (player.isCreative()) return;
        applyCarbs(player, data.carbs);
        applyProtein(player, data.protein);
        applyFat(player, data.fat);
        applyVitamins(player, data.vitamins);
        applyMinerals(player, data.minerals);
    }

    // --- carbs (energy) ---
    // Severe deficit: total energy crash — can't move, work, or fight
    // Mild deficit:   SLUGGISH (softer than SLOWNESS I, just dragging your feet)
    // Mild excess:    NAUSEA (bloated from too many carbs)
    // Severe excess:  sugar crash — bloated, slow, paradoxically hungry again
    private static void applyCarbs(ServerPlayerEntity player, float v) {
        float low  = NutritionConstants.LOW_BAD[0];   // 25
        float high = NutritionConstants.HIGH_BAD[0];  // 300
        if (v <= low / 2f) {
            add(player, StatusEffects.SLOWNESS, 0);
            add(player, StatusEffects.WEAKNESS, 0);
            add(player, StatusEffects.MINING_FATIGUE, 0);
        } else if (v < low) {
            add(player, ModEffects.SLUGGISH, 0);
        } else if (v >= (high + NutritionConstants.MAX) / 2f) {
            add(player, StatusEffects.NAUSEA, 0);
            add(player, ModEffects.SLUGGISH, 0);
            add(player, StatusEffects.HUNGER, 0);
        } else if (v > high) {
            add(player, StatusEffects.NAUSEA, 0);
        }
    }

    // --- protein (muscle) ---
    // Severe deficit: muscle wasting — can't fight or mine
    // Mild deficit:   WEAKNESS I — reduced strength
    // Mild excess:    SLUGGISH — heavy and sluggish from over-eating protein
    // Severe excess:  NAUSEA + POISON — kidney/liver overload
    private static void applyProtein(ServerPlayerEntity player, float v) {
        float low  = NutritionConstants.LOW_BAD[1];   // 25
        float high = NutritionConstants.HIGH_BAD[1];  // 300
        if (v <= low / 2f) {
            add(player, StatusEffects.WEAKNESS, 1);
            add(player, StatusEffects.MINING_FATIGUE, 0);
        } else if (v < low) {
            add(player, StatusEffects.WEAKNESS, 0);
        } else if (v >= (high + NutritionConstants.MAX) / 2f) {
            add(player, StatusEffects.NAUSEA, 0);
            add(player, StatusEffects.POISON, 0);
        } else if (v > high) {
            add(player, ModEffects.SLUGGISH, 0);
        }
    }

    // --- fat (reserves) ---
    // Severe deficit: FAMISHED — no fat reserves, body starving for energy
    // Mild deficit:   HUNGER — constant nagging hunger
    // Mild excess:    SLUGGISH — feeling heavy
    // Severe excess:  very overweight — slow, heavy, nauseous
    private static void applyFat(ServerPlayerEntity player, float v) {
        float low  = NutritionConstants.LOW_BAD[2];   // 25
        float high = NutritionConstants.HIGH_BAD[2];  // 250
        if (v <= low / 2f) {
            add(player, ModEffects.FAMISHED, 0);
            add(player, StatusEffects.WEAKNESS, 0);
        } else if (v < low) {
            add(player, StatusEffects.HUNGER, 0);
        } else if (v >= (high + NutritionConstants.MAX) / 2f) {
            add(player, StatusEffects.SLOWNESS, 0);
            add(player, StatusEffects.NAUSEA, 0);
            add(player, ModEffects.SLUGGISH, 0);
        } else if (v > high) {
            add(player, ModEffects.SLUGGISH, 0);
        }
    }

    // --- vitamins (immune system) ---
    // Severe deficit: immune collapse → fever (WEAKNESS II + FEVERISH)
    // Mild deficit:   WEAKNESS I — reduced immune response
    // Mild excess:    NAUSEA — hypervitaminosis starting
    // Severe excess:  toxic overdose — nausea, poison, weakness all at once
    private static void applyVitamins(ServerPlayerEntity player, float v) {
        float low  = NutritionConstants.LOW_BAD[3];   // 50
        float high = NutritionConstants.HIGH_BAD[3];  // 325
        if (v <= low / 2f) {
            add(player, StatusEffects.WEAKNESS, 1);
            add(player, ModEffects.FEVERISH, 0);
        } else if (v < low) {
            add(player, StatusEffects.WEAKNESS, 0);
        } else if (v >= (high + NutritionConstants.MAX) / 2f) {
            add(player, StatusEffects.NAUSEA, 0);
            add(player, StatusEffects.POISON, 0);
            add(player, StatusEffects.WEAKNESS, 0);
        } else if (v > high) {
            add(player, StatusEffects.NAUSEA, 0);
        }
    }

    // --- minerals (bones, nerves) ---
    // Severe deficit: BRITTLE bones + nerve damage
    // Mild deficit:   SLUGGISH + MINING_FATIGUE — bone density loss, slow and weak
    // Mild excess:    NAUSEA — mineral imbalance
    // Severe excess:  NAUSEA + POISON — mineral toxicity
    private static void applyMinerals(ServerPlayerEntity player, float v) {
        float low  = NutritionConstants.LOW_BAD[4];   // 25
        float high = NutritionConstants.HIGH_BAD[4];  // 300
        if (v <= low / 2f) {
            add(player, ModEffects.BRITTLE, 0);
            add(player, StatusEffects.MINING_FATIGUE, 0);
            add(player, StatusEffects.WEAKNESS, 0);
        } else if (v < low) {
            add(player, ModEffects.SLUGGISH, 0);
            add(player, StatusEffects.MINING_FATIGUE, 0);
        } else if (v >= (high + NutritionConstants.MAX) / 2f) {
            add(player, StatusEffects.NAUSEA, 0);
            add(player, StatusEffects.POISON, 0);
        } else if (v > high) {
            add(player, StatusEffects.NAUSEA, 0);
        }
    }

    private static void add(ServerPlayerEntity player, StatusEffect effect, int amplifier) {
        player.addStatusEffect(new StatusEffectInstance(
                effect, NutritionConstants.EFFECT_DURATION, amplifier, false, false, true));
    }
}

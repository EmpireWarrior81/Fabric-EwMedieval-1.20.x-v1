/*
package net.empire.ewmedieval.thirst;

import net.empire.ewmedieval.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public final class ThirstEffects {

    public static final int PURIFIED = 0;
    public static final int IMPURE   = 1;
    public static final int DIRTY    = 2;

    public static void apply(int quality, ServerPlayerEntity player, World world) {
        if (quality == DIRTY) {
            if (world.random.nextFloat() < 0.75f) {
                player.addStatusEffect(new StatusEffectInstance(
                        ModEffects.THIRST_EFFECT, 500, 1, false, true, true));
            }
            if (world.random.nextFloat() < 0.20f) {
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.NAUSEA, 200, 0, false, true, true));
            }
            if (world.random.nextFloat() < 0.05f) {
                player.addStatusEffect(new StatusEffectInstance(
                        ModEffects.FEVERISH, 600, 0, false, true, true));
            }
        } else if (quality == IMPURE) {
            if (world.random.nextFloat() < 0.375f) {
                player.addStatusEffect(new StatusEffectInstance(
                        ModEffects.THIRST_EFFECT, 500, 0, false, true, true));
            }
        }
    }

    private ThirstEffects() {}
}
*/

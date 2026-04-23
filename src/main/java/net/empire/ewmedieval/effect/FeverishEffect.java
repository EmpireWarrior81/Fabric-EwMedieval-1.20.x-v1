package net.empire.ewmedieval.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FeverishEffect extends StatusEffect {

    public FeverishEffect() {
        super(StatusEffectCategory.HARMFUL, 0xD44832);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Every 3 seconds (60 ticks) flip between "hot" (flushed/fast) and "cold" (chills/slow).
        boolean hotPhase = (entity.getWorld().getTime() / 60) % 2 == 0;
        if (hotPhase) {
            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SPEED, 70, amplifier, false, false, false));
        } else {
            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SLOWNESS, 70, amplifier + 1, false, false, false));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0; // check every second
    }
}

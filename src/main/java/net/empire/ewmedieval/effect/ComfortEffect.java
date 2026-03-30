package net.empire.ewmedieval.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class ComfortEffect extends StatusEffect {

    public ComfortEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Don't heal if Regeneration is already doing the job
        if (entity.hasStatusEffect(StatusEffects.REGENERATION)) {
            return;
        }

        // Don't heal if the player still has saturation to spend
        if (entity instanceof PlayerEntity player) {
            if (player.getHungerManager().getSaturationLevel() > 0.0F) {
                return;
            }
        }

        // Heal 0.5 hearts if not at full health
        if (entity.getHealth() < entity.getMaxHealth()) {
            entity.heal(1.0F);
        }
    }

    // Only tick every 80 ticks — slow healing pace
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 80 == 0;
    }
}

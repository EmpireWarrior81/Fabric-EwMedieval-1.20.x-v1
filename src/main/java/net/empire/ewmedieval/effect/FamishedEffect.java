package net.empire.ewmedieval.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class FamishedEffect extends StatusEffect {

    public FamishedEffect() {
        super(StatusEffectCategory.HARMFUL, 0xC8A45A);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().isClient) return;
        if (!(entity instanceof PlayerEntity player)) return;
        // Drain saturation and add exhaustion so the food bar empties faster.
        // Amplifier 0 = mild drain, amplifier 1 = severe drain.
        float satDrain = 0.5f + amplifier * 0.5f;
        float current = player.getHungerManager().getSaturationLevel();
        player.getHungerManager().setSaturationLevel(Math.max(0f, current - satDrain));
        player.addExhaustion(0.5f * (amplifier + 1));
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 40 == 0; // every 2 seconds
    }
}

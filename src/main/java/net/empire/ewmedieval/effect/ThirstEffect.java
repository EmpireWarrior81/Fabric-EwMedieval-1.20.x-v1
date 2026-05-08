/*
package net.empire.ewmedieval.effect;

import net.empire.ewmedieval.thirst.ThirstHolder;
import net.empire.ewmedieval.thirst.ThirstManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class ThirstEffect extends StatusEffect {

    private static final float DEHYDRATION_PER_TICK = 0.02f;

    public ThirstEffect() {
        super(StatusEffectCategory.HARMFUL, 0x5A1A00);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient() && entity instanceof PlayerEntity player) {
            ThirstManager tm = ((ThirstHolder) player).ewmedieval$getThirstManager();
            tm.addDehydration(DEHYDRATION_PER_TICK * (amplifier + 1));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
*/

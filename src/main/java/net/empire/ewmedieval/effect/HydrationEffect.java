/*
package net.empire.ewmedieval.effect;

import net.empire.ewmedieval.thirst.ThirstHolder;
import net.empire.ewmedieval.thirst.ThirstManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class HydrationEffect extends StatusEffect {

    public HydrationEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x3F76E4);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient() && entity instanceof PlayerEntity player) {
            ThirstManager tm = ((ThirstHolder) player).ewmedieval$getThirstManager();
            tm.add(amplifier + 1);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int interval = 50 >> amplifier;
        return interval <= 0 || duration % interval == 0;
    }
}
*/

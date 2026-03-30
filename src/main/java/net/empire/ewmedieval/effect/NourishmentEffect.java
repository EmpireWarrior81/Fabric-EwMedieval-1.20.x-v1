package net.empire.ewmedieval.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;

public class NourishmentEffect extends StatusEffect {

    public NourishmentEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Only runs server-side for players
        if (entity.getWorld().isClient) return;
        if (!(entity instanceof PlayerEntity player)) return;

        HungerManager hungerManager = player.getHungerManager();

        // Check if the player is currently healing via natural regeneration
        // (has natural regen rule on, is hurt, and has enough food level)
        boolean isHealingWithHunger =
                player.getWorld().getGameRules().getBoolean(GameRules.NATURAL_REGENERATION)
                        && player.getHealth() < player.getMaxHealth()
                        && hungerManager.getFoodLevel() >= 18;

        // Only reduce exhaustion if the player isn't currently healing with hunger
        if (!isHealingWithHunger) {
            float exhaustion = hungerManager.getExhaustion();
            float reduction = Math.min(exhaustion, 4.0F);
            if (exhaustion > 0.0F) {
                // Negative exhaustion effectively cancels out hunger drain
                player.addExhaustion(-reduction);
            }
        }
    }

    // Ticks every single game tick — constant exhaustion management
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
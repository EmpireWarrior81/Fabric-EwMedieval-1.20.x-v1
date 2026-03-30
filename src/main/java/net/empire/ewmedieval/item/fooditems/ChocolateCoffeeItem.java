package net.empire.ewmedieval.item.fooditems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;

public class ChocolateCoffeeItem extends DrinkableItem {

    public ChocolateCoffeeItem(Settings settings) {
        super(settings, true, true);
    }

    @Override
    public void affectConsumer(ItemStack stack, World world, LivingEntity consumer) {
        Iterator<StatusEffectInstance> itr = consumer.getStatusEffects().iterator();
        ArrayList<StatusEffect> compatibleEffects = new ArrayList<>();

        while (itr.hasNext()) {
            StatusEffectInstance effect = itr.next();
            if (effect.getEffectType().getCategory() == StatusEffectCategory.HARMFUL) {
                compatibleEffects.add(effect.getEffectType());
            }
        }

        if (!compatibleEffects.isEmpty()) {
            StatusEffect selectedEffect = compatibleEffects.get(
                    world.random.nextInt(compatibleEffects.size()));
            consumer.removeStatusEffect(selectedEffect);
        }
    }
}
package net.empire.ewmedieval.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MelonJuiceItem extends DrinkableItem {

    public MelonJuiceItem(Settings settings) {
        super(settings, false, false);
    }

    @Override
    public void affectConsumer(ItemStack stack, World world, LivingEntity consumer) {
        consumer.heal(2.0F);
    }
}
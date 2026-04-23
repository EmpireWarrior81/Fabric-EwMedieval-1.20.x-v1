package net.empire.ewmedieval.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class BrittleEffect extends StatusEffect {

    // Reduces armor and max health to simulate fragile, mineral-starved bones.
    private static final UUID ARMOR_UUID  = UUID.fromString("7d4e1a3b-9c2f-4e8d-b6a5-1f3c7e2d9a4b");
    private static final UUID HEALTH_UUID = UUID.fromString("3e8b2f7a-5c4d-4a1e-9b7f-6d2c8a3e1f5b");

    public BrittleEffect() {
        super(StatusEffectCategory.HARMFUL, 0xD4C4A0);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        EntityAttributeInstance armor = attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR);
        if (armor != null) {
            armor.removeModifier(ARMOR_UUID);
            armor.addPersistentModifier(new EntityAttributeModifier(
                    ARMOR_UUID, "Brittle Armor", -(2.0 + amplifier * 2.0),
                    EntityAttributeModifier.Operation.ADDITION));
        }
        EntityAttributeInstance maxHealth = attributes.getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.removeModifier(HEALTH_UUID);
            maxHealth.addPersistentModifier(new EntityAttributeModifier(
                    HEALTH_UUID, "Brittle Health", -(2.0 + amplifier * 2.0),
                    EntityAttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        EntityAttributeInstance armor = attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR);
        if (armor != null) armor.removeModifier(ARMOR_UUID);
        EntityAttributeInstance maxHealth = attributes.getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealth != null) maxHealth.removeModifier(HEALTH_UUID);
    }
}

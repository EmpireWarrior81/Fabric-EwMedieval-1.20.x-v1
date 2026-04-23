package net.empire.ewmedieval.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class SlugEffect extends StatusEffect {

    // Weaker than vanilla Slowness I (-20%), this applies -10% movement speed
    private static final UUID MODIFIER_UUID = UUID.fromString("c3a6b1e2-4d7f-4a8c-9e1b-2f5d3a7c8e4f");
    private static final double SPEED_PENALTY = -0.10;

    public SlugEffect() {
        super(StatusEffectCategory.HARMFUL, 0x8A9A6B);
        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                MODIFIER_UUID.toString(), SPEED_PENALTY, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
    }
}
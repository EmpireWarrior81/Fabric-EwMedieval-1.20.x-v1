package net.empire.ewmedieval.effect;

import net.empire.ewmedieval.EwMedieval;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final StatusEffect NOURISHMENT = register("nourishment", new NourishmentEffect());
    public static final StatusEffect COMFORT = register("comfort", new ComfortEffect());

    private static StatusEffect register(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT,
                new Identifier(EwMedieval.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        EwMedieval.LOGGER.info("Registering status effects for " + EwMedieval.MOD_ID);
    }
}
package net.empire.ewmedieval.world.feature;

import net.empire.ewmedieval.EwMedieval;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public class ModFeatureTypes {

    public static final Feature<EwWildCropConfig> WILD_CROP = Registry.register(
            Registries.FEATURE,
            new Identifier(EwMedieval.MOD_ID, "wild_crop"),
            new EwWildCropFeature()
    );

    public static final Feature<EwWildRiceConfig> WILD_RICE = Registry.register(
            Registries.FEATURE,
            new Identifier(EwMedieval.MOD_ID, "wild_rice"),
            new EwWildRiceFeature()
    );

    /** Call once during mod init to trigger the static registrations above. */
    public static void register() {
        EwMedieval.LOGGER.debug("Registering ModFeatureTypes for " + EwMedieval.MOD_ID);
    }
}
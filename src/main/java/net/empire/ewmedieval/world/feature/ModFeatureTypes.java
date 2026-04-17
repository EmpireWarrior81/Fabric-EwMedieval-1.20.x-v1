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

    /** Call once during mod init to trigger the static registration above. */
    public static void register() {
        EwMedieval.LOGGER.debug("Registering ModFeatureTypes for " + EwMedieval.MOD_ID);
    }
}
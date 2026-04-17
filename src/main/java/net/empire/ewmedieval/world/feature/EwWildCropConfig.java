package net.empire.ewmedieval.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.Optional;

/**
 * Config for the ewmedieval:wild_crop custom feature.
 *
 * For each of {@code tries} attempts the feature:
 *   1. Picks a random offset within ±xzSpread (horizontal) and ±ySpread (vertical).
 *   2. Calls {@code floorFeature} (if present) at the ground block (airPos.down()).
 *      Typically used to place coarse_dirt / gravel under the crop.
 *   3. Calls {@code primaryFeature} (or {@code secondaryFeature} if present, chosen randomly)
 *      at the air position (airPos).
 *
 * All three sub-features are inline PlacedFeatures with their own placement
 * predicates — the predicates guard against wrong-surface placement.
 */
public record EwWildCropConfig(
        RegistryEntry<PlacedFeature> primaryFeature,
        Optional<RegistryEntry<PlacedFeature>> secondaryFeature,
        Optional<RegistryEntry<PlacedFeature>> floorFeature,
        float floorChance,
        int tries,
        int xzSpread,
        int ySpread
) implements FeatureConfig {

    public static final Codec<EwWildCropConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    PlacedFeature.REGISTRY_CODEC.fieldOf("primary_feature")
                            .forGetter(EwWildCropConfig::primaryFeature),
                    PlacedFeature.REGISTRY_CODEC.optionalFieldOf("secondary_feature")
                            .forGetter(EwWildCropConfig::secondaryFeature),
                    PlacedFeature.REGISTRY_CODEC.optionalFieldOf("floor_feature")
                            .forGetter(EwWildCropConfig::floorFeature),
                    Codec.floatRange(0f, 1f).fieldOf("floor_chance").orElse(0.35f)
                            .forGetter(EwWildCropConfig::floorChance),
                    Codec.intRange(0, 512).fieldOf("tries").orElse(64)
                            .forGetter(EwWildCropConfig::tries),
                    Codec.intRange(0, 64).fieldOf("xz_spread").orElse(7)
                            .forGetter(EwWildCropConfig::xzSpread),
                    Codec.intRange(0, 64).fieldOf("y_spread").orElse(3)
                            .forGetter(EwWildCropConfig::ySpread)
            ).apply(instance, EwWildCropConfig::new)
    );
}
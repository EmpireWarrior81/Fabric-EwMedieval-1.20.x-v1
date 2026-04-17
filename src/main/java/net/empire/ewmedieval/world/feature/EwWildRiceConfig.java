package net.empire.ewmedieval.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

/**
 * Config for ewmedieval:wild_rice.
 * Simple: just the scatter parameters — the block itself is hard-coded in EwWildRiceFeature.
 */
public record EwWildRiceConfig(int tries, int xzSpread, int ySpread) implements FeatureConfig {

    public static final Codec<EwWildRiceConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.intRange(0, 512).fieldOf("tries").orElse(96)
                            .forGetter(EwWildRiceConfig::tries),
                    Codec.intRange(0, 64).fieldOf("xz_spread").orElse(7)
                            .forGetter(EwWildRiceConfig::xzSpread),
                    Codec.intRange(0, 64).fieldOf("y_spread").orElse(3)
                            .forGetter(EwWildRiceConfig::ySpread)
            ).apply(instance, EwWildRiceConfig::new)
    );
}
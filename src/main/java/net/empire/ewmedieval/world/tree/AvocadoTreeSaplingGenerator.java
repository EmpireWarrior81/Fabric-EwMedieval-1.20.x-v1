package net.empire.ewmedieval.world.tree;

import net.empire.ewmedieval.world.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

/**
 * Tells the avocado sapling (and pit) which configured feature to grow into.
 * The feature itself is defined in data/ewmedieval/worldgen/configured_feature/avocado_tree.json.
 */
public class AvocadoTreeSaplingGenerator extends SaplingGenerator {

    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.AVOCADO_TREE;
    }
}
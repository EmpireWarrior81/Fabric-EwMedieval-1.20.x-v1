package net.empire.ewmedieval.world.feature;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/**
 * ewmedieval:wild_crop
 *
 * Spreads a wild crop across the surface with optional sporadic ground-block
 * replacement (e.g. coarse_dirt under wild carrots).
 *
 * Origin = first air position above the surface (from MOTION_BLOCKING heightmap).
 *
 * Per try:
 *   airPos   = origin + random(±xzSpread, ±ySpread, ±xzSpread)
 *   groundPos = airPos.down()
 *
 *   → floor_feature  generates at groundPos  (replaces surface dirt with groundBlock)
 *   → primary/secondary generates at airPos  (places the crop)
 *
 * Each sub-feature has its own placement predicates, so wrong-surface positions
 * are silently skipped.
 */
public class EwWildCropFeature extends Feature<EwWildCropConfig> {

    public EwWildCropFeature() {
        super(EwWildCropConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<EwWildCropConfig> context) {
        EwWildCropConfig config   = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        ChunkGenerator generator  = context.getGenerator();
        BlockPos origin           = context.getOrigin();
        Random random             = context.getRandom();

        boolean placed = false;

        for (int i = 0; i < config.tries(); i++) {
            int dx = random.nextBetween(-config.xzSpread(), config.xzSpread());
            int dy = random.nextBetween(-config.ySpread(), config.ySpread());
            int dz = random.nextBetween(-config.xzSpread(), config.xzSpread());

            BlockPos airPos = origin.add(dx, dy, dz);

            // 1. Place the ground modifier (e.g. coarse_dirt) at the block below airPos,
            //    but only floor_chance fraction of the time (default 25 %).
            //    This keeps coarse_dirt sporadic — most carrots grow on grass.
            if (config.floorFeature().isPresent() && random.nextFloat() < config.floorChance()) {
                config.floorFeature().get().value().generate(world, generator, random, airPos.down());
            }

            // 2. Place the crop at the air position.
            //    Randomly pick secondary instead of primary (50/50) when one exists.
            RegistryEntry<PlacedFeature> vegFeature =
                    (config.secondaryFeature().isPresent() && random.nextBoolean())
                    ? config.secondaryFeature().get()
                    : config.primaryFeature();

            if (vegFeature.value().generate(world, generator, random, airPos)) {
                placed = true;
            }
        }
        return placed;
    }
}
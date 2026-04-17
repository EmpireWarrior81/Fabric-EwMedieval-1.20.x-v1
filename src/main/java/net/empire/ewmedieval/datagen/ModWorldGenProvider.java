package net.empire.ewmedieval.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.empire.ewmedieval.EwMedieval;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Single source of truth for all wild-plant world generation.
 *
 * To add a new wild plant:
 *   1. Add one line to WILD_CROPS below.
 *   2. Run  ./gradlew runDatagen  to regenerate JSON files.
 *
 * Feature types:
 *   - groundBlock == null  →  minecraft:random_patch      (standard scattered placement)
 *   - groundBlock != null  →  ewmedieval:wild_crop         (custom feature — per-try ground
 *                             block replacement + crop placement, truly sporadic result)
 *
 * Mixed patches (secondary != null) use minecraft:simple_random_selector (random_patch) or
 * the secondary_feature field (wild_crop) so each try picks primary or secondary at random.
 */
public class ModWorldGenProvider implements DataProvider {

    /** What ground tag must be directly below the placed block. */
    enum FloorType {
        DIRT,        // minecraft:dirt tag (grass_block, dirt, coarse_dirt, podzol …)
        SAND,        // minecraft:sand tag (sand, red_sand)
        DIRT_OR_SAND // either of the above
    }

    /**
     * @param name           block/feature ID in the ewmedieval namespace
     * @param tries          placement attempts per patch (density)
     * @param rarity         1-in-N chunk rarity filter (higher = rarer)
     * @param xzSpread       horizontal spread radius (random_patch) or max xz_radius (vegetation_patch)
     * @param ySpread        vertical spread (random_patch only; ignored for vegetation_patch)
     * @param floor          floor the primary block requires
     * @param secondary      full block ID of secondary filler, or null for single-block patches
     * @param secondaryFloor floor the secondary block requires; ignored when secondary is null
     * @param groundBlock    if non-null, use vegetation_patch and replace the surface with this block
     *                       before placing the crop on top (e.g. "minecraft:coarse_dirt")
     * @param biomes         biome keys where this plant generates
     */
    record WildCropEntry(
            String name, int tries, int rarity,
            int xzSpread, int ySpread,
            FloorType floor,
            String secondary, FloorType secondaryFloor,
            String groundBlock,
            List<RegistryKey<Biome>> biomes) {}


    /** DIRT floor, no secondary, random_patch. */
    @SafeVarargs
    private static WildCropEntry crop(String name, int tries, int rarity,
                                      RegistryKey<Biome>... biomes) {
        return new WildCropEntry(name, tries, rarity, 7, 3, FloorType.DIRT, null, null, null, List.of(biomes));
    }

    /** DIRT floor, no secondary, random_patch, custom spread. */
    @SafeVarargs
    private static WildCropEntry cropSpread(String name, int tries, int rarity, int xzSpread, int ySpread,
                                            RegistryKey<Biome>... biomes) {
        return new WildCropEntry(name, tries, rarity, xzSpread, ySpread, FloorType.DIRT, null, null, null, List.of(biomes));
    }

    /** DIRT floor, secondary also on DIRT, random_patch. */
    @SafeVarargs
    private static WildCropEntry cropWith(String name, int tries, int rarity,
                                          String secondary,
                                          RegistryKey<Biome>... biomes) {
        return new WildCropEntry(name, tries, rarity, 7, 3, FloorType.DIRT, secondary, FloorType.DIRT, null, List.of(biomes));
    }

    /** DIRT floor, secondary also on DIRT, random_patch, custom spread. */
    @SafeVarargs
    private static WildCropEntry cropWithSpread(String name, int tries, int rarity, int xzSpread, int ySpread,
                                                String secondary,
                                                RegistryKey<Biome>... biomes) {
        return new WildCropEntry(name, tries, rarity, xzSpread, ySpread, FloorType.DIRT, secondary, FloorType.DIRT, null, List.of(biomes));
    }

    /**
     * DIRT floor, no secondary, vegetation_patch.
     * The surface block (grass_block, dirt …) is replaced by {@code groundBlock}
     * and then the crop is placed on top.
     * xzSpread controls the max radius of the patch (min is always 2).
     * ySpread maps to vertical_range in the vegetation_patch config.
     */
    @SafeVarargs
    private static WildCropEntry cropGround(String name, int tries, int rarity, int xzSpread, int ySpread,
                                            String groundBlock,
                                            RegistryKey<Biome>... biomes) {
        return new WildCropEntry(name, tries, rarity, xzSpread, ySpread, FloorType.DIRT, null, null, groundBlock, List.of(biomes));
    }

    /**
     * DIRT floor, secondary also on DIRT, vegetation_patch.
     * Same as cropGround but with a secondary filler in the patch.
     */
    @SafeVarargs
    private static WildCropEntry cropWithGround(String name, int tries, int rarity, int xzSpread, int ySpread,
                                                String secondary, String groundBlock,
                                                RegistryKey<Biome>... biomes) {
        return new WildCropEntry(name, tries, rarity, xzSpread, ySpread, FloorType.DIRT, secondary, FloorType.DIRT, groundBlock, List.of(biomes));
    }

    /**
     * DIRT_OR_SAND floor, secondary with its own floor type, random_patch.
     * Use for hot-biome crops where the secondary (sandy_shrub) only appears on sand
     * while the primary crop also tolerates dirt.
     */
    @SafeVarargs
    private static WildCropEntry mixedCropWith(String name, int tries, int rarity,
                                               String secondary, FloorType secondaryFloor,
                                               RegistryKey<Biome>... biomes) {
        return new WildCropEntry(name, tries, rarity, 6, 3, FloorType.DIRT_OR_SAND,
                secondary, secondaryFloor, null, List.of(biomes));
    }

    /** DIRT_OR_SAND floor, secondary with its own floor type, random_patch, custom spread. */
    @SafeVarargs
    private static WildCropEntry mixedCropWithSpread(String name, int tries, int rarity, int xzSpread, int ySpread,
                                                     String secondary, FloorType secondaryFloor,
                                                     RegistryKey<Biome>... biomes) {
        return new WildCropEntry(name, tries, rarity, xzSpread, ySpread, FloorType.DIRT_OR_SAND,
                secondary, secondaryFloor, null, List.of(biomes));
    }


    //  ── minecraft:random_patch (standard scattered placement) ────────────────
    //
    //  crop(name, tries, rarity, biomes...)
    //      DIRT floor, no secondary. Default spread (xz=7, y=3).
    //
    //  cropSpread(name, tries, rarity, xzSpread, ySpread, biomes...)
    //      DIRT floor, no secondary. Custom spread.
    //
    //  cropWith(name, tries, rarity, secondary, biomes...)
    //      DIRT floor. Each try picks crop OR secondary (e.g. "minecraft:grass").
    //      Both require dirt below. Default spread (xz=7, y=3).
    //
    //  cropWithSpread(name, tries, rarity, xzSpread, ySpread, secondary, biomes...)
    //      Same as cropWith but custom spread.
    //
    //  mixedCropWith(name, tries, rarity, secondary, secondaryFloor, biomes...)
    //      Primary on DIRT OR SAND. Secondary uses its own floor check.
    //      Great for crops that spawn in both savanna (dirt) and desert (sand),
    //      where the secondary (e.g. sandy_shrub) only appears on sand.
    //      Default spread (xz=6, y=3).
    //
    //  mixedCropWithSpread(name, tries, rarity, xzSpread, ySpread, secondary, secondaryFloor, biomes...)
    //      Same as mixedCropWith but custom spread.
    //
    //  ── ewmedieval:wild_crop (sporadic ground + crop, no filled rectangle) ───
    //
    //  cropGround(name, tries, rarity, xzSpread, ySpread, groundBlock, biomes...)
    //      DIRT floor. Per try: places groundBlock (e.g. "minecraft:coarse_dirt") at
    //      the surface block, then the crop on top. Blocks are placed one by one so
    //      the result looks sporadic — NOT a filled patch like vegetation_patch.
    //
    //  cropWithGround(name, tries, rarity, xzSpread, ySpread, secondary, groundBlock, biomes...)
    //      Same as cropGround but also scatters a secondary filler (chosen 50/50 with
    //      the primary crop) at the air position above each ground block.


    private static final List<WildCropEntry> WILD_CROPS = List.of(

            cropWith("wild_onions", 64, 120, "minecraft:allium",
                    BiomeKeys.PLAINS, BiomeKeys.MEADOW, BiomeKeys.BIRCH_FOREST),
            cropWithGround("wild_carrots", 64, 120, 6, 3, "minecraft:grass", "minecraft:coarse_dirt",
                    BiomeKeys.PLAINS, BiomeKeys.BIRCH_FOREST, BiomeKeys.FOREST),
            cropWith("wild_potatoes", 64, 100, "minecraft:fern",
                    BiomeKeys.PLAINS, BiomeKeys.BIRCH_FOREST, BiomeKeys.FOREST),

            cropWith("wild_turnips", 10, 80, "minecraft:grass",
                    BiomeKeys.TAIGA, BiomeKeys.PLAINS, BiomeKeys.SNOWY_TAIGA),
            cropWith("wild_broccoli", 10, 80, "minecraft:grass",
                    BiomeKeys.PLAINS, BiomeKeys.MEADOW, BiomeKeys.FOREST),
            cropWith("wild_cauliflowers", 10, 80, "minecraft:grass",
                    BiomeKeys.PLAINS, BiomeKeys.MEADOW, BiomeKeys.BIRCH_FOREST),
            cropWith("wild_zucchinis", 10, 80, "minecraft:fern",
                    BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST),
            cropWith("wild_corn", 64, 50, null,
                    BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS),
            cropWithSpread("wild_corn_dry", 32, 100, 5, 2,
                    null,
                    BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.SAVANNA, BiomeKeys.WINDSWEPT_SAVANNA,
                    BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS),
            cropWith("wild_soya", 10, 80, null,
                    BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST),

            mixedCropWith("wild_tomatoes", 64, 100, "minecraft:dead_bush", FloorType.SAND,
                    BiomeKeys.SAVANNA, BiomeKeys.DESERT),
            cropWithSpread("wild_cucumbers", 96, 60, 7, 3,
                    null,
                    BiomeKeys.SWAMP),
            cropWithSpread("wild_eggplants", 96, 80, 7, 3,
                    null,
                    BiomeKeys.JUNGLE, BiomeKeys.SPARSE_JUNGLE, BiomeKeys.BAMBOO_JUNGLE),
            cropWith("wild_sweet_potatoes", 8, 120, "minecraft:grass",
                    BiomeKeys.SAVANNA, BiomeKeys.PLAINS),

            mixedCropWith("wild_cabbages", 64, 30, "ewmedieval:sandy_shrub", FloorType.SAND,
                    BiomeKeys.BEACH),
            mixedCropWith("wild_beetroots", 64, 30, "ewmedieval:sandy_shrub", FloorType.SAND,
                    BiomeKeys.BEACH),

            cropWith("wild_cotton", 6, 160, "minecraft:grass",
                    BiomeKeys.SAVANNA, BiomeKeys.WINDSWEPT_SAVANNA),
            cropWith("wild_bell_peppers", 6, 160, "minecraft:grass",
                    BiomeKeys.SAVANNA, BiomeKeys.JUNGLE, BiomeKeys.SPARSE_JUNGLE),
            mixedCropWith("wild_aji_amarillo", 6, 160, "ewmedieval:sandy_shrub", FloorType.SAND,
                    BiomeKeys.SAVANNA, BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS),
            mixedCropWith("wild_yuca", 6, 160, "ewmedieval:sandy_shrub", FloorType.SAND,
                    BiomeKeys.SAVANNA, BiomeKeys.DESERT, BiomeKeys.JUNGLE),

            cropWith("wild_coffee", 4, 200, "minecraft:fern",
                    BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE)
    );

    private final FabricDataOutput output;

    public ModWorldGenProvider(FabricDataOutput output) {
        this.output = output;
    }


    @SuppressWarnings("unchecked")
    public static void registerBiomeFeatures() {
        for (WildCropEntry entry : WILD_CROPS) {
            RegistryKey<PlacedFeature> key = RegistryKey.of(
                    RegistryKeys.PLACED_FEATURE,
                    new Identifier(EwMedieval.MOD_ID, entry.name()));
            BiomeModifications.addFeature(
                    BiomeSelectors.includeByKey(entry.biomes().toArray(new RegistryKey[0])),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    key);
        }
    }



    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        DataOutput.PathResolver cfResolver =
                output.getResolver(DataOutput.OutputType.DATA_PACK, "worldgen/configured_feature");
        DataOutput.PathResolver pfResolver =
                output.getResolver(DataOutput.OutputType.DATA_PACK, "worldgen/placed_feature");

        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (WildCropEntry entry : WILD_CROPS) {
            Identifier id = new Identifier(EwMedieval.MOD_ID, entry.name());
            futures.add(DataProvider.writeToPath(writer, buildConfiguredFeatureJson(entry), cfResolver.resolveJson(id)));
            futures.add(DataProvider.writeToPath(writer, buildPlacedFeatureJson(entry), pfResolver.resolveJson(id)));
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public String getName() { return "EwMedieval World Gen"; }


    private static JsonObject buildConfiguredFeatureJson(WildCropEntry entry) {
        if (entry.groundBlock() != null) {
            return buildGroundSpatterJson(entry);
        }
        return buildRandomPatchJson(entry);
    }



    private static JsonObject buildRandomPatchJson(WildCropEntry entry) {
        String primaryId = EwMedieval.MOD_ID + ":" + entry.name();

        JsonObject innerFeature;
        if (entry.secondary() != null) {

            FloorType secFloor = entry.secondaryFloor() != null
                    ? entry.secondaryFloor() : entry.floor();

            JsonArray features = new JsonArray();
            features.add(simpleBlockPlacedFeature(primaryId, entry.floor()));
            features.add(simpleBlockPlacedFeature(entry.secondary(), secFloor));

            JsonObject selectorConfig = new JsonObject();
            selectorConfig.add("features", features);

            JsonObject selectorCF = new JsonObject();
            selectorCF.addProperty("type", "minecraft:simple_random_selector");
            selectorCF.add("config", selectorConfig);

            innerFeature = new JsonObject();
            innerFeature.add("feature", selectorCF);
            innerFeature.add("placement", new JsonArray());
        } else {
            innerFeature = simpleBlockPlacedFeature(primaryId, entry.floor());
        }

        JsonObject config = new JsonObject();
        config.addProperty("tries", entry.tries());
        config.addProperty("xz_spread", entry.xzSpread());
        config.addProperty("y_spread", entry.ySpread());
        config.add("feature", innerFeature);

        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:random_patch");
        root.add("config", config);
        return root;
    }



    /**
     * Generates an ewmedieval:wild_crop configured feature.
     *
     * Unlike vegetation_patch (which fills a solid circular area), this custom
     * feature type places blocks individually per try, giving a truly sporadic look:
     *   - floor_feature places {@code entry.groundBlock()} at the ground block position
     *     (only where the surface is already in the dirt tag).
     *   - primary_feature (and optional secondary_feature) places the crop in the
     *     air position directly above.
     *
     * Each sub-feature has its own block_predicate_filter so invalid positions
     * are silently skipped — no "cannot place feature" errors.
     */
    private static JsonObject buildGroundSpatterJson(WildCropEntry entry) {
        String primaryId = EwMedieval.MOD_ID + ":" + entry.name();

        JsonObject config = new JsonObject();
        config.addProperty("tries", entry.tries());
        config.addProperty("xz_spread", entry.xzSpread());
        config.addProperty("y_spread", entry.ySpread());
        config.add("primary_feature", simpleBlockPlacedFeature(primaryId, entry.floor()));

        if (entry.secondary() != null) {
            FloorType secFloor = entry.secondaryFloor() != null ? entry.secondaryFloor() : entry.floor();
            config.add("secondary_feature", simpleBlockPlacedFeature(entry.secondary(), secFloor));
        }

        config.add("floor_feature", buildFloorReplacePF(entry.groundBlock()));

        JsonObject root = new JsonObject();
        root.addProperty("type", EwMedieval.MOD_ID + ":wild_crop");
        root.add("config", config);
        return root;
    }

    /**
     * Builds an inline PlacedFeature that places {@code groundBlockId} at the
     * current position only when that position is in the #minecraft:dirt tag.
     * Used as the floor_feature in ewmedieval:wild_crop — called at groundPos
     * (one block below the crop's air position) to replace surface dirt with,
     * e.g., coarse_dirt.
     */
    private static JsonObject buildFloorReplacePF(String groundBlockId) {
        JsonObject sbCF = simpleBlockFeatureOnly(groundBlockId);

        // Only replace blocks that are in the #minecraft:dirt tag (grass_block, dirt,
        // coarse_dirt, podzol …). Underground dirt also qualifies, but with small
        // y_spread and a MOTION_BLOCKING heightmap origin this is negligible.
        JsonArray zeroOffset = new JsonArray();
        zeroOffset.add(0); zeroOffset.add(0); zeroOffset.add(0);

        JsonObject dirtPred = new JsonObject();
        dirtPred.addProperty("type", "minecraft:matching_block_tag");
        dirtPred.addProperty("tag", "minecraft:dirt");
        dirtPred.add("offset", zeroOffset);

        JsonObject predFilter = new JsonObject();
        predFilter.addProperty("type", "minecraft:block_predicate_filter");
        predFilter.add("predicate", dirtPred);

        JsonArray placement = new JsonArray();
        placement.add(predFilter);

        JsonObject pf = new JsonObject();
        pf.add("feature", sbCF);
        pf.add("placement", placement);
        return pf;
    }


    /**
     * Builds an inline PlacedFeature that places {@code blockId} only when:
     *   1. the target position is air, and
     *   2. the block directly below matches {@code floor}.
     * Used inside random_patch to prevent crops replacing ground blocks.
     */
    private static JsonObject simpleBlockPlacedFeature(String blockId, FloorType floor) {
        JsonObject sbCF = simpleBlockFeatureOnly(blockId);

        JsonObject airCheck = new JsonObject();
        airCheck.addProperty("type", "minecraft:matching_blocks");
        airCheck.addProperty("blocks", "minecraft:air");

        JsonArray predicates = new JsonArray();
        predicates.add(airCheck);
        predicates.add(buildFloorPredicate(floor));

        JsonObject allOf = new JsonObject();
        allOf.addProperty("type", "minecraft:all_of");
        allOf.add("predicates", predicates);

        JsonObject predFilter = new JsonObject();
        predFilter.addProperty("type", "minecraft:block_predicate_filter");
        predFilter.add("predicate", allOf);

        JsonArray placement = new JsonArray();
        placement.add(predFilter);

        JsonObject placedFeature = new JsonObject();
        placedFeature.add("feature", sbCF);
        placedFeature.add("placement", placement);
        return placedFeature;
    }

    /** Builds just the ConfiguredFeature part of a simple_block placement (no predicates). */
    private static JsonObject simpleBlockFeatureOnly(String blockId) {
        JsonObject state = new JsonObject();
        state.addProperty("Name", blockId);

        JsonObject toPlace = new JsonObject();
        toPlace.addProperty("type", "minecraft:simple_state_provider");
        toPlace.add("state", state);

        JsonObject sbConfig = new JsonObject();
        sbConfig.add("to_place", toPlace);

        JsonObject sbCF = new JsonObject();
        sbCF.addProperty("type", "minecraft:simple_block");
        sbCF.add("config", sbConfig);
        return sbCF;
    }

    private static JsonObject buildFloorPredicate(FloorType floor) {
        return switch (floor) {
            case DIRT -> tagPredicate("minecraft:dirt");
            case SAND -> tagPredicate("minecraft:sand");
            case DIRT_OR_SAND -> {
                JsonArray any = new JsonArray();
                any.add(tagPredicate("minecraft:dirt"));
                any.add(tagPredicate("minecraft:sand"));
                JsonObject anyOf = new JsonObject();
                anyOf.addProperty("type", "minecraft:any_of");
                anyOf.add("predicates", any);
                yield anyOf;
            }
        };
    }

    private static JsonObject tagPredicate(String tag) {
        JsonArray offset = new JsonArray();
        offset.add(0); offset.add(-1); offset.add(0);
        JsonObject pred = new JsonObject();
        pred.addProperty("type", "minecraft:matching_block_tag");
        pred.addProperty("tag", tag);
        pred.add("offset", offset);
        return pred;
    }

    private static JsonObject buildPlacedFeatureJson(WildCropEntry entry) {
        JsonObject rarityFilter = new JsonObject();
        rarityFilter.addProperty("type", "minecraft:rarity_filter");
        rarityFilter.addProperty("chance", entry.rarity());

        JsonObject inSquare = new JsonObject();
        inSquare.addProperty("type", "minecraft:in_square");

        JsonObject heightmap = new JsonObject();
        heightmap.addProperty("type", "minecraft:heightmap");
        heightmap.addProperty("heightmap", "MOTION_BLOCKING");

        JsonObject biome = new JsonObject();
        biome.addProperty("type", "minecraft:biome");

        JsonArray placement = new JsonArray();
        placement.add(rarityFilter);
        placement.add(inSquare);
        placement.add(heightmap);
        placement.add(biome);

        JsonObject root = new JsonObject();
        root.addProperty("feature", EwMedieval.MOD_ID + ":" + entry.name());
        root.add("placement", placement);
        return root;
    }
}
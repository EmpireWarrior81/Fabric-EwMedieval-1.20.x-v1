package net.empire.ewmedieval.season;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Maps each growable block to a float[12] of season modifiers.
 * Index = SeasonPeriod ordinal (0=EarlySpring … 11=LateWinter).
 * Values: 1.0=full speed, 0.5=half speed, 0.25=quarter speed, 0.0=blocked.
 *
 * Tropical blocks also get biome-aware overrides:
 *   hot biome  (temp >= 1.0) → always 1.0
 *   frozen biome (temp < 0.15) → always 0.0
 *
 * Unregistered blocks return 1.0 (full speed, unaffected by seasons).
 *
 * Must be initialised via init() after ModBlocks.registerModBlocks().
 */
public final class SeasonCropRegistry {

    /** Set to true by BoneMealItemMixin when a creative player uses bonemeal — skips the season gate. */
    public static final ThreadLocal<Boolean> CREATIVE_BONEMEAL = ThreadLocal.withInitial(() -> false);

    private static final Map<Block, float[]> REGISTRY  = new HashMap<>();
    private static final Set<Block> TROPICAL            = new HashSet<>();
    /** Crops that need consistently moist soil — halved growth in arid biomes (downfall < 0.3). */
    private static final Set<Block> HUMID_CROPS         = new HashSet<>();

    /** Maps traded crop items (seeds + harvested crops) to their 12-period seasonal modifiers. */
    private static final Map<Item, float[]> ITEM_REGISTRY = new HashMap<>();

    public static void init() {
        // ── Cool-season crops (thrive in spring/autumn, bolt or fail in summer heat) ──

        // Spring - Summer - Fall - Winter
        reg(ModBlocks.CABBAGE_CROP,      1.0f,1.0f,0.5f, 0.0f,0.0f,0.0f, 1.0f,1.0f,0.5f, 0.25f,0.0f,0.0f);
        reg(ModBlocks.CAULIFLOWER_CROP,  1.0f,1.0f,0.5f, 0.0f,0.0f,0.0f, 1.0f,1.0f,0.5f, 0.0f, 0.0f,0.0f);
        reg(ModBlocks.BROCCOLI_CROP,     1.0f,1.0f,0.5f, 0.0f,0.0f,0.0f, 1.0f,1.0f,0.5f, 0.0f, 0.0f,0.0f);
        reg(ModBlocks.TURNIP_CROP,       1.0f,1.0f,0.5f, 0.0f,0.0f,0.0f, 1.0f,1.0f,1.0f, 0.25f,0.0f,0.0f);
        reg(ModBlocks.ONION_CROP,        1.0f,1.0f,0.5f, 0.5f,0.0f,0.0f, 0.5f,1.0f,1.0f, 0.25f,0.0f,0.0f);
        // Garlic: can overwinter — plant in autumn, survives mid-winter dormant, harvest late spring
        reg(ModBlocks.GARLIC_CROP,       1.0f,1.0f,0.5f, 0.0f,0.0f,0.0f, 1.0f,1.0f,1.0f, 0.25f,0.25f,0.0f);
        reg(ModBlocks.CHICKPEA_CROP,     1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.5f,0.5f,0.0f, 0.0f, 0.0f,0.0f);
        reg(ModBlocks.PARSLEY_CROP,      1.0f,1.0f,1.0f, 0.5f,0.5f,0.5f, 1.0f,1.0f,0.5f, 0.25f,0.0f,0.25f);

        // ── Warm-season crops (frost-sensitive, need spring/summer warmth) ──
        reg(ModBlocks.BUDDING_TOMATO_CROP, 0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.TOMATO_CROP,         0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.CUCUMBER_CROP,       0.0f,0.0f,1.0f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.ZUCCHINI_CROP,       0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.BELL_PEPPER_CROP,    0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.EGGPLANT_CROP,       0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.CORN_CROP,           0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.SWEET_POTATO_CROP,   0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.SOYBEAN_CROP,        0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        // Rice: needs warm waterlogged conditions — late spring through early autumn
        reg(ModBlocks.RICE_CROP,           0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.RICE_CROP_PANICLES,  0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        reg(ModBlocks.COTTON_CROP,         0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);

        // ── Tropical crops (hot biome = year-round; frozen biome = never; temperate = summer only) ──
        tropical(ModBlocks.COFFEE_CROP,       0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        tropical(ModBlocks.AJI_AMARILLO_CROP, 0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        tropical(ModBlocks.CASSAVA_CROP,      0.0f,0.0f,0.0f, 1.0f,1.0f,1.0f, 0.0f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        tropical(ModBlocks.AVOCADO_PIT,       0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);
        tropical(ModBlocks.AVOCADO_SAPLING,   0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);

        // ── Vanilla crops (CropBlock mixin) ──
        reg(Blocks.WHEAT,    0.5f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.5f, 0.0f,0.0f,0.0f);
        reg(Blocks.CARROTS,  0.5f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.5f, 0.0f,0.0f,0.0f);
        reg(Blocks.POTATOES, 0.5f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.5f, 0.0f,0.0f,0.0f);
        reg(Blocks.BEETROOTS,1.0f,1.0f,0.5f, 0.5f,0.5f,0.5f, 1.0f,1.0f,0.5f, 0.0f,0.0f,0.0f);

        // ── Sweet berry bush — cool-season, thrives in spring/autumn ──
        reg(Blocks.SWEET_BERRY_BUSH, 1.0f,1.0f,0.5f, 0.0f,0.0f,0.0f, 1.0f,1.0f,0.5f, 0.25f,0.0f,0.0f);

        // ── Melon / Pumpkin stems (StemBlock mixin) ──
        reg(Blocks.MELON_STEM,   0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        reg(Blocks.PUMPKIN_STEM, 0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);

        // ── Sugar cane (SugarCaneBlock mixin) ──
        reg(Blocks.SUGAR_CANE, 0.5f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.5f, 0.25f,0.0f,0.25f);

        // ── Item registry (seeds + harvested crops) for villager trade pricing ──
        float[] cool  = {1f,1f,.5f, 0f,0f,0f, 1f,1f,.5f, .25f,0f,0f};
        float[] coolAutumn = {1f,1f,.5f, 0f,0f,0f, 1f,1f,1f, .25f,0f,0f};
        float[] warm  = {0f,.5f,1f, 1f,1f,1f, 1f,.5f,0f, 0f,0f,0f};
        float[] warmLate = {0f,0f,.5f, 1f,1f,1f, .5f,0f,0f, 0f,0f,0f};
        float[] tropical = {0f,.5f,1f, 1f,1f,1f, 1f,.5f,0f, 0f,0f,0f};
        float[] wheat = {.5f,1f,1f, 1f,1f,1f, 1f,.5f,.5f, 0f,0f,0f};

        // Cool-season
        regItems(cool,     ModItems.CABBAGE_SEEDS,     ModItems.CABBAGE,      ModItems.CABBAGE_LEAF);
        regItems(cool,     ModItems.CAULIFLOWER_SEEDS,  ModItems.CAULIFLOWER);
        regItems(cool,     ModItems.BROCCOLI_SEEDS,     ModItems.BROCCOLI);
        regItems(coolAutumn, ModItems.TURNIP_SEEDS,    ModItems.TURNIP);
        regItems(cool,     ModItems.ONION);
        regItems(cool,     ModItems.GARLIC,             ModItems.GARLIC_CLOVE);
        regItems(cool,     ModItems.CHICKPEA);
        regItems(cool,     ModItems.PARSLEY_SEEDS,      ModItems.PARSLEY);

        // Warm-season
        regItems(warm,     ModItems.TOMATO_SEEDS,       ModItems.TOMATO);
        regItems(warmLate, ModItems.CUCUMBER_SEEDS,     ModItems.CUCUMBER);
        regItems(warm,     ModItems.ZUCCHINI_SEEDS,     ModItems.ZUCCHINI);
        regItems(warmLate, ModItems.BELL_PEPPER_SEEDS,
                ModItems.BELL_PEPPER_GREEN, ModItems.BELL_PEPPER_YELLOW, ModItems.BELL_PEPPER_RED);
        regItems(warmLate, ModItems.EGGPLANT_SEEDS,     ModItems.EGGPLANT,     ModItems.WHITE_EGGPLANT);
        regItems(warm,     ModItems.CORN_KERNELS,        ModItems.CORN_COB);
        regItems(warm,     ModItems.SWEET_POTATO);
        regItems(warm,     ModItems.SOYBEAN_POD,         ModItems.SOYBEANS);
        regItems(warmLate, ModItems.COTTON_SEEDS,        ModItems.COTTON_BOLL);
        regItems(warmLate, ModItems.RICE,                ModItems.RICE_PANICLE);

        // Tropical
        regItems(tropical, ModItems.COFFEE_BEANS);
        regItems(tropical, ModItems.AJI_AMARILLO_SEEDS, ModItems.AJI_AMARILLO);
        regItems(tropical, ModItems.CASSAVA);
        regItems(tropical, ModItems.AVOCADO);

        // Vanilla crops
        regItems(wheat,    Items.WHEAT,      Items.WHEAT_SEEDS);
        regItems(wheat,    Items.CARROT);
        regItems(wheat,    Items.POTATO,     Items.POISONOUS_POTATO);
        regItems(wheat,    Items.BEETROOT,   Items.BEETROOT_SEEDS);
        regItems(warmLate, Items.MELON_SLICE, Items.MELON_SEEDS);
        regItems(warm,     Items.PUMPKIN,    Items.PUMPKIN_SEEDS);
        regItems(wheat,    Items.SUGAR_CANE);
        regItems(cool,     Items.SWEET_BERRIES);

        // ── Tree saplings (SaplingBlock mixin) ──
        reg(Blocks.OAK_SAPLING,      0.5f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.5f, 0.0f,0.0f,0.0f);
        reg(Blocks.BIRCH_SAPLING,    0.5f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.5f, 0.0f,0.0f,0.0f);
        reg(Blocks.DARK_OAK_SAPLING, 0.5f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.5f, 0.0f,0.0f,0.0f);
        // Spruce: cold-hardy, can grow even in light winters
        reg(Blocks.SPRUCE_SAPLING,   1.0f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,1.0f,0.5f, 0.25f,0.0f,0.25f);
        reg(Blocks.CHERRY_SAPLING,   0.5f,1.0f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);
        tropical(Blocks.JUNGLE_SAPLING,      0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 1.0f,0.5f,0.0f, 0.0f,0.0f,0.0f);
        tropical(Blocks.ACACIA_SAPLING,      0.0f,0.5f,1.0f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);
        tropical(Blocks.MANGROVE_PROPAGULE,  0.0f,0.0f,0.5f, 1.0f,1.0f,1.0f, 0.5f,0.0f,0.0f, 0.0f,0.0f,0.0f);

        // ── Humid crops: grow at half speed in arid biomes (downfall < 0.3) ──
        // Rice and cotton need consistently moist soil; cassava and coffee also prefer humidity.
        humid(ModBlocks.RICE_CROP);
        humid(ModBlocks.RICE_CROP_PANICLES);
        humid(ModBlocks.COTTON_CROP);
        humid(ModBlocks.CASSAVA_CROP);
        humid(ModBlocks.COFFEE_CROP);
    }

    private static void reg(Block block, float... mods) {
        REGISTRY.put(block, mods);
    }

    private static void tropical(Block block, float... mods) {
        REGISTRY.put(block, mods);
        TROPICAL.add(block);
    }

    private static void humid(Block block) {
        HUMID_CROPS.add(block);
    }

    private static void regItems(float[] mods, Item... items) {
        for (Item item : items) ITEM_REGISTRY.put(item, mods);
    }

    /** Raw modifiers array (12 entries) for tooltip display. Null if unregistered. */
    public static float[] getRawModifiers(Block block) {
        return REGISTRY.get(block);
    }

    /**
     * Returns the seasonal modifier for a traded crop item (0.0–1.0), or -1 if not a seasonal item.
     * Used by the villager pricing mixin to adjust trade prices.
     */
    public static float getItemModifier(Item item, int periodIndex) {
        float[] mods = ITEM_REGISTRY.get(item);
        if (mods == null) return -1f;
        return mods[periodIndex];
    }

    public static boolean isTropical(Block block) {
        return TROPICAL.contains(block);
    }

    /**
     * Returns the growth modifier for the given block in the current world season.
     * 1.0 = full speed, 0.5 = half speed, 0.25 = quarter, 0.0 = blocked.
     * Unregistered blocks always return 1.0.
     */
    public static float getModifier(Block block, ServerWorld world, BlockPos pos) {
        float[] mods = REGISTRY.get(block);
        if (mods == null) return 1.0f;

        if (TROPICAL.contains(block)) {
            if (SeasonManager.isHotBiome(world, pos))    return 1.0f;
            if (SeasonManager.isFrozenBiome(world, pos)) return 0.0f;
        }

        float base = mods[SeasonManager.getCurrentPeriodIndex(world)];

        // Humidity penalty: crops that need moist soil grow at half speed in arid biomes.
        // Arid = temp >= 1.0 (desert, badlands, savanna) — same threshold as isHotBiome().
        if (base > 0 && HUMID_CROPS.contains(block)) {
            if (world.getBiome(pos).value().getTemperature() >= 1.0f) base *= 0.5f;
        }

        return base;
    }
}

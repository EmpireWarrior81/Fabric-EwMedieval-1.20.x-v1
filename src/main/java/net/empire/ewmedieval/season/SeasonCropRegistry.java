package net.empire.ewmedieval.season;

import net.empire.ewmedieval.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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

    private static final Map<Block, float[]> REGISTRY = new HashMap<>();
    private static final Set<Block> TROPICAL = new HashSet<>();

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
    }

    private static void reg(Block block, float... mods) {
        REGISTRY.put(block, mods);
    }

    private static void tropical(Block block, float... mods) {
        REGISTRY.put(block, mods);
        TROPICAL.add(block);
    }

    /** Raw modifiers array (12 entries) for tooltip display. Null if unregistered. */
    public static float[] getRawModifiers(Block block) {
        return REGISTRY.get(block);
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

        return mods[SeasonManager.getCurrentPeriodIndex(world)];
    }
}

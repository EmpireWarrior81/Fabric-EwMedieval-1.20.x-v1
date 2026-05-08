package net.empire.ewmedieval.season;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public final class SeasonColors {

    // Max blend for temperate biomes. Scales down toward tropical ones.
    private static final float BLEND_MAX = 0.40f;
    // Minimum blend for fully tropical/desert biomes (temp >= 1.0) — just a whisper of change
    private static final float BLEND_MIN = 0.05f;

    // Grass tint target per period (0 = Early Spring → 11 = Late Winter)
    private static final int[] GRASS_TARGETS = {
        0x9DB87A, // 0  Early Spring  — pale yellow-green
        0x79C455, // 1  Mid Spring    — fresh bright green
        0x60B83C, // 2  Late Spring   — rich green
        0x52AF2C, // 3  Early Summer  — deep saturated green
        0x4AA422, // 4  Mid Summer    — deep green
        0x68AC2A, // 5  Late Summer   — slightly warm/dry
        0x98B83C, // 6  Early Autumn  — yellow-green
        0xB8A030, // 7  Mid Autumn    — golden-brown
        0xA07828, // 8  Late Autumn   — pale brown
        0x8A9068, // 9  Early Winter  — gray-green
        0x828888, // 10 Mid Winter    — pale gray
        0x8A9070, // 11 Late Winter   — gray-green returning
    };

    // Foliage tint target for biome-blended leaves (oak, jungle, acacia, dark oak, etc.)
    private static final int[] FOLIAGE_TARGETS = {
        0xA8C878, // 0  Early Spring  — light yellow-green
        0x72C050, // 1  Mid Spring    — bright green
        0x52A830, // 2  Late Spring   — deep green
        0x449E24, // 3  Early Summer  — deep green
        0x3A9020, // 4  Mid Summer    — dark green
        0x609A28, // 5  Late Summer   — warm green
        0xC0A024, // 6  Early Autumn  — yellow-orange
        0xC05820, // 7  Mid Autumn    — orange-red
        0x8C3018, // 8  Late Autumn   — deep red/brown
        0x7A7050, // 9  Early Winter  — gray-brown
        0x787068, // 10 Mid Winter    — pale desaturated brown
        0x7A7258, // 11 Late Winter   — slightly warming
    };

    // Birch — brilliant yellow/orange fall colors, pale fresh spring
    private static final int[] BIRCH_COLORS = {
        0x94C468, // 0  Early Spring  — pale fresh green (emerging leaves)
        0x88C060, // 1  Mid Spring    — bright fresh green
        0x82B858, // 2  Late Spring   — rich green
        0x80A755, // 3  Early Summer  — standard vanilla (peak growth)
        0x7CA050, // 4  Mid Summer    — slightly deeper
        0x8CAA48, // 5  Late Summer   — first hints of yellow
        0xC8B030, // 6  Early Autumn  — golden yellow
        0xD07818, // 7  Mid Autumn    — deep orange-gold
        0xA83820, // 8  Late Autumn   — orange-red
        0x7A7855, // 9  Early Winter  — dried/sparse
        0x788070, // 10 Mid Winter    — bare gray-beige
        0x7A7E60, // 11 Late Winter   — very slightly returning
    };

    // Spruce — evergreen, subtle seasonal shift only
    private static final int[] SPRUCE_COLORS = {
        0x609858, // 0  Early Spring  — slight yellow-green tinge
        0x649E5E, // 1  Mid Spring    — brightening
        0x619961, // 2  Late Spring   — standard vanilla (fresh needles)
        0x5C9860, // 3  Early Summer  — peak dark green
        0x589060, // 4  Mid Summer    — darkest
        0x5A9462, // 5  Late Summer   — slight cooling
        0x608A58, // 6  Early Autumn  — slight olive
        0x628060, // 7  Mid Autumn    — cooler olive
        0x5E8260, // 8  Late Autumn   — dark cool green
        0x568060, // 9  Early Winter  — darker, blue-green tinge
        0x547A5E, // 10 Mid Winter    — darkest, coldest
        0x568260, // 11 Late Winter   — very slightly brightening
    };

    // Cached so the division isn't redone for every block during a render frame
    private static int  cachedPeriod    = 1;
    private static long lastCheckedTime = -1L;

    // Tracks the last period we fired a reload for; -1 means uninitialised
    private static int lastFiredPeriod = -1;

    // -------------------------------------------------------------------------

    public static int blendGrass(int biomeColor, float biomeTemp) {
        return blend(biomeColor, GRASS_TARGETS[getPeriodIndex()], blendStrength(biomeTemp));
    }

    public static int blendFoliage(int biomeColor, float biomeTemp) {
        return blend(biomeColor, FOLIAGE_TARGETS[getPeriodIndex()], blendStrength(biomeTemp));
    }

    public static int getBirchColor() {
        return BIRCH_COLORS[getPeriodIndex()];
    }

    public static int getSpruceColor() {
        return SPRUCE_COLORS[getPeriodIndex()];
    }

    /**
     * Called once per client tick. Returns true the first time the season period
     * changes so the caller can trigger a chunk re-render.
     * Never returns true on the very first call (initialises silently instead).
     */
    public static boolean checkPeriodChanged() {
        int current = getPeriodIndex();
        if (lastFiredPeriod == -1) {
            lastFiredPeriod = current;
            return false;
        }
        if (current != lastFiredPeriod) {
            lastFiredPeriod = current;
            return true;
        }
        return false;
    }

    // -------------------------------------------------------------------------

    private static int getPeriodIndex() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null) {
            // World unloaded — reset so we re-initialise properly next load
            lastCheckedTime = -1L;
            lastFiredPeriod = -1;
            return cachedPeriod;
        }
        long time = mc.world.getTimeOfDay();
        if (time != lastCheckedTime) {
            lastCheckedTime = time;
            long totalDays = time / 24000L;
            cachedPeriod = (int) ((totalDays % SeasonPeriod.TOTAL_YEAR_DAYS) / SeasonPeriod.DAYS_PER_PERIOD);
        }
        return cachedPeriod;
    }

    /**
     * Scales blend strength by biome temperature.
     * Temperate (≤ 0.85): full BLEND_MAX.
     * Tropical/desert (≥ 1.0): BLEND_MIN (barely noticeable).
     * Smooth lerp in between — jungle (~0.95) gets roughly half effect.
     *
     * Vanilla biome temps for reference:
     *   Taiga 0.25 | Forest 0.7 | Plains 0.8 | Birch Forest 0.6
     *   Jungle 0.95 | Savanna 1.2 | Desert 2.0 | Badlands 2.0
     */
    private static float blendStrength(float temp) {
        if (temp >= 1.0f) return BLEND_MIN;
        if (temp <= 0.85f) return BLEND_MAX;
        float t = (temp - 0.85f) / 0.15f; // 0→1 as temp goes 0.85→1.0
        return BLEND_MAX + (BLEND_MIN - BLEND_MAX) * t;
    }

    private static int blend(int base, int target, float strength) {
        int br = (base   >> 16) & 0xFF;
        int bg = (base   >>  8) & 0xFF;
        int bb =  base          & 0xFF;
        int tr = (target >> 16) & 0xFF;
        int tg = (target >>  8) & 0xFF;
        int tb =  target        & 0xFF;
        int r  = (int)(br + (tr - br) * strength);
        int g  = (int)(bg + (tg - bg) * strength);
        int b  = (int)(bb + (tb - bb) * strength);
        return (r << 16) | (g << 8) | b;
    }

    private SeasonColors() {}
}

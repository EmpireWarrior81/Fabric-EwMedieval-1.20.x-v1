package net.empire.ewmedieval.season;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.level.ServerWorldProperties;

import java.util.WeakHashMap;

public final class SeasonManager {

    // Per-world accumulator for fractional game-tick advancement.
    private static final WeakHashMap<ServerWorld, Double>  accumulators  = new WeakHashMap<>();
    private static final WeakHashMap<ServerWorld, Integer> weatherTicks  = new WeakHashMap<>();

    /**
     * Current season period — updated every server tick.
     * Also written client-side from EwMedievalClient so biome mixins work on both sides.
     */
    public static volatile SeasonPeriod currentPeriod = SeasonPeriod.EARLY_SPRING;

    public static void init() {
        SeasonConfig.load();

        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (!world.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)) return;

            // Vanilla's tickTime() already advanced timeOfDay by +1. Undo that and apply our rate.
            long absTime = world.getTimeOfDay();  // already +1 from vanilla tick
            long base    = absTime - 1L;
            long dayPos  = base % 24000L;

            SeasonPeriod period = fromAbsoluteTime(base);
            currentPeriod = period;

            double rate = dayPos < 12000L
                ? SeasonConfig.getDayRate(period.ordinal())
                : SeasonConfig.getNightRate(period.ordinal());

            double acc = accumulators.getOrDefault(world, 0.0) + rate;
            long toAdvance = (long) acc;
            accumulators.put(world, acc - toAdvance);
            world.setTimeOfDay(base + toAdvance);

            // Weather control — runs every 1200 ticks (~1 real minute)
            int wTick = weatherTicks.getOrDefault(world, 0) + 1;
            weatherTicks.put(world, wTick);
            if (wTick % 1200 == 0) {
                applyWeather(world, period);
            }
        });
    }

    /**
     * Adjusts in-game weather to match the season's precipitation level.
     * DRY  — cuts any active rain short so clear weather dominates.
     * WET  — occasionally forces rain to start if the sky is clear.
     * NORMAL — no interference; vanilla weather runs freely.
     */
    private static void applyWeather(ServerWorld world, SeasonPeriod period) {
        PrecipitationLevel prec = period.precipitation;
        ServerWorldProperties props = (ServerWorldProperties) world.getLevelProperties();

        if (prec == PrecipitationLevel.DRY) {
            if (world.isRaining()) {
                // Truncate the rain — it will stop naturally within 30 seconds.
                props.setRainTime(600);
            }
        } else if (prec == PrecipitationLevel.WET) {
            if (!world.isRaining() && world.getRandom().nextFloat() < 0.15f) {
                // ~15 % chance each minute → forces rain to start roughly every 6–7 minutes.
                props.setClearWeatherTime(0);
                props.setRaining(true);
                props.setRainTime(6000 + world.getRandom().nextInt(12000));
            }
        }
        // NORMAL: vanilla decides
    }

    // Returns the SeasonPeriod for a given absolute in-game tick value.
    public static SeasonPeriod fromAbsoluteTime(long absTime) {
        long totalDays = absTime / 24000L;
        int periodIndex = (int) ((totalDays % SeasonPeriod.TOTAL_YEAR_DAYS) / SeasonPeriod.DAYS_PER_PERIOD);
        return SeasonPeriod.values()[periodIndex];
    }

    public static SeasonPeriod getCurrentPeriod(ServerWorld world) {
        return fromAbsoluteTime(world.getTimeOfDay());
    }

    public static int getCurrentPeriodIndex(ServerWorld world) {
        long totalDays = world.getTimeOfDay() / 24000L;
        return (int) ((totalDays % SeasonPeriod.TOTAL_YEAR_DAYS) / SeasonPeriod.DAYS_PER_PERIOD);
    }

    // Biome temperature helpers used by the crop registry.
    public static boolean isHotBiome(ServerWorld world, BlockPos pos) {
        return world.getBiome(pos).value().getTemperature() >= 1.0f;
    }

    public static boolean isFrozenBiome(ServerWorld world, BlockPos pos) {
        return world.getBiome(pos).value().getTemperature() < 0.15f;
    }
}

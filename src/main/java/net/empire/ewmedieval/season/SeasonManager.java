package net.empire.ewmedieval.season;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;

import java.util.WeakHashMap;

public final class SeasonManager {

    // Per-world accumulator for fractional game-tick advancement.
    private static final WeakHashMap<ServerWorld, Double> accumulators = new WeakHashMap<>();

    public static void init() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (!world.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)) return;

            // Vanilla's tickTime() already advanced timeOfDay by +1. Undo that and apply our rate.
            long absTime = world.getTimeOfDay();  // day-cycle ticks (already +1, this is what setTimeOfDay controls)
            long base    = absTime - 1L;           // rewind to pre-tick value
            long dayPos  = base % 24000L;          // position within the day/night cycle

            SeasonPeriod period = fromAbsoluteTime(base);
            double rate = dayPos < 12000L ? period.dayRate : period.nightRate;

            double acc = accumulators.getOrDefault(world, 0.0) + rate;
            long toAdvance = (long) acc;
            accumulators.put(world, acc - toAdvance);

            world.setTimeOfDay(base + toAdvance);
        });
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

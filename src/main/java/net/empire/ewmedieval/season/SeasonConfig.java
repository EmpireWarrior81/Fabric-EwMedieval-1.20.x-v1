package net.empire.ewmedieval.season;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class SeasonConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("ewmedieval_seasons.json");

    // Defaults match SeasonPeriod enum order (index = ordinal)
    private static final double[] DEFAULT_DAY   = { 55, 62, 68, 75, 80, 75, 68, 62, 55, 48, 45, 48 };
    private static final double[] DEFAULT_NIGHT = { 65, 58, 52, 45, 40, 45, 52, 58, 65, 72, 75, 72 };

    static double[] dayMinutes   = DEFAULT_DAY.clone();
    static double[] nightMinutes = DEFAULT_NIGHT.clone();
    private static boolean showSeasonTooltips = true;

    private static final String[] KEYS = {
        "earlySpring", "midSpring",  "lateSpring",
        "earlySummer", "midSummer",  "lateSummer",
        "earlyAutumn", "midAutumn",  "lateAutumn",
        "earlyWinter", "midWinter",  "lateWinter"
    };

    public static void load() {
        if (!Files.exists(PATH)) { save(); return; }
        try {
            JsonObject root = GSON.fromJson(Files.readString(PATH), JsonObject.class);
            if (root == null) return;

            if (root.has("showSeasonTooltips")) {
                showSeasonTooltips = root.get("showSeasonTooltips").getAsBoolean();
            }

            if (root.has("dayNightCycle")) {
                JsonObject cycle = root.getAsJsonObject("dayNightCycle");
                for (int i = 0; i < 12; i++) {
                    if (!cycle.has(KEYS[i])) continue;
                    JsonObject p = cycle.getAsJsonObject(KEYS[i]);
                    if (p.has("dayMinutes"))   dayMinutes[i]   = Math.max(1, p.get("dayMinutes").getAsDouble());
                    if (p.has("nightMinutes")) nightMinutes[i] = Math.max(1, p.get("nightMinutes").getAsDouble());
                }
            }
        } catch (IOException ignored) {}
        save();
    }

    public static void save() {
        JsonObject root = new JsonObject();

        root.addProperty("_info",
            "EwMedieval Season Config - all changes take effect on restart.");

        root.addProperty("showSeasonTooltips", showSeasonTooltips);
        root.addProperty("_tooltipInfo",
            "Show growing-season dots on seeds and saplings. Hold Shift for the full breakdown.");

        JsonObject cycle = new JsonObject();
        cycle.addProperty("_info",
            "Day and night length in real minutes per season period. " +
            "Day + Night should ideally sum to 120 for a consistent 2-hour cycle. Minimum: 1.");
        cycle.addProperty("_periods",
            "Each season has three periods: Early / Mid / Late.");

        for (int i = 0; i < 12; i++) {
            JsonObject p = new JsonObject();
            p.addProperty("dayMinutes",   dayMinutes[i]);
            p.addProperty("nightMinutes", nightMinutes[i]);
            cycle.add(KEYS[i], p);
        }
        root.add("dayNightCycle", cycle);

        try { Files.writeString(PATH, GSON.toJson(root)); } catch (IOException ignored) {}
    }

    public static boolean isTooltipEnabled() { return showSeasonTooltips; }

    public static void setTooltipEnabled(boolean value) {
        showSeasonTooltips = value;
        save();
    }

    public static double getDayRate(int periodIndex) {
        return 10.0 / dayMinutes[periodIndex];
    }

    public static double getNightRate(int periodIndex) {
        return 10.0 / nightMinutes[periodIndex];
    }
}

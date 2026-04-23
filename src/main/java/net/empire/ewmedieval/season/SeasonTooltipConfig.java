package net.empire.ewmedieval.season;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class SeasonTooltipConfig {

    private static final Gson GSON = new Gson();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("ewmedieval_season.json");

    private static boolean enabled = true;

    public static void load() {
        if (!Files.exists(PATH)) { save(); return; }
        try {
            JsonObject obj = GSON.fromJson(Files.readString(PATH), JsonObject.class);
            if (obj != null && obj.has("showSeasonTooltips")) {
                enabled = obj.get("showSeasonTooltips").getAsBoolean();
            }
        } catch (IOException ignored) {}
    }

    public static void toggle() {
        enabled = !enabled;
        save();
    }

    public static boolean isEnabled() { return enabled; }

    private static void save() {
        JsonObject obj = new JsonObject();
        obj.addProperty("showSeasonTooltips", enabled);
        try { Files.writeString(PATH, GSON.toJson(obj)); } catch (IOException ignored) {}
    }
}

package net.empire.ewmedieval.nutrition;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.empire.ewmedieval.EwMedieval;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class NutritionFoodLoader implements SimpleSynchronousResourceReloadListener {

    private static final Identifier ID = new Identifier(EwMedieval.MOD_ID, "nutrition_loader");
    private static final Map<Item, NutritionFoodValues> FOOD_DATA = new HashMap<>();

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public void reload(ResourceManager manager) {
        FOOD_DATA.clear();
        manager.findResources("nutrition", path -> path.getPath().endsWith(".json"))
            .forEach((id, resource) -> {
                try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    parseAndStore(json);
                } catch (Exception e) {
                    EwMedieval.LOGGER.error("Failed to load nutrition data from {}: {}", id, e.getMessage());
                }
            });
        EwMedieval.LOGGER.info("Loaded nutrition data for {} food items.", FOOD_DATA.size());
    }

    private static void parseAndStore(JsonObject json) {
        if (!json.has("item")) return;
        String itemId = json.get("item").getAsString();
        Item item = Registries.ITEM.get(new Identifier(itemId));
        if (item == Items.AIR) {
            EwMedieval.LOGGER.warn("Unknown item in nutrition data: {}", itemId);
            return;
        }
        float carbs    = json.has("carbs")    ? json.get("carbs").getAsFloat()    : 0f;
        float protein  = json.has("protein")  ? json.get("protein").getAsFloat()  : 0f;
        float fat      = json.has("fat")      ? json.get("fat").getAsFloat()      : 0f;
        float vitamins = json.has("vitamins") ? json.get("vitamins").getAsFloat() : 0f;
        float minerals = json.has("minerals") ? json.get("minerals").getAsFloat() : 0f;
        FOOD_DATA.put(item, new NutritionFoodValues(carbs, protein, fat, vitamins, minerals));
    }

    public static @Nullable NutritionFoodValues get(Item item) {
        return FOOD_DATA.get(item);
    }
}
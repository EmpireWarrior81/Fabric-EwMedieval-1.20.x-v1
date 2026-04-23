package net.empire.ewmedieval.nutrition;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class ClientNutritionData {
    public static final NutritionData INSTANCE = new NutritionData();

    public static void update(NutritionData incoming) {
        for (int i = 0; i < 5; i++) {
            INSTANCE.set(i, incoming.get(i));
        }
    }
}
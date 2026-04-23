package net.empire.ewmedieval.nutrition;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.network.NutritionSyncPacket;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ModNutrition {

    public static NutritionData get(ServerPlayerEntity player) {
        return ((NutritionHolder) player).ewmedieval$getNutrition();
    }

    public static void init() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA)
            .registerReloadListener(new NutritionFoodLoader());

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                tickPlayer(player);
            }
        });

        EwMedieval.LOGGER.info("Nutrition system initialised.");
    }

    private static void tickPlayer(ServerPlayerEntity player) {
        if (player.isCreative()) return;
        if (player.age % NutritionConstants.DECAY_INTERVAL != 0) return;

        NutritionData data = get(player);
        data.decay();
        NutritionEffects.apply(player, data);
        NutritionSyncPacket.send(player, data);
    }
}

/*
package net.empire.ewmedieval.thirst;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.network.ThirstSyncPacket;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ModThirst {

    private static final float BASE_RATE    = 0.002f;
    private static final float SPRINT_BONUS = 0.004f;
    private static final float SWIM_BONUS   = 0.003f;

    public static ThirstManager get(ServerPlayerEntity player) {
        return ((ThirstHolder) player).ewmedieval$getThirstManager();
    }

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                tickPlayer(player);
            }
        });
        EwMedieval.LOGGER.info("Thirst system initialised.");
    }

    private static void tickPlayer(ServerPlayerEntity player) {
        if (player.isCreative() || player.isSpectator()) return;

        ThirstManager tm = get(player);

        float rate = BASE_RATE;
        if (player.isSprinting()) rate += SPRINT_BONUS;
        if (player.isSwimming())  rate += SWIM_BONUS;
        tm.addDehydration(rate);

        tm.update(player);

        if (player.age % 20 == 0) {
            ThirstSyncPacket.send(player, tm.getThirstLevel());
        }
    }
}
*/

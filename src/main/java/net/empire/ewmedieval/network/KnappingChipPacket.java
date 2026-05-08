package net.empire.ewmedieval.network;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.gui.knapping.KnappingScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public final class KnappingChipPacket {

    public static final Identifier ID = new Identifier(EwMedieval.MOD_ID, "knapping_chip");

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, handler, buf, responseSender) -> {
            int index = buf.readVarInt();
            if (index < 0 || index >= 9) return;
            server.execute(() -> {
                if (player.currentScreenHandler instanceof KnappingScreenHandler menu) {
                    menu.setChip(index);
                }
            });
        });
    }

    private KnappingChipPacket() {}
}

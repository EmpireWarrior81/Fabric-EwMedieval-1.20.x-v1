/*
package net.empire.ewmedieval.network;

import net.empire.ewmedieval.EwMedieval;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class ThirstSyncPacket {

    public static final Identifier ID = new Identifier(EwMedieval.MOD_ID, "thirst_sync");

    public static void send(ServerPlayerEntity player, int thirstLevel) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(thirstLevel);
        ServerPlayNetworking.send(player, ID, buf);
    }

    public static int read(PacketByteBuf buf) {
        return buf.readInt();
    }
}
*/

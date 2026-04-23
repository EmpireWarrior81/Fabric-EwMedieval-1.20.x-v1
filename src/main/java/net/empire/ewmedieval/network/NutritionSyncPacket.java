package net.empire.ewmedieval.network;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.nutrition.NutritionData;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class NutritionSyncPacket {

    public static final Identifier ID = new Identifier(EwMedieval.MOD_ID, "nutrition_sync");

    public static void send(ServerPlayerEntity player, NutritionData data) {
        PacketByteBuf buf = PacketByteBufs.create();
        for (int i = 0; i < 5; i++) {
            buf.writeFloat(data.get(i));
        }
        ServerPlayNetworking.send(player, ID, buf);
    }

    public static NutritionData read(PacketByteBuf buf) {
        NutritionData data = new NutritionData();
        for (int i = 0; i < 5; i++) {
            data.set(i, buf.readFloat());
        }
        return data;
    }
}
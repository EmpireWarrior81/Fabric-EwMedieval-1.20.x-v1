package net.empire.ewmedieval.network;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.forging.SmithingMinigameState;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

/** Server → Client: start the smithing minigame with given parameters. */
public final class AnvilStartMinigamePacket {

    public static final Identifier ID = new Identifier(EwMedieval.MOD_ID, "anvil_start_minigame");

    private AnvilStartMinigamePacket() {}

    /** Send from server to a specific player. */
    public static void send(ServerPlayerEntity player, BlockPos pos, int totalHits,
                            float initialSpeed, float goodZoneSize, float perfectZoneSize,
                            float shrinkFactor, float speedIncrease, float maxSpeed) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(pos);
        buf.writeInt(totalHits);
        buf.writeFloat(initialSpeed);
        buf.writeFloat(goodZoneSize);
        buf.writeFloat(perfectZoneSize);
        buf.writeFloat(shrinkFactor);
        buf.writeFloat(speedIncrease);
        buf.writeFloat(maxSpeed);
        ServerPlayNetworking.send(player, ID, buf);
    }

    /** Register client-side receiver. */
    public static void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(ID, (client, handler, buf, responseSender) -> {
            BlockPos pos      = buf.readBlockPos();
            int hits          = buf.readInt();
            float speed       = buf.readFloat();
            float goodSize    = buf.readFloat();
            float perfectSize = buf.readFloat();
            float shrink      = buf.readFloat();
            float speedInc    = buf.readFloat();
            float maxSpeed    = buf.readFloat();
            client.execute(() ->
                SmithingMinigameState.start(pos, hits, speed, goodSize, perfectSize, shrink, speedInc, maxSpeed));
        });
    }
}

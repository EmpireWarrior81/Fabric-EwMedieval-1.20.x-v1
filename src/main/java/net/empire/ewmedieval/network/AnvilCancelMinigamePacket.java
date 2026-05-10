package net.empire.ewmedieval.network;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.block.entity.custom.SmithingAnvilBlockEntity;
import net.empire.ewmedieval.forging.GrindingHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

/** Client → Server: player cancelled the active smithing minigame. */
public final class AnvilCancelMinigamePacket {

    public static final Identifier ID = new Identifier(EwMedieval.MOD_ID, "anvil_cancel");

    private AnvilCancelMinigamePacket() {}

    public static void send(BlockPos pos) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(pos);
        ClientPlayNetworking.send(ID, buf);
    }

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            server.execute(() -> {
                if (player.getWorld().getBlockEntity(pos) instanceof SmithingAnvilBlockEntity be) {
                    if (player.getUuid().equals(be.getCurrentPlayer())) {
                        be.cancelMinigame();
                    }
                } else {
                    GrindingHandler.cancelSession(player.getUuid());
                }
            });
        });
    }
}

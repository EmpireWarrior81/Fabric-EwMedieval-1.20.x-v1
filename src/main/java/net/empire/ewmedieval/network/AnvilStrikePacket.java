package net.empire.ewmedieval.network;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.block.entity.custom.SmithingAnvilBlockEntity;
import net.empire.ewmedieval.forging.GrindingHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

/** Client → Server: player struck during the minigame. */
public final class AnvilStrikePacket {

    public static final Identifier ID = new Identifier(EwMedieval.MOD_ID, "anvil_strike");

    private AnvilStrikePacket() {}

    /** Send from client. hitType: 0=miss, 1=good, 2=perfect. */
    public static void send(BlockPos pos, int hitType) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(pos);
        buf.writeByte(hitType);
        ClientPlayNetworking.send(ID, buf);
    }

    /** Register server-side receiver. */
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            int hitType  = buf.readByte();
            if (hitType < 0 || hitType > 2) return;
            server.execute(() -> {
                if (player.getWorld().getBlockEntity(pos) instanceof SmithingAnvilBlockEntity be) {
                    be.recordHit(hitType, player);
                } else if (player.getWorld().getBlockState(pos).isOf(Blocks.GRINDSTONE)) {
                    GrindingHandler.recordGrindHit(hitType, (ServerPlayerEntity) player, pos);
                }
            });
        });
    }
}

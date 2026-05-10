package net.empire.ewmedieval.network;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.block.entity.custom.SmithingAnvilBlockEntity;
import net.empire.ewmedieval.gui.smithinganvil.SmithingAnvilScreenHandler;
import net.empire.ewmedieval.recipe.ForgingRecipe;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

/** Client → Server: player pressed the Forge button. */
public final class AnvilForgeRequestPacket {

    public static final Identifier ID = new Identifier(EwMedieval.MOD_ID, "anvil_forge_request");

    private AnvilForgeRequestPacket() {}

    /** Send from client when Forge button is pressed. */
    public static void send() {
        ClientPlayNetworking.send(ID, PacketByteBufs.empty());
    }

    /** Register server-side receiver. */
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                if (!(player.currentScreenHandler instanceof SmithingAnvilScreenHandler menu)) return;
                SmithingAnvilBlockEntity be = menu.blockEntity;
                if (be == null) return;

                handleForgeRequest(player, be);
            });
        });
    }

    private static void handleForgeRequest(ServerPlayerEntity player, SmithingAnvilBlockEntity be) {
        // If a simple forging is already in progress, just record another perfect hit
        if (be.isMinigameActive() && be.isSimpleForging()) {
            be.recordHit(SmithingAnvilBlockEntity.HIT_PERFECT, player);
            return;
        }

        // Don't interrupt an active complex minigame
        if (be.isMinigameActive()) return;

        ForgingRecipe recipe = be.startMinigame(player);
        if (recipe == null) return;

        if (recipe.isSimple()) {
            // Simple recipe: first hammer press already starts + records hit 1
            be.recordHit(SmithingAnvilBlockEntity.HIT_PERFECT, player);
            // No minigame packet — client uses the property-delegate hit counter instead
        } else {
            // Complex recipe: start the timing-zone minigame on the client
            int hits = recipe.getHits();
            float initialSpeed    = 1.2f + hits * 0.1f;
            float goodZoneSize    = Math.max(20f, 60f - hits * 3f);
            float perfectZoneSize = Math.max(8f,  30f - hits * 2f);

            AnvilStartMinigamePacket.send(player, be.getPos(), hits, initialSpeed,
                    goodZoneSize, perfectZoneSize, 0.90f, 0.20f, 5.0f);
        }
    }
}

/*
package net.empire.ewmedieval.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.effect.ModEffects;
import net.empire.ewmedieval.thirst.ClientThirstData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ThirstHudOverlay {

    private static final Identifier TEXTURE = new Identifier("ewmedieval", "textures/gui/thirst.png");

    public static void render(DrawContext context, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options.hudHidden || !mc.interactionManager.hasStatusBars()) return;

        PlayerEntity player = mc.player;
        if (player == null || player.isCreative() || player.isSpectator()) return;
        if (player.getVehicle() instanceof LivingEntity) return;

        int scaledWidth  = mc.getWindow().getScaledWidth();
        int scaledHeight = mc.getWindow().getScaledHeight();

        int thirst = ClientThirstData.getThirstLevel();
        int ticks  = mc.inGameHud.getTicks();

        int top   = scaledHeight - 49;
        int right = scaledWidth / 2 + 91;

        boolean hasThirstEffect = player.hasStatusEffect(ModEffects.THIRST_EFFECT);

        RenderSystem.enableBlend();

        for (int i = 0; i < 10; i++) {
            int x = right - i * 8 - 9;
            int y = top;

            if (thirst <= 4 && ticks % (thirst * 3 + 1) == 0) {
                y = top + (mc.world.getRandom().nextInt(3) - 1);
            }

            context.drawTexture(TEXTURE, x, y, 0, 0, 9, 9);

            int iconX = hasThirstEffect ? 36 : 0;

            if (i * 2 + 1 < thirst) {
                context.drawTexture(TEXTURE, x, y, iconX, 9, 9, 9);
            } else if (i * 2 + 1 == thirst) {
                context.drawTexture(TEXTURE, x, y, iconX + 9, 9, 9, 9);
            }

            if (player.getFrozenTicks() > 0) {
                RenderSystem.setShaderColor(1f, 1f, 1f, player.getFreezingScale());
                if (i * 2 + 1 < thirst) {
                    context.drawTexture(TEXTURE, x, y, 54, 9, 9, 9);
                } else if (i * 2 + 1 == thirst) {
                    context.drawTexture(TEXTURE, x, y, 63, 9, 9, 9);
                }
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            }
        }

        RenderSystem.disableBlend();
    }
}
*/

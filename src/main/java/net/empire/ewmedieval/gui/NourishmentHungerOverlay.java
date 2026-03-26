package net.empire.ewmedieval.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

import java.util.Random;

// Credits to squeek502 (AppleSkin) for the implementation reference!
public class NourishmentHungerOverlay {

    public static int foodIconsOffset;
    private static final Identifier MOD_ICONS_TEXTURE =
            new Identifier("ewmedieval", "textures/gui/fd_icons.png");

    // Register this in your ClientModInitializer:
    // HudRenderCallback.EVENT.register((drawContext, tickDelta) ->
    //         NourishmentHungerOverlay.onRenderGuiOverlayPost(drawContext, tickDelta));
    public static void onRenderGuiOverlayPost(DrawContext context, float partialTicks) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (!mc.options.hudHidden && mc.interactionManager.hasStatusBars()) {
            renderNourishmentOverlay(mc, context);
        }
    }

    public static void renderNourishmentOverlay(MinecraftClient mc, DrawContext context) {
        // Don't render while mounted on a living entity
        boolean isMounted = mc.player != null
                && mc.player.getVehicle() instanceof LivingEntity;
        if (isMounted) return;

        foodIconsOffset = 49;
        PlayerEntity player = mc.player;
        if (player == null) return;

        HungerManager stats = player.getHungerManager();
        int top = mc.getWindow().getScaledHeight() - foodIconsOffset + 10;
        int left = mc.getWindow().getScaledWidth() / 2 + 91;

        boolean isPlayerHealingWithFood =
                player.getWorld().getGameRules().getBoolean(GameRules.NATURAL_REGENERATION)
                        && player.getHealth() < player.getMaxHealth()
                        && stats.getFoodLevel() >= 18;

        if (player.getStatusEffect(ModEffects.NOURISHMENT) != null) {
            drawNourishmentOverlay(stats, mc, context, left, top, isPlayerHealingWithFood);
        }
    }

    public static void drawNourishmentOverlay(HungerManager stats, MinecraftClient mc,
                                              DrawContext context, int left, int top,
                                              boolean naturalHealing) {
        float saturation = stats.getSaturationLevel();
        int foodLevel = stats.getFoodLevel();
        int ticks = mc.inGameHud.getTicks();
        Random rand = new Random();
        rand.setSeed(ticks * 312871L);

        RenderSystem.enableBlend();

        for (int j = 0; j < 10; ++j) {
            int x = left - j * 8 - 9;
            int y = top;

            // Shake animation when starving
            if (saturation <= 0.0F && ticks % (foodLevel * 3 + 1) == 0) {
                y = top + (rand.nextInt(3) - 1);
            }

            // Background texture
            context.drawTexture(MOD_ICONS_TEXTURE, x, y, 0, 0, 9, 9);

            float effectiveHunger = stats.getFoodLevel() / 2.0F - j;
            int naturalHealingOffset = naturalHealing ? 18 : 0;

            // Gilded hunger icons — full or half
            if (effectiveHunger >= 1)
                context.drawTexture(MOD_ICONS_TEXTURE, x, y, 18 + naturalHealingOffset, 0, 9, 9);
            else if (effectiveHunger >= 0.5F)
                context.drawTexture(MOD_ICONS_TEXTURE, x, y, 9 + naturalHealingOffset, 0, 9, 9);
        }

        RenderSystem.disableBlend();
    }
}

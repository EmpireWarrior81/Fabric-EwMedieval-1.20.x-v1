package net.empire.ewmedieval.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;

import java.util.Random;

public class ComfortHealthOverlay {

    protected static int healthIconsOffset;
    private static final Identifier MOD_ICONS_TEXTURE =
            new Identifier("ewmedieval", "textures/gui/fd_icons.png");

    // Register this in your ClientModInitializer:
    // HudRenderCallback.EVENT.register((drawContext, tickDelta) ->
    //         ComfortHealthOverlay.onRenderGuiOverlayPost(drawContext, tickDelta));
    public static void onRenderGuiOverlayPost(DrawContext context, float partialTicks) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (!mc.options.hudHidden && mc.interactionManager.hasStatusBars()) {
            renderComfortOverlay(mc, context);
        }
    }

    public static void renderComfortOverlay(MinecraftClient mc, DrawContext context) {
        healthIconsOffset = 49;
        PlayerEntity player = mc.player;

        if (player == null) return;

        HungerManager stats = player.getHungerManager();
        int top = mc.getWindow().getScaledHeight() - healthIconsOffset + 10;
        int left = mc.getWindow().getScaledWidth() / 2 - 91;

        boolean isPlayerEligibleForComfort = stats.getSaturationLevel() == 0.0F
                && player.getHealth() < player.getMaxHealth()
                && !player.hasStatusEffect(StatusEffects.REGENERATION);

        if (player.getStatusEffect(ModEffects.COMFORT) != null && isPlayerEligibleForComfort) {
            drawComfortOverlay(player, mc, context, left, top);
        }
    }

    public static void drawComfortOverlay(PlayerEntity player, MinecraftClient mc,
                                          DrawContext context, int left, int top) {
        int ticks = mc.inGameHud.getTicks();
        Random rand = new Random();
        rand.setSeed(ticks * 312871L);

        int health = MathHelper.ceil(player.getHealth());
        float absorb = MathHelper.ceil(player.getAbsorptionAmount());
        EntityAttributeInstance attrMaxHealth = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        float healthMax = (float) attrMaxHealth.getValue();

        int regen = -1;
        if (player.hasStatusEffect(StatusEffects.REGENERATION)) regen = ticks % 25;

        int healthRows = MathHelper.ceil((healthMax + absorb) / 2.0F / 10.0F);
        int rowHeight = Math.max(10 - (healthRows - 2), 3);

        int comfortSheen = ticks % 50;
        int comfortHeartFrame = comfortSheen % 2;
        int[] textureWidth = {5, 9};

        RenderSystem.setShaderTexture(0, MOD_ICONS_TEXTURE);
        RenderSystem.enableBlend();

        int healthMaxSingleRow = MathHelper.ceil(Math.min(healthMax, 20) / 2.0F);
        int leftHeightOffset = (healthRows - 1) * rowHeight;

        for (int i = 0; i < healthMaxSingleRow; ++i) {
            int column = i % 10;
            int x = left + column * 8;
            int y = top + leftHeightOffset;

            if (health <= 4) y += rand.nextInt(2);
            if (i == regen) y -= 2;

            if (column == comfortSheen / 2) {
                context.drawTexture(MOD_ICONS_TEXTURE, x, y, 0, 9,
                        textureWidth[comfortHeartFrame], 9);
            }
            if (column == (comfortSheen / 2) - 1 && comfortHeartFrame == 0) {
                context.drawTexture(MOD_ICONS_TEXTURE, x + 5, y, 5, 9, 4, 9);
            }
        }

        RenderSystem.disableBlend();
    }
}
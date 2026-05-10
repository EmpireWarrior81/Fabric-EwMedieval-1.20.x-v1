package net.empire.ewmedieval.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.forging.SmithingMinigameState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public final class SmithingMinigameOverlay {

    private static final Identifier TEXTURE =
            new Identifier(EwMedieval.MOD_ID, "textures/gui/smithing_anvil_minigame.png");

    private static final int IMAGE_W = 238;
    private static final int IMAGE_H = 37;
    private static final int TEX_W   = 256;
    private static final int TEX_H   = 128;
    private static final int BAR_W   = 220;
    private static final int BAR_H   = 10;

    private SmithingMinigameOverlay() {}

    public static void render(DrawContext ctx, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        int screenW = mc.getWindow().getScaledWidth();
        int screenH = mc.getWindow().getScaledHeight();

        // Popups linger briefly after the minigame ends
        if (!SmithingMinigameState.active) {
            renderPopups(ctx, mc, screenW, screenH);
            return;
        }

        int x = (screenW - IMAGE_W) / 2;
        int y = screenH - IMAGE_H - 40;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        // Main overlay image
        ctx.drawTexture(TEXTURE, x, y, 0, 0, IMAGE_W, IMAGE_H, TEX_W, TEX_H);

        int barX = x + 9;
        int barY = y + 21;

        // Good zone
        int goodStartPx = (int)(BAR_W * SmithingMinigameState.goodZoneStart / 100f);
        int goodEndPx   = (int)(BAR_W * SmithingMinigameState.goodZoneEnd   / 100f);
        if (goodEndPx > goodStartPx) {
            ctx.drawTexture(TEXTURE, barX + goodStartPx, barY, 9, 94,
                    goodEndPx - goodStartPx, BAR_H, TEX_W, TEX_H);
        }

        // Perfect zone
        int perfectStartPx = (int)(BAR_W * SmithingMinigameState.perfectZoneStart / 100f);
        int perfectEndPx   = (int)(BAR_W * SmithingMinigameState.perfectZoneEnd   / 100f);
        if (perfectEndPx > perfectStartPx) {
            ctx.drawTexture(TEXTURE, barX + perfectStartPx, barY, 9, 72,
                    perfectEndPx - perfectStartPx, BAR_H, TEX_W, TEX_H);
        }

        // Progress bar (hits completed / total)
        int done       = SmithingMinigameState.totalHits - SmithingMinigameState.hitsRemaining;
        int progressPx = SmithingMinigameState.totalHits > 0
                ? (int)(222f * done / SmithingMinigameState.totalHits) : 0;
        if (progressPx > 0) {
            ctx.drawTexture(TEXTURE, x + 8, y + 12, 8, 62, progressPx, 5, TEX_W, TEX_H);
        }

        // Arrow
        int arrowX = barX + (int)(BAR_W * SmithingMinigameState.arrowPosition / 100f) - 4;
        ctx.drawTexture(TEXTURE, arrowX, barY - 3, 9, 41, 8, 16, TEX_W, TEX_H);

        RenderSystem.disableBlend();

        renderPopups(ctx, mc, screenW, screenH);
    }

    /** Overgeared-style animated popups: float up, fade out, scale from 115% to 100%. */
    private static void renderPopups(DrawContext ctx, MinecraftClient mc, int screenW, int screenH) {
        if (SmithingMinigameState.popups.isEmpty()) return;

        for (int i = 0; i < SmithingMinigameState.popups.size(); i++) {
            SmithingMinigameState.HitPopup popup = SmithingMinigameState.popups.get(i);
            float progress = popup.age / SmithingMinigameState.HitPopup.MAX_AGE;
            float alpha    = 1f - progress;
            float floatUp  = progress * 12f;
            float scale    = 1f + (1f - progress) * 0.15f; // 115% → 100%

            String text = switch (popup.type) {
                case 2 -> "PERFECT!";
                case 1 -> "GOOD";
                default -> "MISS";
            };
            int baseColor = switch (popup.type) {
                case 2 -> 0xFFAA00; // gold
                case 1 -> 0x55FF55; // green
                default -> 0xFF5555; // red
            };
            int color = ((int)(alpha * 255) << 24) | (baseColor & 0x00FFFFFF);

            int textW   = mc.textRenderer.getWidth(text);
            float yOff  = i * 8f;
            float popX  = screenW / 2f;
            float popY  = screenH / 2f - 40 - floatUp - yOff;

            ctx.getMatrices().push();
            ctx.getMatrices().translate(popX, popY, 0);
            ctx.getMatrices().scale(scale, scale, 1f);
            ctx.drawText(mc.textRenderer, text, -textW / 2, 0, color, true);
            ctx.getMatrices().pop();
        }
    }
}

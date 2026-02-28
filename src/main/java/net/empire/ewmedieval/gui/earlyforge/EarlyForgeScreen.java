package net.empire.ewmedieval.gui.earlyforge;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.EwMedieval;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EarlyForgeScreen extends HandledScreen<EarlyForgeScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(EwMedieval.MOD_ID, "textures/gui/earlyforge_gui.png");

    public EarlyForgeScreen(EarlyForgeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderFuelIndicator(context, x, y);

        renderProgressArrow(context, x, y);
    }

    private void renderFuelIndicator(DrawContext context, int x, int y) {
        if (handler.isBurning()) {
            int fuelHeight = handler.getFuelScaled();
            context.drawTexture(TEXTURE,
                    x + 42, y + 36 + (14 - fuelHeight),
                    176, 14 - fuelHeight,
                    14, fuelHeight);
        }
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        int progress = handler.getScaledProgress(); // max 26 px breed
        if (progress > 0) {
            context.drawTexture(TEXTURE,
                    x + 87, y + 15,
                    176, 14,
                    progress, 17);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}

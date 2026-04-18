package net.empire.ewmedieval.gui.cookingpot;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.EwMedieval;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CookingPotScreen extends HandledScreen<CookingPotScreenHandler> {

    /**
     * GUI texture at:
     *   resources/assets/ewmedieval/textures/gui/cooking_pot.png
     *
     * Layout (256×256 atlas):
     *   0,0    – 176×166 : main background
     *   176,0  – 24×17   : progress arrow sprite (full width, clipped by progress)
     */
    private static final Identifier TEXTURE =
            new Identifier(EwMedieval.MOD_ID, "textures/gui/cooking_pot.png");

    public CookingPotScreen(CookingPotScreenHandler handler,
                             PlayerInventory inventory,
                             Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // Push titles off-screen (the texture already has labels baked in)
        titleY            = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width  - backgroundWidth)  / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // Heat indicator at (47, 55) — UV (176, 0), 17×15px
        if (handler.isCooking()) {
            context.drawTexture(TEXTURE, x + 47, y + 55, 176, 0, 17, 15);
        }

        // Progress arrow at (89, 25) — UV (176, 15), 24×17px max
        int progress = handler.getScaledProgress();
        if (progress > 0) {
            context.drawTexture(TEXTURE, x + 89, y + 25, 176, 15, progress, 17);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
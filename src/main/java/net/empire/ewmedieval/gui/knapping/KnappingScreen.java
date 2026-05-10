package net.empire.ewmedieval.gui.knapping;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.network.KnappingChipPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class KnappingScreen extends HandledScreen<KnappingScreenHandler> {

    private static final Identifier TEXTURE =
            new Identifier(EwMedieval.MOD_ID, "textures/gui/rock_knapping_gui.png");
    private static final Identifier STONE_TILE =
            new Identifier("minecraft", "textures/block/stone.png");
    private static final Identifier FLINT_TILE =
            new Identifier(EwMedieval.MOD_ID, "textures/block/flint_block.png");

    private static final int GRID_ORIGIN_X = 32;
    private static final int GRID_ORIGIN_Y = 19;
    private static final int SLOT_SIZE = 16;

    public KnappingScreen(KnappingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) handleKnapping(mouseX, mouseY);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button == 0) handleKnapping(mouseX, mouseY);
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private void handleKnapping(double mouseX, double mouseY) {
        if (handler.isKnappingFinished()) return;

        for (int i = 0; i < 9; i++) {
            int col = i % 3;
            int row = i / 3;
            int cellX = this.x + GRID_ORIGIN_X + col * SLOT_SIZE;
            int cellY = this.y + GRID_ORIGIN_Y + row * SLOT_SIZE;

            if (mouseX >= cellX && mouseX < cellX + SLOT_SIZE
                    && mouseY >= cellY && mouseY < cellY + SLOT_SIZE
                    && !handler.isChipped(i)) {

                handler.setChip(i); // Client-side instant preview
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeVarInt(i);
                ClientPlayNetworking.send(KnappingChipPacket.ID, buf);
                if (client != null && client.player != null) {
                    client.player.playSound(handler.getKnappingSound(), 1.0f, 1.0f);
                }
                break;
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        int x = this.x;
        int y = this.y;

        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        // Draw material tile on each unchipped cell
        if (!handler.isKnappingFinished() && !handler.isResultCollected()) {
            Identifier tileTex = handler.getInputRock().getItem() == Items.FLINT ? FLINT_TILE : STONE_TILE;
            for (int i = 0; i < 9; i++) {
                if (!handler.isChipped(i)) {
                    int col = i % 3;
                    int row = i / 3;
                    int cellX = x + GRID_ORIGIN_X + col * SLOT_SIZE;
                    int cellY = y + GRID_ORIGIN_Y + row * SLOT_SIZE;
                    context.drawTexture(tileTex, cellX, cellY, 0, 0, SLOT_SIZE, SLOT_SIZE, SLOT_SIZE, SLOT_SIZE);
                }
            }
        }
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 0x404040, false);
        context.drawText(this.textRenderer, this.playerInventoryTitle,
                this.playerInventoryTitleX, this.playerInventoryTitleY, 0x404040, false);
    }
}

package net.empire.ewmedieval.gui.smithinganvil;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.forging.SmithingMinigameState;
import net.empire.ewmedieval.recipe.ForgingRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class SmithingAnvilScreen extends HandledScreen<SmithingAnvilScreenHandler> {

    private static final Identifier TEXTURE =
            new Identifier(EwMedieval.MOD_ID, "textures/gui/smithing_anvil.png");

    /** Width in pixels of the lit-arrow sprite in the texture atlas (at U=176, V=0). */
    private static final int ARROW_SPRITE_W = 24;
    private static final int ARROW_SPRITE_H = 17;

    public SmithingAnvilScreen(SmithingAnvilScreenHandler handler,
                               PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title);
        this.backgroundWidth  = 176;
        this.backgroundHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = 28;
        this.titleY = 6;
        this.playerInventoryTitleX = 8;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void drawBackground(DrawContext ctx, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        ctx.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // Arrow progress — fills left-to-right as hits are completed
        int scaled = getScaledArrow();
        if (scaled > 0) {
            ctx.drawTexture(TEXTURE, x + 89, y + 35, 176, 0, scaled, ARROW_SPRITE_H);
        }
    }

    /**
     * Returns the pixel width of the progress arrow (0–24).
     * Uses the complex minigame state when that is active, otherwise
     * falls back to the property-delegate values (simple forging mode).
     */
    private int getScaledArrow() {
        int total, done;
        if (SmithingMinigameState.active) {
            total = SmithingMinigameState.totalHits;
            done  = total - SmithingMinigameState.hitsRemaining;
        } else if (handler.isMinigameActive()) {
            total = handler.getTotalHits();
            done  = total - handler.getHitsRemaining();
        } else {
            return 0;
        }
        if (total <= 0 || done <= 0) return 0;
        return Math.min(done * ARROW_SPRITE_W / total, ARROW_SPRITE_W);
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx);
        super.render(ctx, mouseX, mouseY, delta);
        drawMouseoverTooltip(ctx, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext ctx, int mouseX, int mouseY) {
        ctx.drawText(textRenderer, title, titleX, titleY, 0x404040, false);
        ctx.drawText(textRenderer, playerInventoryTitle,
                playerInventoryTitleX, playerInventoryTitleY, 0x404040, false);

        // Hit counter — above the arrow/output area (y=35 → text sits at y=25)
        drawHitCounter(ctx);

        MinecraftClient mc = MinecraftClient.getInstance();
        if (handler.hasRecipe() && mc.world != null) {
            SimpleInventory inv = new SimpleInventory(9);
            for (int i = 0; i < 9; i++) inv.setStack(i, handler.slots.get(i).getStack());
            mc.world.getRecipeManager()
                    .getFirstMatch(ForgingRecipe.Type.INSTANCE, inv, mc.world)
                    .ifPresent(recipe -> {
                        // Ghost preview — only when output slot is empty
                        if (handler.slots.get(10).getStack().isEmpty()) {
                            ItemStack preview = recipe.getOutput(mc.world.getRegistryManager());
                            if (!preview.isEmpty()) {
                                ctx.fill(124, 35, 140, 51, 0xFF8B8B8B);
                                ctx.drawItem(preview, 124, 35);
                                // Push z above item render layer (items draw at z≈232) so overlay shows on top
                                ctx.getMatrices().push();
                                ctx.getMatrices().translate(0, 0, 300);
                                ctx.fill(124, 35, 140, 51, 0xC08B8B8B);
                                ctx.getMatrices().pop();
                            }
                        }
                    });
        }

    }

    private void drawHitCounter(DrawContext ctx) {
        Text text;
        if (SmithingMinigameState.active) {
            // Complex minigame running — show remaining from client minigame state
            text = Text.literal(SmithingMinigameState.hitsRemaining + " left")
                       .formatted(Formatting.GOLD);
        } else if (handler.isMinigameActive()) {
            // Simple forging in progress — use synced property values
            text = Text.literal(handler.getHitsRemaining() + " left")
                       .formatted(Formatting.YELLOW);
        } else if (handler.hasRecipe()) {
            // Recipe matched, not yet started — show total hits needed
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.world == null) return;
            SimpleInventory inv = new SimpleInventory(9);
            for (int i = 0; i < 9; i++) inv.setStack(i, handler.slots.get(i).getStack());
            mc.world.getRecipeManager()
                    .getFirstMatch(ForgingRecipe.Type.INSTANCE, inv, mc.world)
                    .ifPresent(recipe -> {
                        Text t = Text.literal(recipe.getHits() + " hits")
                                     .formatted(Formatting.GRAY);
                        ctx.drawText(textRenderer, t, 89, 25, 0x808080, false);
                    });
            return;
        } else {
            return;
        }
        ctx.drawText(textRenderer, text, 89, 25, 0xFFFFFF, false);
    }
}

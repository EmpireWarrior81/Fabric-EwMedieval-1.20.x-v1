package net.empire.ewmedieval.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.gui.nutrition.NutritionScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    private static final Identifier NUTRITION_TEXTURE = new Identifier(EwMedieval.MOD_ID, "textures/gui/nutrition.png");
    private static final int BACKGROUND_WIDTH = 176;
    private static final int BACKGROUND_HEIGHT = 166;
    private static final int BTN_X = 157;
    private static final int BTN_Y = 5;
    private static final int BTN_SIZE = 16;

    // Normal drumstick: center ~(181,14) → top-left U=177, V=10, size 9×9
    // Hover  drumstick: center ~(190,14) → top-left U=186, V=10, size 9×9
    private static final int DRUM_U       = 176;
    private static final int DRUM_U_HOVER = 185;
    private static final int DRUM_V = 10;
    private static final int DRUM_W = 9;
    private static final int DRUM_H = 9;

    @Inject(method = "render", at = @At("RETURN"))
    private void renderNutritionButton(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        int guiLeft = (mc.getWindow().getScaledWidth() - BACKGROUND_WIDTH) / 2;
        int guiTop  = (mc.getWindow().getScaledHeight() - BACKGROUND_HEIGHT) / 2;
        int bx = guiLeft + BTN_X;
        int by = guiTop  + BTN_Y;

        boolean hovering = isHovering(mouseX, mouseY, guiLeft, guiTop);
        int u = hovering ? DRUM_U_HOVER : DRUM_U;

        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        // Center the sprite in the 16×16 button area
        context.drawTexture(NUTRITION_TEXTURE, bx + (BTN_SIZE - DRUM_W) / 2, by + (BTN_SIZE - DRUM_H) / 2, u, DRUM_V, DRUM_W, DRUM_H);
        RenderSystem.enableDepthTest();

        if (hovering) {
            context.drawTooltip(mc.textRenderer, List.of(Text.translatable("screen.ewmedieval.nutrition")), mouseX, mouseY);
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient mc = MinecraftClient.getInstance();
        int guiLeft = (mc.getWindow().getScaledWidth() - BACKGROUND_WIDTH) / 2;
        int guiTop  = (mc.getWindow().getScaledHeight() - BACKGROUND_HEIGHT) / 2;
        if (isHovering((int) mouseX, (int) mouseY, guiLeft, guiTop)) {
            mc.setScreen(new NutritionScreen());
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    private static boolean isHovering(int mouseX, int mouseY, int guiLeft, int guiTop) {
        int bx = guiLeft + BTN_X;
        int by = guiTop  + BTN_Y;
        return mouseX >= bx && mouseX < bx + BTN_SIZE && mouseY >= by && mouseY < by + BTN_SIZE;
    }
}

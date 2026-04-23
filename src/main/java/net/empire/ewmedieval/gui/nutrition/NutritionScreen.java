package net.empire.ewmedieval.gui.nutrition;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.nutrition.ClientNutritionData;
import net.empire.ewmedieval.nutrition.NutritionConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class NutritionScreen extends Screen {

    private static final Identifier TEXTURE = new Identifier(EwMedieval.MOD_ID, "textures/gui/nutrition.png");

    private static final List<ItemStack> ICONS = List.of(
        new ItemStack(Items.SUGAR),
        new ItemStack(Items.CHICKEN),
        new ItemStack(Items.PORKCHOP),
        new ItemStack(Items.APPLE),
        new ItemStack(Items.IRON_NUGGET)
    );

    private static final List<Text> LABELS = List.of(
        Text.translatable("screen.ewmedieval.nutrition.carbs"),
        Text.translatable("screen.ewmedieval.nutrition.protein"),
        Text.translatable("screen.ewmedieval.nutrition.fat"),
        Text.translatable("screen.ewmedieval.nutrition.vitamins"),
        Text.translatable("screen.ewmedieval.nutrition.minerals")
    );

    private int x;
    private int y;

    public NutritionScreen() {
        super(Text.translatable("screen.ewmedieval.nutrition"));
    }

    @Override
    protected void init() {
        super.init();
        this.x = this.width / 2 - (176 / 2);
        this.y = this.height / 2 - (141 / 2);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);

        // Main panel
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, 176, 142);

        // Title
        context.drawText(this.textRenderer, this.title,
            this.x + 176 / 2 - this.textRenderer.getWidth(this.title) / 2,
            this.y + 7, 0x3F3F3F, false);

        // Back button (hover highlight)
        if (isHoveringBackButton(mouseX, mouseY)) {
            context.drawTexture(TEXTURE, this.x + 5, this.y + 5, 187, 0, 11, 10);
        } else {
            context.drawTexture(TEXTURE, this.x + 5, this.y + 5, 176, 0, 11, 10);
        }

        // Nutrient rows
        int extraY = 0;
        int extraBarY = 0;
        for (int i = 0; i < 5; i++) {
            float value = ClientNutritionData.INSTANCE.get(i);
            int fillWidth = (int)(140f * value / NutritionConstants.MAX);

            context.drawItem(ICONS.get(i), this.x + 7, this.y + 25 + extraY);
            context.drawText(this.textRenderer, LABELS.get(i),
                this.x + 28, this.y + 26 + extraY, 0x3F3F3F, false);

            // Bar background
            context.drawTexture(TEXTURE, this.x + 27, this.y + 36 + extraY, 0, 206 + extraBarY, 141, 5);
            // Bar fill
            if (fillWidth > 0) {
                context.drawTexture(TEXTURE, this.x + 27, this.y + 36 + extraY, 0, 211 + extraBarY, fillWidth, 5);
            }

            // Value text
            String valueStr = (int) value + "/" + (int) NutritionConstants.MAX;
            context.drawText(this.textRenderer, Text.literal(valueStr),
                this.x + 127, this.y + 26 + extraY, 0x3F3F3F, false);

            extraY += 23;
            extraBarY += 10;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isHoveringBackButton((int) mouseX, (int) mouseY)) {
            this.client.setScreen(new InventoryScreen(this.client.player));
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (MinecraftClient.getInstance().options.inventoryKey.matchesKey(keyCode, scanCode)) {
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private boolean isHoveringBackButton(int mouseX, int mouseY) {
        return mouseX >= this.x + 5 && mouseX < this.x + 16
            && mouseY >= this.y + 5 && mouseY < this.y + 15;
    }
}
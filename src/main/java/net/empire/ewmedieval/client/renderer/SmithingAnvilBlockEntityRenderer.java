package net.empire.ewmedieval.client.renderer;

import net.empire.ewmedieval.block.entity.custom.SmithingAnvilBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

import java.util.HashSet;
import java.util.Set;

public class SmithingAnvilBlockEntityRenderer implements BlockEntityRenderer<SmithingAnvilBlockEntity> {

    private static final float BASE_Y             = 1.01f;
    private static final float ITEM_HEIGHT        = 0.02f;
    private static final float BLOCK_HEIGHT       = 0.2f;
    private static final float BLOCK_BASE_Y_OFFSET = 0.09f;

    public SmithingAnvilBlockEntityRenderer(BlockEntityRendererFactory.Context context) {}

    @Override
    public void render(SmithingAnvilBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        boolean inputsEmpty = areInputSlotsEmpty(entity);
        float zOffset = inputsEmpty ? 0f : -0.43f;

        // Output item (slot 10)
        ItemStack output = entity.getRenderStack(SmithingAnvilBlockEntity.OUTPUT_SLOT);
        if (!output.isEmpty()) {
            float yOffset = isBlockItem(output) ? 1.05f : 1.02f;
            renderStack(matrices, vertexConsumers, itemRenderer, output, entity,
                    0f, yOffset, zOffset, 110f, 0.4f);
        }

        // Input items — up to 3 unique items from slots 0–8
        Set<Item> renderedItems = new HashSet<>();
        Set<Integer> renderedSlots = new HashSet<>();
        int rendered = renderPass(matrices, vertexConsumers, itemRenderer, entity,
                renderedItems, renderedSlots, 0f, 0, true);
        if (rendered < 3) {
            renderPass(matrices, vertexConsumers, itemRenderer, entity,
                    renderedItems, renderedSlots, 0f, rendered, false);
        }

        // Hammer item (slot 9)
        ItemStack hammer = entity.getRenderStack(SmithingAnvilBlockEntity.RESERVED_SLOT);
        if (!hammer.isEmpty()) {
            renderStack(matrices, vertexConsumers, itemRenderer, hammer, entity,
                    0f, 1.025f, 0.43f, 135f, 0.5f);
        }
    }

    private boolean areInputSlotsEmpty(SmithingAnvilBlockEntity entity) {
        for (int i = SmithingAnvilBlockEntity.INPUT_START; i < SmithingAnvilBlockEntity.INPUT_END; i++) {
            if (!entity.getRenderStack(i).isEmpty()) return false;
        }
        return true;
    }

    private int renderPass(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                           ItemRenderer itemRenderer, SmithingAnvilBlockEntity entity,
                           Set<Item> renderedItems, Set<Integer> renderedSlots,
                           float zOffset, int renderedCount, boolean checkUniqueness) {
        float currentHeight = BASE_Y;
        for (int i : renderedSlots) {
            currentHeight += isBlockItem(entity.getRenderStack(i)) ? BLOCK_HEIGHT : ITEM_HEIGHT;
        }

        int rendered = renderedCount;
        for (int i = SmithingAnvilBlockEntity.INPUT_START; i < SmithingAnvilBlockEntity.INPUT_END && rendered < 3; i++) {
            if (renderedSlots.contains(i)) continue;
            ItemStack stack = entity.getRenderStack(i);
            if (stack.isEmpty()) continue;
            Item item = stack.getItem();
            if (checkUniqueness && renderedItems.contains(item)) continue;

            float scale    = isBlockItem(stack) ? 0.4f : 0.35f;
            float rotation = 96f + (rendered * 14f);
            float yOffset  = currentHeight + (isBlockItem(stack) ? BLOCK_BASE_Y_OFFSET : 0f);

            renderStack(matrices, vertexConsumers, itemRenderer, stack, entity,
                    0f, yOffset, zOffset, rotation, scale);

            currentHeight += isBlockItem(stack) ? BLOCK_HEIGHT : ITEM_HEIGHT;
            renderedItems.add(item);
            renderedSlots.add(i);
            rendered++;
        }
        return rendered;
    }

    private boolean isBlockItem(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof BlockItem;
    }

    private void renderStack(MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                             ItemRenderer itemRenderer, ItemStack stack,
                             SmithingAnvilBlockEntity entity,
                             float xOffset, float yOffset, float zOffset,
                             float rotationDegrees, float scale) {
        if (stack.isEmpty()) return;

        BlockState state  = entity.getCachedState();
        Direction facing  = state.contains(Properties.HORIZONTAL_FACING)
                ? state.get(Properties.HORIZONTAL_FACING)
                : Direction.NORTH;

        float facingRot = switch (facing) {
            case NORTH -> 180f;
            case SOUTH -> 0f;
            case WEST  -> 270f;
            case EAST  -> 90f;
            default    -> 0f;
        };

        double radians = Math.toRadians(facingRot);
        float rotatedX = (float)(xOffset * Math.cos(radians) - zOffset * Math.sin(radians));
        float rotatedZ = (float)(xOffset * Math.sin(radians) + zOffset * Math.cos(radians));

        matrices.push();
        matrices.translate(0.5f - rotatedX, yOffset, 0.5f + rotatedZ);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(facingRot));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationDegrees));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(isBlockItem(stack) ? 0f : 90f));
        matrices.scale(scale, scale, scale);

        // Use max light so items always appear at full brightness with their correct colours
        itemRenderer.renderItem(stack, ModelTransformationMode.FIXED,
                LightmapTextureManager.MAX_LIGHT_COORDINATE, OverlayTexture.DEFAULT_UV,
                matrices, vertexConsumers, entity.getWorld(), 1);

        matrices.pop();
    }
}

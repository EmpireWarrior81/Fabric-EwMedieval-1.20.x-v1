package net.empire.ewmedieval.client.renderer;

import net.empire.ewmedieval.block.custom.StoveBlock;
import net.empire.ewmedieval.block.entity.custom.StoveBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec2f;

public class StoveBlockEntityRenderer implements BlockEntityRenderer<StoveBlockEntity> {

    public StoveBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(StoveBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        ItemStack[] inventory = entity.getInventory();
        Direction facing = entity.getCachedState().get(StoveBlock.FACING);
        Direction right = facing.rotateYClockwise();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        for (int i = 0; i < inventory.length; i++) {
            ItemStack stack = inventory[i];
            if (stack.isEmpty()) continue;

            Vec2f offset = entity.getStoveItemOffset(i);

            // Map stove-local slot offsets to block-local X/Z coordinates.
            // offset.x = left/right relative to stove facing (right = positive)
            // offset.y = depth relative to stove facing (away from face = positive)
            float wx = 0.5f + right.getOffsetX() * offset.x - facing.getOffsetX() * offset.y;
            float wz = 0.5f + right.getOffsetZ() * offset.x - facing.getOffsetZ() * offset.y;

            matrices.push();
            // Place just above the stove top surface
            matrices.translate(wx, 1.02, wz);
            // Align item orientation with stove facing direction (+ 180 so face points upward)
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-facing.asRotation() + 180));
            // Lay the item flat on the grill surface
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
            // Scale to fit in the grill slot
            matrices.scale(0.35F, 0.35F, 0.35F);

            itemRenderer.renderItem(
                    stack,
                    ModelTransformationMode.FIXED,
                    light,
                    overlay,
                    matrices,
                    vertexConsumers,
                    entity.getWorld(),
                    (int) entity.getPos().asLong() + i
            );

            matrices.pop();
        }
    }
}
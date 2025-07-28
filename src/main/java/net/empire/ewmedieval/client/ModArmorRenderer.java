package net.empire.ewmedieval.client;

import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;


public class ModArmorRenderer implements ArmorRenderer {

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            ItemStack stack,
            LivingEntity entity,
            EquipmentSlot slot,
            int light,
            BipedEntityModel<LivingEntity> contextModel
    ) {
        Identifier texture;

        if (stack.isOf(ModItems.GONDORIAN_FOUNTAIN_GUARD_HELMET)) {
            texture = new Identifier("ewmedieval", "textures/models/armor/gondorian_fountain_guard_helmet.png");
        } else if (stack.isOf(ModItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE)) {
            texture = new Identifier("ewmedieval", "textures/models/armor/gondorian_fountain_guard_chestplate.png");
        } else if (stack.isOf(ModItems.GONDORIAN_FOUNTAIN_GUARD_LEGGINGS)) {
            texture = new Identifier("ewmedieval", "textures/models/armor/gondorian_fountain_guard_leggings.png");
        } else if (stack.isOf(ModItems.GONDORIAN_FOUNTAIN_GUARD_BOOTS)) {
            texture = new Identifier("ewmedieval", "textures/models/armor/gondorian_fountain_guard_boots.png");
        } else {
            return;
        }

        switch (slot) {
            case HEAD -> renderPart(contextModel.head, matrices, vertexConsumers, light, texture);
            case CHEST -> renderPart(contextModel.body, matrices, vertexConsumers, light, texture);
            case LEGS -> {
                renderPart(contextModel.leftLeg, matrices, vertexConsumers, light, texture);
                renderPart(contextModel.rightLeg, matrices, vertexConsumers, light, texture);
            }
        }
    }
    private void renderPart(ModelPart part, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Identifier texture) {
        MinecraftClient client = MinecraftClient.getInstance();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texture));
        part.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
    }

}
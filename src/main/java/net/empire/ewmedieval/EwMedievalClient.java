package net.empire.ewmedieval;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.client.model.equipment.CustomBootsModel;
import net.empire.ewmedieval.client.model.equipment.CustomChestplateModel;
import net.empire.ewmedieval.client.model.equipment.CustomHelmetModel;
import net.empire.ewmedieval.client.model.equipment.CustomLeggingsModel;
import net.empire.ewmedieval.client.renderer.ModArmorRenderer;
import net.empire.ewmedieval.gui.ModScreenHandlers;
import net.empire.ewmedieval.gui.earlyforge.EarlyForgeScreen;
import net.empire.ewmedieval.gui.forge.ForgeScreen;
import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class EwMedievalClient implements ClientModInitializer {

    public static EntityModelLayer CUSTOM_ARMOR_HELMET = new EntityModelLayer(Identifier.of(EwMedieval.MOD_ID, "armor"), "_1");
    public static EntityModelLayer CUSTOM_ARMOR_CHESTPLATE = new EntityModelLayer(Identifier.of(EwMedieval.MOD_ID, "armor"), "_2");
    public static EntityModelLayer CUSTOM_ARMOR_LEGGINGS = new EntityModelLayer(Identifier.of(EwMedieval.MOD_ID, "armor"), "_3");
    public static EntityModelLayer CUSTOM_ARMOR_BOOTS = new EntityModelLayer(Identifier.of(EwMedieval.MOD_ID, "armor"), "_4");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.TUFF_CARVED_WINDOW,
                ModBlocks.TUFF_CARVED_WINDOW_PANE);

        EntityModelLayerRegistry.registerModelLayer(CUSTOM_ARMOR_HELMET, CustomHelmetModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CUSTOM_ARMOR_CHESTPLATE, CustomChestplateModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CUSTOM_ARMOR_LEGGINGS, CustomLeggingsModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CUSTOM_ARMOR_BOOTS, CustomBootsModel::getTexturedModelData);


        ArmorRenderer.register(new ModArmorRenderer(),
                ModItems.GONDORIAN_FOUNTAIN_GUARD_HELMET,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_LEGGINGS,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_BOOTS
        );

        HandledScreens.register(ModScreenHandlers.FORGE_SCREEN_SCREEN_HANDLER, ForgeScreen::new);
        HandledScreens.register(ModScreenHandlers.EARLY_FORGE_SCREEN_SCREEN_HANDLER, EarlyForgeScreen::new);

    }
}

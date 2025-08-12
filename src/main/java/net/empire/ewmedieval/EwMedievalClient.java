package net.empire.ewmedieval;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.client.ModArmorRenderer;
import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.RenderLayer;

public class EwMedievalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.TUFF_CARVED_WINDOW,
                ModBlocks.TUFF_CARVED_WINDOW_PANE);

        ArmorRenderer.register(new ModArmorRenderer(),
                ModItems.GONDORIAN_FOUNTAIN_GUARD_HELMET,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_LEGGINGS,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_BOOTS
        );

    }
}

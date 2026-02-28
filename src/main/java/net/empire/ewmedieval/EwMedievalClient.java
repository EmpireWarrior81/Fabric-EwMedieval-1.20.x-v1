package net.empire.ewmedieval;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.block.entity.ModBlockEntities;
import net.empire.ewmedieval.client.renderer.CuttingBoardRenderer;
import net.empire.ewmedieval.gui.ModScreenHandlers;
import net.empire.ewmedieval.gui.earlyforge.EarlyForgeScreen;
import net.empire.ewmedieval.gui.forge.ForgeScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class EwMedievalClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.TUFF_CARVED_WINDOW,
                ModBlocks.TUFF_CARVED_WINDOW_PANE);


        HandledScreens.register(ModScreenHandlers.FORGE_SCREEN_SCREEN_HANDLER, ForgeScreen::new);
        HandledScreens.register(ModScreenHandlers.EARLY_FORGE_SCREEN_SCREEN_HANDLER, EarlyForgeScreen::new);

        BlockEntityRendererFactories.register(
                ModBlockEntities.CUTTING_BOARD_BLOCK_ENTITY,
                CuttingBoardRenderer::new);

    }
}

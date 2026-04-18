package net.empire.ewmedieval;

import net.empire.ewmedieval.block.ModBlocks;
import net.empire.ewmedieval.block.entity.ModBlockEntities;
import net.empire.ewmedieval.client.particle.SteamParticle;
import net.empire.ewmedieval.client.renderer.CuttingBoardRenderer;
import net.empire.ewmedieval.client.renderer.StoveBlockEntityRenderer;
import net.empire.ewmedieval.entity.ModEntityTypes;
import net.empire.ewmedieval.gui.ComfortHealthOverlay;
import net.empire.ewmedieval.gui.ModScreenHandlers;
import net.empire.ewmedieval.gui.NourishmentHungerOverlay;
import net.empire.ewmedieval.gui.cookingpot.CookingPotScreen;
import net.empire.ewmedieval.gui.earlyforge.EarlyForgeScreen;
import net.empire.ewmedieval.gui.forge.ForgeScreen;
import net.empire.ewmedieval.particle.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class EwMedievalClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.COOKING_POT,
                ModBlocks.TUFF_CARVED_WINDOW,
                ModBlocks.TUFF_CARVED_WINDOW_PANE,
                ModBlocks.ROAST_CHICKEN_BLOCK,
                ModBlocks.WILD_CARROTS,
                ModBlocks.WILD_BEETROOTS,
                ModBlocks.WILD_CABBAGES,
                ModBlocks.WILD_ONIONS,
                ModBlocks.WILD_POTATOES,
                ModBlocks.WILD_TOMATOES,
                ModBlocks.WILD_RICE,
                ModBlocks.WILD_CORN,
                ModBlocks.WILD_CORN_DRY,
                ModBlocks.WILD_EGGPLANTS,
                ModBlocks.WILD_CUCUMBERS,
                ModBlocks.WILD_COFFEE,
                ModBlocks.WILD_COTTON,
                ModBlocks.WILD_BELL_PEPPERS,
                ModBlocks.WILD_TURNIPS,
                ModBlocks.WILD_ZUCCHINIS,
                ModBlocks.WILD_BROCCOLI,
                ModBlocks.WILD_CAULIFLOWERS,
                ModBlocks.WILD_SWEET_POTATOES,
                ModBlocks.WILD_AJI_AMARILLO,
                ModBlocks.WILD_SOYA,
                ModBlocks.WILD_YUCA,
                ModBlocks.MATURE_DANDELION,
                ModBlocks.SOYA_CROP,
                ModBlocks.YUCA_CROP,
                ModBlocks.AJI_AMARILLO_CROP,
                ModBlocks.GARLIC_CROP,
                ModBlocks.ONION_CROP,
                ModBlocks.CABBAGE_CROP,
                ModBlocks.RICE_CROP,
                ModBlocks.RICE_CROP_PANICLES,
                ModBlocks.ROPE,
                ModBlocks.TOMATO_CROP,
                ModBlocks.BUDDING_TOMATO_CROP,
                ModBlocks.CUCUMBER_CROP,
                ModBlocks.EGGPLANT_CROP,
                ModBlocks.CORN_CROP,
                ModBlocks.CORN_UPPER_CROP,
                ModBlocks.COTTON_CROP,
                ModBlocks.COFFEE_CROP,
                ModBlocks.BELL_PEPPER_CROP,
                ModBlocks.CAULIFLOWER_CROP,
                ModBlocks.BROCCOLI_CROP,
                ModBlocks.SWEET_POTATO_CROP,
                ModBlocks.TURNIP_CROP,
                ModBlocks.ZUCCHINI_CROP,
                ModBlocks.SANDY_SHRUB,
                ModBlocks.AVOCADO_SAPLING,
                ModBlocks.AVOCADO_PIT,
                ModBlocks.CHICKPEA_CROP,
                ModBlocks.PARSLEY_CROP
                );

        // Leaves need CUTOUT_MIPPED (like vanilla oak leaves) to avoid a black box
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                ModBlocks.AVOCADO_LEAVES
        );


        ParticleFactoryRegistry.getInstance().register(ModParticles.STEAM, SteamParticle.Factory::new);

        HandledScreens.register(ModScreenHandlers.FORGE_SCREEN_SCREEN_HANDLER, ForgeScreen::new);
        HandledScreens.register(ModScreenHandlers.EARLY_FORGE_SCREEN_SCREEN_HANDLER, EarlyForgeScreen::new);
        HandledScreens.register(ModScreenHandlers.COOKING_POT_SCREEN_HANDLER, CookingPotScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntities.CUTTING_BOARD_BLOCK_ENTITY, CuttingBoardRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.STOVE, StoveBlockEntityRenderer::new);

        EntityRendererRegistry.register(ModEntityTypes.ROTTEN_TOMATO, FlyingItemEntityRenderer::new);

        HudRenderCallback.EVENT.register((drawContext, tickDelta) ->
                ComfortHealthOverlay.onRenderGuiOverlayPost(drawContext, tickDelta));

        HudRenderCallback.EVENT.register((drawContext, tickDelta) ->
                NourishmentHungerOverlay.onRenderGuiOverlayPost(drawContext, tickDelta));

    }
}

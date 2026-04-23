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
import net.empire.ewmedieval.nutrition.NutritionFoodLoader;
import net.empire.ewmedieval.nutrition.NutritionFoodValues;
import net.empire.ewmedieval.particle.ModParticles;
import net.empire.ewmedieval.network.NutritionSyncPacket;
import net.empire.ewmedieval.nutrition.ClientNutritionData;
import net.empire.ewmedieval.season.SeasonCropRegistry;
import net.empire.ewmedieval.season.SeasonPeriod;
import net.empire.ewmedieval.season.SeasonTooltipConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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
                ModBlocks.WILD_SOYBEAN,
                ModBlocks.WILD_CASSAVA,
                ModBlocks.MATURE_DANDELION,
                ModBlocks.SOYBEAN_CROP,
                ModBlocks.CASSAVA_CROP,
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

ClientPlayNetworking.registerGlobalReceiver(NutritionSyncPacket.ID, (client, handler, buf, responseSender) -> {
            net.empire.ewmedieval.nutrition.NutritionData incoming = NutritionSyncPacket.read(buf);
            client.execute(() -> ClientNutritionData.update(incoming));
        });

        ItemTooltipCallback.EVENT.register((stack, tooltipContext, lines) -> {
            if (!Screen.hasShiftDown()) return;
            NutritionFoodValues values = NutritionFoodLoader.get(stack.getItem());
            if (values == null) return;
            lines.add(Text.translatable("tooltip.ewmedieval.nutrition_header").formatted(Formatting.GRAY));
            String[] keys = {
                "screen.ewmedieval.nutrition.carbs",
                "screen.ewmedieval.nutrition.protein",
                "screen.ewmedieval.nutrition.fat",
                "screen.ewmedieval.nutrition.vitamins",
                "screen.ewmedieval.nutrition.minerals"
            };
            float[] vals = {values.carbs(), values.protein(), values.fat(), values.vitamins(), values.minerals()};
            for (int i = 0; i < 5; i++) {
                if (vals[i] > 0) {
                    lines.add(Text.translatable(keys[i])
                        .append(Text.literal(": " + (int) vals[i]).formatted(Formatting.WHITE))
                        .formatted(Formatting.YELLOW));
                }
            }
        });

        // Season tooltip — brief line on normal hover, full grid on shift+hover
        SeasonTooltipConfig.load();
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, lines) -> {
            if (!SeasonTooltipConfig.isEnabled()) return;
            if (!(stack.getItem() instanceof BlockItem bi)) return;
            Block block = bi.getBlock();
            float[] mods = SeasonCropRegistry.getRawModifiers(block);
            if (mods == null) return;

            // Determine current period index from client world time
            MinecraftClient mc = MinecraftClient.getInstance();
            int periodIndex = 0;
            if (mc.world != null) {
                long totalDays = mc.world.getTimeOfDay() / 24000L;
                periodIndex = (int) ((totalDays % SeasonPeriod.TOTAL_YEAR_DAYS) / SeasonPeriod.DAYS_PER_PERIOD);
            }

            // Tropical biome override for brief line
            float currentMod = mods[periodIndex];
            if (SeasonCropRegistry.isTropical(block) && mc.world != null && mc.player != null) {
                float temp = mc.world.getBiome(mc.player.getBlockPos()).value().getTemperature();
                if (temp >= 1.0f) currentMod = 1.0f;
                else if (temp < 0.15f) currentMod = 0.0f;
            }

            if (!Screen.hasShiftDown()) {
                // Brief one-liner
                if (currentMod <= 0f) {
                    lines.add(Text.literal("Won't grow this season").formatted(Formatting.RED));
                } else if (currentMod < 1f) {
                    lines.add(Text.literal("Grows slower this season").formatted(Formatting.YELLOW));
                } else {
                    lines.add(Text.literal("In season").formatted(Formatting.GREEN));
                }
                if (SeasonCropRegistry.isTropical(block)) {
                    lines.add(Text.literal("Year-round in hot biomes").formatted(Formatting.GOLD));
                }
            } else {
                // Two-line season overview — colored by best modifier in each season
                lines.add(seasonDotLine(mods, "Spring", 0, "Summer", 3));
                lines.add(seasonDotLine(mods, "Autumn", 6, "Winter", 9));
                if (SeasonCropRegistry.isTropical(block)) {
                    lines.add(Text.literal("✶ Year-round in hot biomes").formatted(Formatting.GOLD));
                }
            }
        });
    }

    // --- Season tooltip helpers ---

    private static MutableText seasonDotLine(float[] mods, String nameA, int startA, String nameB, int startB) {
        return Text.empty()
            .append(seasonEntry(mods, nameA, startA))
            .append(Text.literal("    ").formatted(Formatting.DARK_GRAY))
            .append(seasonEntry(mods, nameB, startB));
    }

    private static MutableText seasonEntry(float[] mods, String name, int start) {
        MutableText t = Text.literal(name + " ").formatted(Formatting.GRAY);
        for (int i = start; i < start + 3; i++) {
            t.append(periodDot(mods[i]));
        }
        return t;
    }

    private static Text periodDot(float mod) {
        if (mod >= 1.0f) return Text.literal("●").formatted(Formatting.GREEN);
        if (mod >= 0.5f) return Text.literal("●").formatted(Formatting.YELLOW);
        if (mod > 0f)    return Text.literal("●").formatted(Formatting.GOLD);
        return            Text.literal("○").formatted(Formatting.DARK_GRAY);
    }
}

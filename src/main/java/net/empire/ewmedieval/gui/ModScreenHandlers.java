package net.empire.ewmedieval.gui;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.gui.cookingpot.CookingPotScreenHandler;
import net.empire.ewmedieval.gui.earlyforge.EarlyForgeScreenHandler;
import net.empire.ewmedieval.gui.forge.ForgeScreenHandler;
import net.empire.ewmedieval.gui.knapping.KnappingScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ForgeScreenHandler> FORGE_SCREEN_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(EwMedieval.MOD_ID, "forge"),
                    new ExtendedScreenHandlerType<>(ForgeScreenHandler::new));

    public static final ScreenHandlerType<EarlyForgeScreenHandler> EARLY_FORGE_SCREEN_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(EwMedieval.MOD_ID, "earlyforge"),
                    new ExtendedScreenHandlerType<>(EarlyForgeScreenHandler::new));

    public static final ScreenHandlerType<CookingPotScreenHandler> COOKING_POT_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(EwMedieval.MOD_ID, "cooking_pot"),
                    new ExtendedScreenHandlerType<>(CookingPotScreenHandler::new));

    public static final ScreenHandlerType<KnappingScreenHandler> KNAPPING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(EwMedieval.MOD_ID, "knapping"),
                    new ExtendedScreenHandlerType<>((syncId, inv, buf) -> new KnappingScreenHandler(syncId, inv)));

    public static void registerScreenHandlers() {
        EwMedieval.LOGGER.info("Registering Screen Handlers for" + EwMedieval.MOD_ID);
    }
}

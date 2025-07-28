package net.empire.ewmedieval;

import net.empire.ewmedieval.client.ModArmorRenderer;
import net.empire.ewmedieval.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;

public class EwMedievalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ArmorRenderer.register(new ModArmorRenderer(),
                ModItems.GONDORIAN_FOUNTAIN_GUARD_HELMET,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_LEGGINGS,
                ModItems.GONDORIAN_FOUNTAIN_GUARD_BOOTS
        );

    }
}

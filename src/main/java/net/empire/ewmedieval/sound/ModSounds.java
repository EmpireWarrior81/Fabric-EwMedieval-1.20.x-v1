package net.empire.ewmedieval.sound;

import net.empire.ewmedieval.EwMedieval;
import net.empire.ewmedieval.util.LoggerUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(EwMedieval.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
       LoggerUtil.logDebug("Registering Mod SoundEvents for " + EwMedieval.MOD_ID);
    }
}

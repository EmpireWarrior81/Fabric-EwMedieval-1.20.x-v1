package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonManager;
import net.empire.ewmedieval.season.SeasonPeriod;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Prevents animals from entering love mode during non-breeding seasons (Autumn and Winter).
 * Spring and Summer are the only seasons when animals can breed.
 * The food item is still consumed by vanilla before this cancel fires, so the item is not returned —
 * this is intentional: feeding an animal out of season wastes the food, signalling to the player
 * that something is off without requiring any UI message.
 */
@Mixin(AnimalEntity.class)
public abstract class AnimalBreedingSeasonMixin {

    @Inject(method = "lovePlayer", at = @At("HEAD"), cancellable = true)
    private void ewmedieval$blockOffSeasonBreeding(PlayerEntity player, CallbackInfo ci) {
        SeasonPeriod period = SeasonManager.currentPeriod;
        if (period != null && !period.isBreedingSeason) {
            ci.cancel();
        }
    }
}

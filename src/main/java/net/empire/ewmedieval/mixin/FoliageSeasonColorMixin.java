package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.world.FoliageColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(FoliageColors.class)
public class FoliageSeasonColorMixin {

    @Inject(method = "getBirchColor", at = @At("RETURN"), cancellable = true)
    private static void seasonalBirch(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(SeasonColors.getBirchColor());
    }

    @Inject(method = "getSpruceColor", at = @At("RETURN"), cancellable = true)
    private static void seasonalSpruce(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(SeasonColors.getSpruceColor());
    }
}

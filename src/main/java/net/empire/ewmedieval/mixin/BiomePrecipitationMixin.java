package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.PrecipitationLevel;
import net.empire.ewmedieval.season.SeasonManager;
import net.empire.ewmedieval.season.SeasonPeriod;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Forces SNOW precipitation in temperate biomes during winter periods.
 * Runs on both client (visual rain/snow particles) and server (snow block placement).
 * Only affects biomes that would normally return RAIN — frozen biomes already return SNOW,
 * and hot/tropical biomes (temp >= 0.9) are left unchanged.
 */
@Mixin(Biome.class)
public abstract class BiomePrecipitationMixin {

    @Shadow public abstract float getTemperature();

    @Inject(method = "getPrecipitation", at = @At("RETURN"), cancellable = true)
    private void ewmedieval$winterSnow(BlockPos pos, CallbackInfoReturnable<Biome.Precipitation> cir) {
        // Only upgrade RAIN → SNOW; NONE stays NONE, SNOW stays SNOW.
        if (cir.getReturnValue() != Biome.Precipitation.RAIN) return;

        SeasonPeriod period = SeasonManager.currentPeriod;
        if (period == null || period.precipitation != PrecipitationLevel.DRY) return;

        // Only apply in the three winter periods.
        if (period != SeasonPeriod.EARLY_WINTER
         && period != SeasonPeriod.MID_WINTER
         && period != SeasonPeriod.LATE_WINTER) return;

        // Tropical/jungle biomes (temp >= 0.9) keep RAIN; everything cooler gets SNOW.
        if (this.getTemperature() < 0.9f) {
            cir.setReturnValue(Biome.Precipitation.SNOW);
        }
    }
}

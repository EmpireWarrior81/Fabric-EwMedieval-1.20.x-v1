package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(BiomeColors.class)
public class BiomeSeasonColorMixin {

    @Inject(method = "getGrassColor", at = @At("RETURN"), cancellable = true)
    private static void seasonalGrass(BlockRenderView world, BlockPos pos,
                                      CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(SeasonColors.blendGrass(cir.getReturnValue(), getBiomeTemp(world, pos)));
    }

    @Inject(method = "getFoliageColor", at = @At("RETURN"), cancellable = true)
    private static void seasonalFoliage(BlockRenderView world, BlockPos pos,
                                        CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(SeasonColors.blendFoliage(cir.getReturnValue(), getBiomeTemp(world, pos)));
    }

    private static float getBiomeTemp(BlockRenderView world, BlockPos pos) {
        if (world instanceof WorldView wv) {
            return wv.getBiome(pos).value().getTemperature();
        }
        return 0.5f; // safe temperate fallback
    }
}

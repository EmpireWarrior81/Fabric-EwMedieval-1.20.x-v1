package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonCropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CropBlock.class)
public abstract class CropSeasonMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void seasonGate(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        float mod = SeasonCropRegistry.getModifier((Block)(Object)this, world, pos);
        if (mod <= 0f || (mod < 1f && random.nextFloat() >= mod)) {
            ci.cancel();
        }
    }
}

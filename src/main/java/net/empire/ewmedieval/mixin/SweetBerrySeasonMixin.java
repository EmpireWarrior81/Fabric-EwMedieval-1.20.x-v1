package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonCropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SweetBerryBushBlock.class)
public abstract class SweetBerrySeasonMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void seasonGate(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        float mod = SeasonCropRegistry.getModifier((Block)(Object)this, world, pos);
        if (mod <= 0f || (mod < 1f && random.nextFloat() >= mod)) {
            ci.cancel();
        }
    }
}

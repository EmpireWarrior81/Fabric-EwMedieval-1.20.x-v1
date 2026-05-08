package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonCropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public abstract class CropSeasonMixin {

    @Inject(method = "canGrow", at = @At("HEAD"), cancellable = true)
    private void seasonBoneMealGrowGate(World world, Random random, BlockPos pos, BlockState state,
                                        CallbackInfoReturnable<Boolean> cir) {
        if (SeasonCropRegistry.CREATIVE_BONEMEAL.get()) return;
        if (!(world instanceof ServerWorld sw)) return;
        if (SeasonCropRegistry.getModifier((Block)(Object)this, sw, pos) <= 0f) cir.setReturnValue(false);
    }

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void seasonGate(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        float mod = SeasonCropRegistry.getModifier((Block)(Object)this, world, pos);
        if (mod <= 0f || (mod < 1f && random.nextFloat() >= mod)) {

            ci.cancel();
            return;
        }
        if (world.isRaining()) ((CropBlock)(Object)this).applyGrowth(world, pos, state);
    }
}

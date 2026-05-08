package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonCropRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public class CropYieldMixin {

    @Inject(
        method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
        at = @At("RETURN"), cancellable = true
    )
    private static void seasonYieldBonus(BlockState state, ServerWorld world, BlockPos pos,
                                         BlockEntity blockEntity, Entity entity, ItemStack tool,
                                         CallbackInfoReturnable<List<ItemStack>> cir) {
        if (!(state.getBlock() instanceof CropBlock crop)) return;
        if (!crop.isMature(state)) return;
        float mod = SeasonCropRegistry.getModifier(state.getBlock(), world, pos);
        if (mod < 1.0f) return;

        List<ItemStack> drops = cir.getReturnValue();
        List<ItemStack> bonus = new ArrayList<>();
        for (ItemStack stack : drops) {
            if (!stack.isEmpty() && world.random.nextFloat() < 0.25f) {
                bonus.add(new ItemStack(stack.getItem(), 1));
            }
        }
        if (!bonus.isEmpty()) {
            List<ItemStack> result = new ArrayList<>(drops);
            result.addAll(bonus);
            cir.setReturnValue(result);
        }
    }
}
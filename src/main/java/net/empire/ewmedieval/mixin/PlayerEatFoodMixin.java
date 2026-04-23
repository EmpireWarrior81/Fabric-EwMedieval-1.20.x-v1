package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.network.NutritionSyncPacket;
import net.empire.ewmedieval.nutrition.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEatFoodMixin {

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "eatFood", at = @At("HEAD"))
    private void onEatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (world.isClient()) return;
        if (!((Object) this instanceof ServerPlayerEntity player)) return;
        if (player.isCreative()) return;

        NutritionFoodValues values = NutritionFoodLoader.get(stack.getItem());
        if (values == null) return;

        NutritionData data = ModNutrition.get(player);
        for (int i = 0; i < 5; i++) {
            data.add(i, values.get(i));
        }
        NutritionEffects.apply(player, data);
        NutritionSyncPacket.send(player, data);
    }
}
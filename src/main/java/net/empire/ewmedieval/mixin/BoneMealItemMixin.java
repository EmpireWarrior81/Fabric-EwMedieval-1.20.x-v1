package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonCropRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void setCreativeFlag(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        if (player != null && player.isCreative()) {
            SeasonCropRegistry.CREATIVE_BONEMEAL.set(true);
        }
    }

    @Inject(method = "useOnBlock", at = @At("RETURN"))
    private void clearCreativeFlag(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        SeasonCropRegistry.CREATIVE_BONEMEAL.set(false);
    }
}

package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.season.SeasonCropRegistry;
import net.empire.ewmedieval.season.SeasonManager;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MerchantEntity.class)
public abstract class VillagerSeasonalPriceMixin {

    @Inject(method = "getOffers", at = @At("RETURN"))
    private void ewmedieval$applySeasonalPrices(CallbackInfoReturnable<TradeOfferList> cir) {
        MerchantEntity self = (MerchantEntity) (Object) this;
        if (self.getWorld().isClient()) return;

        TradeOfferList offers = cir.getReturnValue();
        if (offers == null || offers.isEmpty()) return;

        int periodIndex = SeasonManager.getCurrentPeriodIndex((ServerWorld) self.getWorld());

        for (TradeOffer offer : offers) {
            Item buyItem  = offer.getOriginalFirstBuyItem().getItem();
            Item sellItem = offer.getSellItem().getItem();

            float mod = SeasonCropRegistry.getItemModifier(buyItem, periodIndex);
            if (mod >= 0f && mod < 1f) {
                float safeMod = Math.max(mod, 0.1f);
                int base     = offer.getOriginalFirstBuyItem().getCount();
                int adjusted = Math.max(1, Math.round(base * safeMod));
                offer.setSpecialPrice(-(base - adjusted));
                continue;
            }

            mod = SeasonCropRegistry.getItemModifier(sellItem, periodIndex);
            if (mod >= 0f && mod < 1f) {
                float safeMod = Math.max(mod, 0.1f);
                int base     = offer.getOriginalFirstBuyItem().getCount();
                int adjusted = Math.round(base / safeMod);
                int increase = Math.min(64 - base, adjusted - base);
                if (increase > 0) offer.setSpecialPrice(increase);
            }
        }
    }
}

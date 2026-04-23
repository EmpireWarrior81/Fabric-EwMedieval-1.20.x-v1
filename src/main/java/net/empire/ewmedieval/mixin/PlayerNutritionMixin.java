package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.nutrition.NutritionData;
import net.empire.ewmedieval.nutrition.NutritionHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerNutritionMixin implements NutritionHolder {

    @Unique
    private final NutritionData ewmedieval$nutrition = new NutritionData();

    @Override
    public NutritionData ewmedieval$getNutrition() {
        return ewmedieval$nutrition;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeNutrition(NbtCompound nbt, CallbackInfo ci) {
        NbtCompound tag = new NbtCompound();
        tag.putFloat("carbs",    ewmedieval$nutrition.carbs);
        tag.putFloat("protein",  ewmedieval$nutrition.protein);
        tag.putFloat("fat",      ewmedieval$nutrition.fat);
        tag.putFloat("vitamins", ewmedieval$nutrition.vitamins);
        tag.putFloat("minerals", ewmedieval$nutrition.minerals);
        nbt.put("ewmedieval_nutrition", tag);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readNutrition(NbtCompound nbt, CallbackInfo ci) {
        if (!nbt.contains("ewmedieval_nutrition")) return;
        NbtCompound tag = nbt.getCompound("ewmedieval_nutrition");
        ewmedieval$nutrition.carbs    = tag.getFloat("carbs");
        ewmedieval$nutrition.protein  = tag.getFloat("protein");
        ewmedieval$nutrition.fat      = tag.getFloat("fat");
        ewmedieval$nutrition.vitamins = tag.getFloat("vitamins");
        ewmedieval$nutrition.minerals = tag.getFloat("minerals");
    }
}

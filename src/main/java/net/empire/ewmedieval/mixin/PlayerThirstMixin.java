/*
package net.empire.ewmedieval.mixin;

import net.empire.ewmedieval.thirst.ThirstHolder;
import net.empire.ewmedieval.thirst.ThirstManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerThirstMixin implements ThirstHolder {

    @Unique
    private final ThirstManager ewmedieval$thirst = new ThirstManager();

    @Override
    public ThirstManager ewmedieval$getThirstManager() {
        return ewmedieval$thirst;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeThirst(NbtCompound nbt, CallbackInfo ci) {
        ewmedieval$thirst.writeNbt(nbt);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readThirst(NbtCompound nbt, CallbackInfo ci) {
        ewmedieval$thirst.readNbt(nbt);
    }
}
*/

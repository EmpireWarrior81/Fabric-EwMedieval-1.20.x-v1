/*
package net.empire.ewmedieval.thirst;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;

public class ThirstManager {

    public static final RegistryKey<net.minecraft.entity.damage.DamageType> THIRST_DAMAGE =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("ewmedieval", "thirst"));

    public float dehydration;
    private int dehydrationTimer;
    private int thirstLevel = 20;

    public void add(int amount) {
        this.thirstLevel = Math.min(20, this.thirstLevel + amount);
    }

    public void addDehydration(float amount) {
        this.dehydration = Math.min(40f, this.dehydration + amount);
    }

    public void update(PlayerEntity player) {
        Difficulty difficulty = player.getWorld().getDifficulty();
        if (difficulty == Difficulty.PEACEFUL) return;

        if (this.dehydration > 4.0f) {
            this.dehydration -= 4.0f;
            this.thirstLevel = Math.max(0, this.thirstLevel - 1);
        }

        if (this.thirstLevel <= 0) {
            ++this.dehydrationTimer;
            if (this.dehydrationTimer >= 90) {
                if (player.getHealth() > 10.0f || difficulty == Difficulty.HARD
                        || (player.getHealth() > 1.0f && difficulty == Difficulty.NORMAL)) {
                    player.damage(player.getWorld().getDamageSources().create(THIRST_DAMAGE), 1.0f);
                }
                this.dehydrationTimer = 0;
            }
        } else {
            this.dehydrationTimer = 0;
        }
    }

    public void readNbt(NbtCompound tag) {
        if (!tag.contains("ewmedieval_thirst")) return;
        NbtCompound t = tag.getCompound("ewmedieval_thirst");
        this.thirstLevel = t.getInt("level");
        this.dehydrationTimer = t.getInt("timer");
        this.dehydration = t.getFloat("dehydration");
    }

    public void writeNbt(NbtCompound tag) {
        NbtCompound t = new NbtCompound();
        t.putInt("level", this.thirstLevel);
        t.putInt("timer", this.dehydrationTimer);
        t.putFloat("dehydration", this.dehydration);
        tag.put("ewmedieval_thirst", t);
    }

    public int getThirstLevel() { return this.thirstLevel; }

    public void setThirstLevel(int level) { this.thirstLevel = Math.max(0, Math.min(20, level)); }

    public boolean isFull() { return this.thirstLevel >= 20; }
}
*/

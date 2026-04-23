package net.empire.ewmedieval.nutrition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class NutritionData {
    public float carbs;
    public float protein;
    public float fat;
    public float vitamins;
    public float minerals;

    public static final Codec<NutritionData> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.FLOAT.fieldOf("carbs").forGetter(d -> d.carbs),
            Codec.FLOAT.fieldOf("protein").forGetter(d -> d.protein),
            Codec.FLOAT.fieldOf("fat").forGetter(d -> d.fat),
            Codec.FLOAT.fieldOf("vitamins").forGetter(d -> d.vitamins),
            Codec.FLOAT.fieldOf("minerals").forGetter(d -> d.minerals)
        ).apply(instance, NutritionData::new)
    );

    public NutritionData() {
        // Start comfortably inside the sweet spot so new players don't immediately get penalties.
        this.carbs    = 200f;
        this.protein  = 200f;
        this.fat      = 150f;
        this.vitamins = 225f;
        this.minerals = 225f;
    }

    public NutritionData(float carbs, float protein, float fat, float vitamins, float minerals) {
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.vitamins = vitamins;
        this.minerals = minerals;
    }

    public float get(int index) {
        return switch (index) {
            case 0 -> carbs;
            case 1 -> protein;
            case 2 -> fat;
            case 3 -> vitamins;
            case 4 -> minerals;
            default -> 0f;
        };
    }

    public void set(int index, float value) {
        float clamped = Math.max(0f, Math.min(NutritionConstants.MAX, value));
        switch (index) {
            case 0 -> carbs = clamped;
            case 1 -> protein = clamped;
            case 2 -> fat = clamped;
            case 3 -> vitamins = clamped;
            case 4 -> minerals = clamped;
        }
    }

    public void add(int index, float amount) {
        set(index, get(index) + amount);
    }

    public void decay() {
        for (int i = 0; i < 5; i++) {
            set(i, get(i) - NutritionConstants.DECAY_RATE[i]);
        }
    }

    public NutritionData copy() {
        return new NutritionData(carbs, protein, fat, vitamins, minerals);
    }
}
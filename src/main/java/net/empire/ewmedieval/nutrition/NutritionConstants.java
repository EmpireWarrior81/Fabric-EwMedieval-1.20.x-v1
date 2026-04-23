package net.empire.ewmedieval.nutrition;

public final class NutritionConstants {
    public static final float MAX = 350f;

    // Index: 0=carbs, 1=protein, 2=fat, 3=vitamins, 4=minerals
    public static final float[] LOW_BAD   = {25f, 25f, 25f, 50f, 25f};
    public static final float[] HIGH_BAD  = {300f, 300f, 250f, 325f, 300f};
    public static final float[] SWEET_LOW  = {175f, 175f, 125f, 175f, 200f};
    public static final float[] SWEET_HIGH = {225f, 225f, 175f, 300f, 250f};

    // Decay per DECAY_INTERVAL ticks: carbs > protein > vitamins = minerals > fat
    public static final float[] DECAY_RATE = {0.6f, 0.5f, 0.3f, 0.4f, 0.4f};

    // Tick interval between decay steps (600 ticks = 30 seconds; ~240 decays per 2-hour day)
    public static final int DECAY_INTERVAL = 600;

    // Effect duration when applied — must exceed DECAY_INTERVAL so effects don't blink
    public static final int EFFECT_DURATION = 700;
}
package net.empire.ewmedieval.forging;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public enum ForgingQuality {
    POOR    ("poor",    Formatting.RED,          -0.30f, -0.15f),
    WELL    ("well",    Formatting.YELLOW,         0.10f,  0.05f),
    EXPERT  ("expert",  Formatting.GREEN,          0.30f,  0.15f),
    PERFECT ("perfect", Formatting.GOLD,           0.50f,  0.25f),
    MASTER  ("master",  Formatting.LIGHT_PURPLE,   0.50f,  0.30f),
    NONE    ("none",    Formatting.WHITE,           0.00f,  0.00f);

    private final String id;
    private final Formatting color;
    private final float durabilityMultiplier;
    private final float damageMultiplier;

    ForgingQuality(String id, Formatting color, float durabilityMultiplier, float damageMultiplier) {
        this.id = id;
        this.color = color;
        this.durabilityMultiplier = durabilityMultiplier;
        this.damageMultiplier = damageMultiplier;
    }

    public String getId() { return id; }
    public Formatting getColor() { return color; }
    public float getDurabilityMultiplier() { return durabilityMultiplier; }
    public float getDamageMultiplier() { return damageMultiplier; }

    public Text getDisplayName() {
        return Text.translatable("forging.ewmedieval.quality." + id).formatted(color);
    }

    public static ForgingQuality fromId(String id) {
        for (ForgingQuality q : values()) {
            if (q.id.equals(id)) return q;
        }
        return NONE;
    }

    /** Maps a minigame score (0.0–1.0) to a quality tier. */
    public static ForgingQuality fromScore(float score) {
        if (score >= 0.90f) return PERFECT;
        if (score >= 0.70f) return EXPERT;
        if (score >= 0.45f) return WELL;
        return POOR;
    }
}

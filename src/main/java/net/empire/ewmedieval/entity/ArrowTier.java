package net.empire.ewmedieval.entity;

public enum ArrowTier {
    IRON(1.2f),
    STEEL(1.5f);

    private final float damageMultiplier;

    ArrowTier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public byte toByte() {
        return (byte) ordinal();
    }

    public static ArrowTier fromByte(byte b) {
        ArrowTier[] values = values();
        return (b >= 0 && b < values.length) ? values[b] : IRON;
    }
}

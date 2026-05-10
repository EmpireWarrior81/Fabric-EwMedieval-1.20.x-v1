package net.empire.ewmedieval.forging;

import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.List;

public final class SmithingMinigameState {

    private SmithingMinigameState() {}

    public static boolean active = false;
    public static BlockPos anvilPos = null;

    // Arrow
    public static float arrowPosition       = 50f; // 0–100
    public static float arrowSpeed          = 1.2f;
    public static float maxArrowSpeed       = 4.0f;
    public static float speedIncreasePerHit = 0.2f;
    public static boolean movingRight       = true;

    // Zone boundaries (0–100 scale)
    public static int perfectZoneStart = 40;
    public static int perfectZoneEnd   = 60;
    public static int goodZoneStart    = 20;
    public static int goodZoneEnd      = 80;

    // Zone shrink state
    public static float zoneShrinkFactor   = 0.90f;
    public static float currentPerfectSize = 20f;
    public static float currentGoodSize    = 60f;
    public static float minPerfectSize     = 4f;

    // Hit counters
    public static int hitsRemaining = 0;
    public static int totalHits     = 0;
    public static int perfectHits   = 0;
    public static int goodHits      = 0;
    public static int missedHits    = 0;

    // Per-strike cooldown — prevents hold-right-click spam (one strike per N ticks)
    public static long lastStrikeTick       = -1L;
    public static final int STRIKE_COOLDOWN = 5; // ticks

    // Hit feedback popups — Overgeared-style animated labels
    public static final List<HitPopup> popups = new ArrayList<>();

    public static final class HitPopup {
        public final int type; // 0=miss, 1=good, 2=perfect
        public float age;
        public static final float MAX_AGE = 35f; // ticks

        HitPopup(int type) { this.type = type; }
    }

    /** Called from client tick — advances arrow and ages popups. */
    public static void tick() {
        popups.removeIf(p -> { p.age++; return p.age >= HitPopup.MAX_AGE; });
        if (!active) return;

        if (arrowPosition >= 100f) movingRight = false;
        else if (arrowPosition <= 1f) movingRight = true;

        float delta = arrowSpeed * (movingRight ? 1f : -1f);
        arrowPosition = Math.max(1f, Math.min(arrowPosition + delta, 100f));
    }

    /** Called when the server's start packet arrives. */
    public static void start(BlockPos pos, int hits, float initialSpeed, float goodZoneSize,
                              float perfectZoneSize, float shrink,
                              float speedIncrease, float maxSpeed) {
        anvilPos            = pos;
        active              = true;
        totalHits           = hits;
        hitsRemaining       = hits;
        perfectHits         = 0;
        goodHits            = 0;
        missedHits          = 0;
        arrowPosition       = 50f;
        movingRight         = true;
        arrowSpeed          = initialSpeed;
        speedIncreasePerHit = speedIncrease;
        maxArrowSpeed       = maxSpeed;
        zoneShrinkFactor    = shrink;
        currentPerfectSize  = perfectZoneSize;
        currentGoodSize     = goodZoneSize;
        lastStrikeTick      = -1L;

        randomizeCenter();
    }

    /** 2 = perfect, 1 = good, 0 = miss. */
    public static int getHitType() {
        if (arrowPosition >= perfectZoneStart && arrowPosition <= perfectZoneEnd) return 2;
        if (arrowPosition >= goodZoneStart    && arrowPosition <= goodZoneEnd)    return 1;
        return 0;
    }

    /** Update client state after a strike. */
    public static void onStrike(int hitType) {
        popups.add(new HitPopup(hitType));

        switch (hitType) {
            case 2 -> perfectHits++;
            case 1 -> goodHits++;
            default -> missedHits++;
        }
        hitsRemaining--;

        arrowSpeed = Math.min(maxArrowSpeed, arrowSpeed + speedIncreasePerHit);

        currentPerfectSize = Math.max(minPerfectSize, currentPerfectSize * zoneShrinkFactor);
        currentGoodSize    = Math.max(currentPerfectSize * 3f, currentGoodSize * zoneShrinkFactor);

        shrinkAndShift();

        if (hitsRemaining <= 0) active = false;
    }

    public static void reset() {
        active             = false;
        anvilPos           = null;
        arrowPosition      = 50f;
        movingRight        = true;
        hitsRemaining      = 0;
        totalHits          = 0;
        perfectHits        = 0;
        goodHits           = 0;
        missedHits         = 0;
        currentPerfectSize = 20f;
        currentGoodSize    = 60f;
        lastStrikeTick     = -1L;
        popups.clear();
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private static void randomizeCenter() {
        float center = 20f + (float)(Math.random() * 60f);
        applyCenter(center);
    }

    private static void shrinkAndShift() {
        int oldPerfectStart = perfectZoneStart;
        int oldPerfectEnd   = perfectZoneEnd;

        for (int attempts = 0; attempts < 30; attempts++) {
            float w        = (float) Math.random();
            float weighted = (float) Math.pow(w, 1.5);
            float center   = 50f + (weighted - 0.5f) * 100f;
            center = Math.max(10f, Math.min(90f, center));

            int pStart = clamp(Math.round(center - currentPerfectSize / 2f), 0, 100);
            int pEnd   = clamp(Math.round(center + currentPerfectSize / 2f), 0, 100);

            float newCenter = (pStart + pEnd) / 2f;
            float oldCenter = (oldPerfectStart + oldPerfectEnd) / 2f;
            if (Math.abs(newCenter - oldCenter) >= 5f &&
                Math.abs(pStart - oldPerfectStart) >= 3f &&
                Math.abs(pEnd   - oldPerfectEnd)   >= 3f) {
                applyCenter(center);
                return;
            }
        }
        randomizeCenter();
    }

    private static void applyCenter(float center) {
        int halfP = Math.round(currentPerfectSize / 2f);
        int halfG = Math.round(currentGoodSize    / 2f);
        perfectZoneStart = clamp(Math.round(center) - halfP, 0, 100);
        perfectZoneEnd   = clamp(Math.round(center) + halfP, 0, 100);
        goodZoneStart    = clamp(Math.round(center) - halfG, 0, 100);
        goodZoneEnd      = clamp(Math.round(center) + halfG, 0, 100);
    }

    private static int clamp(int v, int lo, int hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}

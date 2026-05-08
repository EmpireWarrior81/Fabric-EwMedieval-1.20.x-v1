package net.empire.ewmedieval.season;

// 12 periods: Early/Mid/Late × Spring/Summer/Autumn/Winter, 8 in-game days each = 96 days/year.
// Rates: game-ticks advanced per real server-tick (fractional). Formula: 10.0 / dayMinutes.
// Total day+night is always 120 real minutes so every period sums to the same 2-hour cycle.
//
// Precipitation: Spring=DRY, Summer=WET, EarlyAutumn/MidAutumn=WET, LateAutumn=NORMAL, Winter=DRY
// Breeding season: animals can only breed in Spring and Summer.
public enum SeasonPeriod {
    EARLY_SPRING ("Early Spring",  10.0 / 55, 10.0 / 65, PrecipitationLevel.DRY,    true),
    MID_SPRING   ("Mid Spring",    10.0 / 62, 10.0 / 58, PrecipitationLevel.DRY,    true),
    LATE_SPRING  ("Late Spring",   10.0 / 68, 10.0 / 52, PrecipitationLevel.DRY,    true),
    EARLY_SUMMER ("Early Summer",  10.0 / 75, 10.0 / 45, PrecipitationLevel.WET,    true),
    MID_SUMMER   ("Mid Summer",    10.0 / 80, 10.0 / 40, PrecipitationLevel.WET,    true),
    LATE_SUMMER  ("Late Summer",   10.0 / 75, 10.0 / 45, PrecipitationLevel.WET,    true),
    EARLY_AUTUMN ("Early Autumn",  10.0 / 68, 10.0 / 52, PrecipitationLevel.WET,    false),
    MID_AUTUMN   ("Mid Autumn",    10.0 / 62, 10.0 / 58, PrecipitationLevel.WET,    false),
    LATE_AUTUMN  ("Late Autumn",   10.0 / 55, 10.0 / 65, PrecipitationLevel.NORMAL, false),
    EARLY_WINTER ("Early Winter",  10.0 / 48, 10.0 / 72, PrecipitationLevel.DRY,    false),
    MID_WINTER   ("Mid Winter",    10.0 / 45, 10.0 / 75, PrecipitationLevel.DRY,    false),
    LATE_WINTER  ("Late Winter",   10.0 / 48, 10.0 / 72, PrecipitationLevel.DRY,    false);

    public final String displayName;
    public final double dayRate;    // game ticks per real tick during daytime (time < 12000)
    public final double nightRate;  // game ticks per real tick during nighttime (time >= 12000)
    public final PrecipitationLevel precipitation;
    public final boolean isBreedingSeason;

    public static final int DAYS_PER_PERIOD = 8;
    public static final int TOTAL_YEAR_DAYS = 96; // 12 * 8

    SeasonPeriod(String displayName, double dayRate, double nightRate,
                 PrecipitationLevel precipitation, boolean isBreedingSeason) {
        this.displayName      = displayName;
        this.dayRate          = dayRate;
        this.nightRate        = nightRate;
        this.precipitation    = precipitation;
        this.isBreedingSeason = isBreedingSeason;
    }
}

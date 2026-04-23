package net.empire.ewmedieval.nutrition;

public record NutritionFoodValues(float carbs, float protein, float fat, float vitamins, float minerals) {
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
}
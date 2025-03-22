public class ScoreService {
    public int getIngredientScore(String name) {
        return switch (name) {
            case "Vanilla" -> -4;
            case "Chocolate" -> -5;
            case "Strawberry" -> -3;
            case "Caramel" -> -6;
            case "Banana" -> 8;
            case "Apple" -> 9;
            case "Kiwi" -> 8;
            case "Pineapple" -> 7;
            case "Berry" -> 8;
            case "Mango" -> 10;
            case "Nuts" -> 5;
            case "Sprinkles" -> -2;
            case "Choco Chips" -> -4;
            case "Granola" -> 6;
            case "Coconut Flakes" -> 5;
            default -> 0;
        };
    }

    public int calculateTotalScore(java.util.List<Ingredient> ingredients) {
        return ingredients.stream()
                .mapToInt(ingredient -> getIngredientScore(ingredient.getName()))
                .sum();
    }
}
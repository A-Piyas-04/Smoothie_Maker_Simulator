public class ScoreService {
    public int getIngredientScore(String name) {
        return switch (name) {
            case "Natural Milk" -> 6;
            case "Almond Milk" -> 8;
            case "Vanilla" -> -3;
            case "Chocolate" -> -5;
            case "Strawberry" -> -6;
            case "Caramel" -> -9;
            case "Banana" -> 6;
            case "Apple" -> 9;
            case "Kiwi" -> 7;
            case "Pineapple" -> 5;
            case "Berry" -> 8;
            case "Mango" -> 10;
            case "Nuts" -> 5;
            case "Sprinkles" -> -2;
            case "Choco Chips" -> -4;
            case "Granola" -> 6;
            case "Coconut Flakes" -> 4;
            default -> 0;
        };
    }

    public int calculateTotalScore(java.util.List<Ingredient> ingredients) {
        return ingredients.stream()
                .mapToInt(ingredient -> getIngredientScore(ingredient.getName()))
                .sum();
    }
}
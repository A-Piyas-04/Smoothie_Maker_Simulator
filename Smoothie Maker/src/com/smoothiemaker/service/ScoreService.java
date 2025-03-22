package com.smoothiemaker.service;

import com.smoothiemaker.model.Ingredient;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ScoreService {
    private final Map<String, Integer> ingredientScores;

    public ScoreService() {
        ingredientScores = new HashMap<>();
        initializeScores();
    }

    private void initializeScores() {
        // Flavors
        ingredientScores.put("Almond Milk", 10);
        ingredientScores.put("Natural Milk", 8);
        ingredientScores.put("Vanilla", 12);
        ingredientScores.put("Chocolate", 15);
        ingredientScores.put("Strawberry", 12);
        ingredientScores.put("Caramel", 14);

        // Fruits
        ingredientScores.put("Banana", 8);
        ingredientScores.put("Apple", 6);
        ingredientScores.put("Kiwi", 10);
        ingredientScores.put("Mango", 12);
        ingredientScores.put("Pineapple", 10);
        ingredientScores.put("Berry", 14);

        // Toppings
        ingredientScores.put("Nuts", 8);
        ingredientScores.put("Sprinkles", 6);
        ingredientScores.put("Choco Chips", 10);
        ingredientScores.put("Granola", 8);
        ingredientScores.put("Coconut Flakes", 7);
    }

    public int getIngredientScore(String ingredientName) {
        return ingredientScores.getOrDefault(ingredientName, 0);
    }

    public int calculateTotalScore(List<Ingredient> ingredients) {
        return ingredients.stream()
                .mapToInt(Ingredient::getScore)
                .sum();
    }
}
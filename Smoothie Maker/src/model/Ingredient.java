package model;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private String name;
    private String category;
    private String imageUrl;

    public Ingredient(String name, String category, String imageUrl) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public static List<Ingredient> loadSampleIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        
        // Fruits
        ingredients.add(new Ingredient("Strawberry", "Fruits", "https://example.com/strawberry.png"));
        ingredients.add(new Ingredient("Banana", "Fruits", "https://example.com/banana.png"));
        ingredients.add(new Ingredient("Mango", "Fruits", "https://example.com/mango.png"));

        // Vegetables
        ingredients.add(new Ingredient("Spinach", "Vegetables", "https://example.com/spinach.png"));
        ingredients.add(new Ingredient("Kale", "Vegetables", "https://example.com/kale.png"));
        ingredients.add(new Ingredient("Celery", "Vegetables", "https://example.com/celery.png"));

        // Add-ons
        ingredients.add(new Ingredient("Yogurt", "Add-ons", "https://example.com/yogurt.png"));
        ingredients.add(new Ingredient("Honey", "Add-ons", "https://example.com/honey.png"));
        ingredients.add(new Ingredient("Chia Seeds", "Add-ons", "https://example.com/chia.png"));

        return ingredients;
    }
}
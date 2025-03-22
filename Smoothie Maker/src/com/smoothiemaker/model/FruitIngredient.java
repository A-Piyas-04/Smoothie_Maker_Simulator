package com.smoothiemaker.model;

public class FruitIngredient extends Ingredient {
    public FruitIngredient(String name, int score) {
        super(name, score);
    }
    
    @Override
    public IngredientType getType() {
        return IngredientType.FRUIT;
    }
}
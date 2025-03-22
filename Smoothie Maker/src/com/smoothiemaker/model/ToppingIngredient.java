package com.smoothiemaker.model;

public class ToppingIngredient extends Ingredient {
    public ToppingIngredient(String name, int score) {
        super(name, score);
    }
    
    @Override
    public IngredientType getType() {
        return IngredientType.TOPPING;
    }
}
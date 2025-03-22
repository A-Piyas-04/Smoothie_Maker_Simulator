public abstract class Ingredient {
    private String name;
    private int score;
    
    public Ingredient(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    public abstract IngredientType getType();
    
    public enum IngredientType {
        FLAVOR,
        TOPPING,
        FRUIT
    }
}
public class FlavorIngredient extends Ingredient {
    public FlavorIngredient(String name, int score) {
        super(name, score);
    }
    
    @Override
    public IngredientType getType() {
        return IngredientType.FLAVOR;
    }
}
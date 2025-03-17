import java.util.ArrayList;
import java.util.List;

class BlenderController {
    private List<Ingredient> ingredients = new ArrayList<>();
    private Mug mug;
    private int lastScore;
    
    public String blend(String smoothieName) {
        if (ingredients.isEmpty()) return "Add ingredients first!";
        
        int totalScore = 0;
        StringBuilder result = new StringBuilder("Blending " + smoothieName + " in " + mug.getType() + ":\n");
        for (Ingredient i : ingredients) {
            result.append("- Mixing ").append(i.getName()).append("\n");
            totalScore += i.getScore();
        }
        this.lastScore = totalScore;
        result.append("Blend complete! Enjoy your " + smoothieName);
        ingredients.clear();
        return result.toString();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    
    public void setMug(Mug mug) {
        this.mug = mug;
    }
    
    public boolean hasIngredients() {
        return !ingredients.isEmpty();
    }

    public int getLastScore() {
        return lastScore;
    }
}
import java.util.ArrayList;
import java.util.List;

class BlenderController {
    private List<Ingredient> ingredients = new ArrayList<>();
    private Mug mug;
    private BlenderAnimationPanel blenderPanel;

    public BlenderController() {
        blenderPanel = new BlenderAnimationPanel();
    }

    public BlenderAnimationPanel getBlenderPanel() {
        return blenderPanel;
    }
    
    private int lastScore = 0;

    public String blend(String smoothieName) {
        if (ingredients.isEmpty()) return "Add ingredients first!";
        
        lastScore = calculateScore();
        StringBuilder result = new StringBuilder("Blending " + smoothieName + " in " + mug.getType() + ":\n");
        for (Ingredient i : ingredients) {
            result.append("- Mixing ").append(i.getName()).append("\n");
        }
        result.append("\n=========== BLEND COMPLETE! =========== \n\nEnjoy your - [" + smoothieName +"]" + "\n\n");
        ingredients.clear();
        return result.toString();
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
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

    private final ScoreService scoreService = new ScoreService();

    public int calculateScore() {
        if (ingredients.isEmpty()) {
            return lastScore;
        }
        return scoreService.calculateTotalScore(ingredients);
    }
}
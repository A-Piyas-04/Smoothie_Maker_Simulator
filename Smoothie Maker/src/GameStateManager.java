import java.util.List;

public class GameStateManager {
    private BlenderController blenderController;
    private Mug currentMug;
    private int score;
    private ScoreService scoreService;

    public GameStateManager() {
        blenderController = new BlenderController();
        scoreService = new ScoreService();
        score = 0;
    }

    public void setMug(Mug mug) {
        this.currentMug = mug;
        blenderController.setMug(mug);
    }

    public Mug getCurrentMug() {
        return currentMug;
    }

    public void addIngredient(String category, String item) {
        Ingredient ingredient = switch(category) {
            case "Flavors" -> new FlavorIngredient(item, scoreService.getIngredientScore(item));
            case "Fruits" -> new FruitIngredient(item, scoreService.getIngredientScore(item));
            case "Toppings" -> new ToppingIngredient(item, scoreService.getIngredientScore(item));
            default -> throw new IllegalStateException("Unexpected category: " + category);
        };
        blenderController.addIngredient(ingredient);
    }

    public boolean hasIngredients() {
        return blenderController.hasIngredients();
    }

    public String blend(String smoothieName) {
        String result = blenderController.blend(smoothieName);
        score += blenderController.calculateScore();
        return result;
    }

    public int getScore() {
        return score;
    }

    public BlenderController getBlenderController() {
        return blenderController;
    }
}
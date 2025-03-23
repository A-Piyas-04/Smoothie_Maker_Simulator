import java.util.function.Consumer;

public class GameStateManager {

    private BlenderController blenderController;
    private Mug currentMug;
    private int score;
    private ScoreService scoreService;
    private Consumer<Integer> scoreUpdateListener;
    private String playerName;
    private StatusLogger statusLogger;

    public void setScoreUpdateListener(Consumer<Integer> listener) {
        this.scoreUpdateListener = listener;
        if (listener != null) {
            listener.accept(score);
        }
    }

    public GameStateManager() {
        blenderController = new BlenderController();
        scoreService = new ScoreService();
        statusLogger = new StatusLogger();
        score = 0;
        playerName = "Player";
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
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
        int blendScore = blenderController.calculateScore();
        score += blendScore;
        if (scoreUpdateListener != null) {
            scoreUpdateListener.accept(score);
        }
        statusLogger.logBlend(playerName, smoothieName, blenderController.getIngredients(), blendScore);
        return result;
    }

    public int getScore() {
        return score;
    }

    public BlenderController getBlenderController() {
        return blenderController;
    }
}
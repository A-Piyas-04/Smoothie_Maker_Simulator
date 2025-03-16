package controller;

import model.GameState;
import model.Ingredient;
import view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private MainFrame mainFrame;
    private GameState gameState;
    private List<Ingredient> allIngredients;

    public List<Ingredient> getIngredientsByCategory(String category) {
        List<Ingredient> filtered = new ArrayList<>();
        for (Ingredient ingredient : allIngredients) {
            if (ingredient.getCategory().equalsIgnoreCase(category)) {
                filtered.add(ingredient);
            }
        }
        return filtered;
    }
    
    public GameController() {
        gameState = new GameState();
        initializeIngredients();
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
    }

    private void initializeIngredients() {
        allIngredients = new ArrayList<>();
        allIngredients.add(new Ingredient("Strawberry", "Fruits", "https://example.com/strawberry.png"));
        allIngredients.add(new Ingredient("Banana", "Fruits", "https://example.com/banana.png"));
        allIngredients.add(new Ingredient("Spinach", "Vegetables", "https://example.com/spinach.png"));
        allIngredients.add(new Ingredient("Honey", "Add-ons", "https://example.com/honey.png"));
    }
    
    public void startGame() {
        // Initialize game components
        mainFrame.switchView("MAIN_MENU");
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
    public void updateScore(int points) {
        gameState.setScore(gameState.getScore() + points);
    }
    
    public void updateCoins(int amount) {
        gameState.setCoins(gameState.getCoins() + amount);
    }
}
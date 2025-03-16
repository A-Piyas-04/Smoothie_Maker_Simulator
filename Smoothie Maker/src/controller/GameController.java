package controller;

import model.GameState;
import model.Ingredient;
import model.Order;
import view.MainFrame;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private MainFrame mainFrame;
    private GameState gameState;
    private List<Ingredient> allIngredients;
    private List<Order> activeOrders;
    private Timer orderGenerationTimer;

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
        activeOrders = new ArrayList<>();
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
        startOrderSystem();
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

    private void startOrderSystem() {
        orderGenerationTimer = new Timer(30000, e -> generateRandomOrder());
        orderGenerationTimer.start();

        new Timer(1000, e -> checkOrderExpiration()).start();
    }

    private void generateRandomOrder() {
        List<Ingredient> orderIngredients = new ArrayList<>();
        int ingredientCount = 2 + (int)(Math.random() * 3);
        
        for(int i = 0; i < ingredientCount; i++) {
            orderIngredients.add(allIngredients.get(
                (int)(Math.random() * allIngredients.size())));
        }

        int timeLimit = 30 + (int)(Math.random() * 31);
        activeOrders.add(new Order(orderIngredients, timeLimit));
        mainFrame.getOrderPanel().updateOrders(getActiveOrders());
    }

    private void checkOrderExpiration() {
        List<Order> expiredOrders = activeOrders.stream()
            .filter(Order::isExpired)
            .toList();

        expiredOrders.forEach(order -> {
            updateScore(-20);
            updateCoins(-5);
        });

        activeOrders.removeAll(expiredOrders);
        mainFrame.getOrderPanel().updateOrders(getActiveOrders());
    }

    public List<Order> getActiveOrders() {
        return activeOrders.stream()
            .filter(order -> !order.isExpired())
            .toList();
    }

    public void completeOrder(Order order) {
        order.completeOrder();
        activeOrders.remove(order);
        updateScore(50);
        updateCoins(15);
    }
}
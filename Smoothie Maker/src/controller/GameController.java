package controller;

import model.GameState;
import view.MainFrame;

public class GameController {
    private MainFrame mainFrame;
    private GameState gameState;
    
    public GameController() {
        gameState = new GameState();
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
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
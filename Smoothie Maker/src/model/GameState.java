package model;

public class GameState {
    private int score;
    private int coins;
    
    public GameState() {
        score = 0;
        coins = 0;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getCoins() {
        return coins;
    }
    
    public void setCoins(int coins) {
        this.coins = coins;
    }
}
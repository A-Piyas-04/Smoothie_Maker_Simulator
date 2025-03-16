package game.entity;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Represents a collectible superfood item in the game.
 * Different types of food provide different power-ups and scoring values.
 */
public class Food extends GameObject {
    public enum FoodType {
        BERRY("Berry", Player.PowerUpType.SPEED_BOOST, 100),
        LEAFY_GREEN("Leafy Green", Player.PowerUpType.HEALTH_BOOST, 150),
        NUT("Nut", Player.PowerUpType.JUMP_BOOST, 200);

        private final String name;
        private final Player.PowerUpType powerUpType;
        private final int scoreValue;

        FoodType(String name, Player.PowerUpType powerUpType, int scoreValue) {
            this.name = name;
            this.powerUpType = powerUpType;
            this.scoreValue = scoreValue;
        }

        public String getName() { return name; }
        public Player.PowerUpType getPowerUpType() { return powerUpType; }
        public int getScoreValue() { return scoreValue; }
    }

    private final FoodType type;
    private boolean isCollected;
    private static final int POWER_UP_DURATION = 300; // 5 seconds at 60 FPS

    public Food(float x, float y, FoodType type) {
        super(x, y, 20, 20); // Default food size
        this.type = type;
        this.isCollected = false;
    }

    @Override
    public void update() {
        // Add floating animation or effects here
    }

    @Override
    public void render(Graphics g) {
        if (!isCollected) {
            // Temporary rendering - replace with sprites later
            switch (type) {
                case BERRY:
                    g.setColor(Color.RED);
                    break;
                case LEAFY_GREEN:
                    g.setColor(Color.GREEN);
                    break;
                case NUT:
                    g.setColor(Color.ORANGE);
                    break;
            }
            g.fillOval((int)x, (int)y, width, height);
        }
    }

    /**
     * Applies the food's power-up effect to the player.
     *
     * @param player The player to apply the power-up to
     * @return The score value of this food
     */
    public int collect(Player player) {
        if (!isCollected) {
            isCollected = true;
            player.applyPowerUp(type.getPowerUpType(), POWER_UP_DURATION);
            return type.getScoreValue();
        }
        return 0;
    }

    // Getters
    public FoodType getType() { return type; }
    public boolean isCollected() { return isCollected; }
}
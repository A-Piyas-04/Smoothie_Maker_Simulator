package game.entity;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Represents the player character in the game.
 * Handles movement, collision detection, and power-up states.
 */
public class Player extends GameObject {
    private static final float MOVE_SPEED = 5.0f;
    private static final float JUMP_FORCE = -12.0f;
    private static final float GRAVITY = 0.5f;
    private static final float MAX_FALL_SPEED = 10.0f;

    private float velocityX;
    private float velocityY;
    private boolean isJumping;
    private boolean isGrounded;
    private int health;
    private PowerUpType activePowerUp;
    private int powerUpDuration;

    public enum PowerUpType {
        NONE,
        SPEED_BOOST,
        JUMP_BOOST,
        HEALTH_BOOST
    }

    public Player(float x, float y) {
        super(x, y, 40, 60); // Default player size
        this.health = 100;
        this.activePowerUp = PowerUpType.NONE;
        reset();
    }

    @Override
    public void update() {
        // Apply gravity
        if (!isGrounded) {
            velocityY = Math.min(velocityY + GRAVITY, MAX_FALL_SPEED);
        }

        // Update position
        x += velocityX;
        y += velocityY;

        // Update hitbox
        updateHitbox();

        // Update power-up duration
        if (powerUpDuration > 0) {
            powerUpDuration--;
            if (powerUpDuration == 0) {
                activePowerUp = PowerUpType.NONE;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        // Temporary rendering - replace with sprite later
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, width, height);

        // Render power-up effect
        if (activePowerUp != PowerUpType.NONE) {
            g.setColor(new Color(255, 255, 0, 100));
            g.drawRect((int)x - 2, (int)y - 2, width + 4, height + 4);
        }
    }

    public void moveLeft() {
        velocityX = -MOVE_SPEED * (activePowerUp == PowerUpType.SPEED_BOOST ? 1.5f : 1.0f);
    }

    public void moveRight() {
        velocityX = MOVE_SPEED * (activePowerUp == PowerUpType.SPEED_BOOST ? 1.5f : 1.0f);
    }

    public void jump() {
        if (isGrounded) {
            velocityY = JUMP_FORCE * (activePowerUp == PowerUpType.JUMP_BOOST ? 1.3f : 1.0f);
            isGrounded = false;
            isJumping = true;
        }
    }

    public void stopMoving() {
        velocityX = 0;
    }

    public void applyPowerUp(PowerUpType type, int duration) {
        this.activePowerUp = type;
        this.powerUpDuration = duration;

        if (type == PowerUpType.HEALTH_BOOST) {
            health = Math.min(health + 25, 100);
            activePowerUp = PowerUpType.NONE;
            powerUpDuration = 0;
        }
    }

    public void takeDamage(int amount) {
        health = Math.max(0, health - amount);
    }

    public void reset() {
        velocityX = 0;
        velocityY = 0;
        isJumping = false;
        isGrounded = false;
        activePowerUp = PowerUpType.NONE;
        powerUpDuration = 0;
    }

    // Getters and setters
    public boolean isGrounded() { return isGrounded; }
    public void setGrounded(boolean grounded) { 
        this.isGrounded = grounded;
        if (grounded) {
            velocityY = 0;
            isJumping = false;
        }
    }

    public int getHealth() { return health; }
    public PowerUpType getActivePowerUp() { return activePowerUp; }
    public int getPowerUpDuration() { return powerUpDuration; }
}
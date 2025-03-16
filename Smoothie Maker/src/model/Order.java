package model;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Ingredient;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderId = 1;
    private final int orderId;
    private List<Ingredient> requiredIngredients;
    private Timer timer;
    private int timeRemaining;
    private boolean expired;

    public Order(List<Ingredient> ingredients, int timeLimit) {
        this.orderId = nextOrderId++;
        this.requiredIngredients = new ArrayList<>(ingredients);
        this.timeRemaining = timeLimit;
        this.expired = false;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                if(timeRemaining <= 0) {
                    timer.stop();
                    expired = true;
                }
            }
        });
        timer.start();
    }

    public int getOrderId() { return orderId; }
    public List<Ingredient> getRequiredIngredients() { return requiredIngredients; }
    public int getTimeRemaining() { return timeRemaining; }
    public boolean isExpired() { return expired; }

    public void completeOrder() {
        timer.stop();
    }
}
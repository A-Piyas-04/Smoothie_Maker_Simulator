import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IngredientPanel extends JPanel {
    private GameStateManager gameStateManager;
    private StatusPanel statusPanel;

    public IngredientPanel(GameStateManager gameStateManager, StatusPanel statusPanel) {
        this.gameStateManager = gameStateManager;
        this.statusPanel = statusPanel;
        setLayout(new GridLayout(1, 3, 10, 10));
        initializeIngredientCategories();
    }

    private void initializeIngredientCategories() {
        add(createCategoryPanel("Flavors", List.of("Almond Milk", "Natural Milk", "Vanilla", "Chocolate", "Strawberry", "Caramel")));
        add(createCategoryPanel("Fruits", List.of("Banana", "Apple", "Kiwi", "Mango", "Pineapple", "Berry")));
        add(createCategoryPanel("Toppings", List.of("Nuts", "Sprinkles", "Choco Chips", "Granola", "Coconut Flakes")));
    }

    private JPanel createCategoryPanel(String title, List<String> items) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));

        for (String item : items) {
            JButton btn = new JButton(item);
            btn.addActionListener(e -> {
                gameStateManager.addIngredient(title, item);
                statusPanel.updateStatus("Added: " + item);
            });
            buttonPanel.add(btn);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }
}
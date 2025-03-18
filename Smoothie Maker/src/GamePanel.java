import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel{
    private BlenderController blenderController;
    private JTextArea statusArea;
    private Mug currentMug;
    private BlenderAnimationPanel blenderPanel;
    private JLabel scoreLabel;

    public GamePanel() {
        blenderController = new BlenderController();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 760));
        
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.add(createIngredientsPanel());
        mainPanel.add(createMugSelectionPanel());
        mainPanel.add(createControlPanel());
        
        add(mainPanel, BorderLayout.CENTER);
        blenderPanel = new BlenderAnimationPanel();
        blenderPanel.setPreferredSize(new Dimension(300, 200));
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scorePanel.add(scoreLabel);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(blenderPanel, BorderLayout.CENTER);
        northPanel.add(scorePanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
        
        statusArea = new JTextArea(5, 20);
        statusArea.setEditable(false);
        add(new JScrollPane(statusArea), BorderLayout.SOUTH);
    }

    private JPanel createIngredientsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.add(createCategoryPanel("Flavors", Arrays.asList("Vanilla", "Chocolate", "Strawberry", "Caramel")));
        panel.add(createCategoryPanel("Fruits", Arrays.asList("Banana", "Apple", "Kiwi", "Mango", "Pineapple", "Berry")));
        panel.add(createCategoryPanel("Toppings", Arrays.asList("Nuts", "Sprinkles", "Choco Chips", "Granola", "Coconut Flakes")));
        return panel;
    }

    private JPanel createCategoryPanel(String title, List<String> items) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2)); // 2 columns for better spacing
        
        for (String item : items) {
            JButton btn = new JButton(item);
            btn.addActionListener(e -> {
                blenderController.addIngredient(new BasicIngredient(item, getIngredientScore(item)));
                updateStatus("Added: " + item);
            });
            buttonPanel.add(btn);
        }
        
        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createMugSelectionPanel() {
        JPanel panel = new JPanel();
        JComboBox<String> mugSelector = new JComboBox<>(new String[]{"Plastic Mug", "Glass Mug"});
        mugSelector.addActionListener(e -> {
            currentMug = MugFactory.createMug((String) mugSelector.getSelectedItem());
            updateStatus("Selected: " + currentMug.getType());
        });
        panel.add(new JLabel("Select Mug:"));
        panel.add(mugSelector);
        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JTextField nameField = new JTextField();
        inputPanel.add(new JLabel("Smoothie Name:"));
        inputPanel.add(nameField);
        
        JButton blendBtn = new JButton("Blend!");
        blendBtn.addActionListener(e -> {
            if (nameField.getText().trim().isEmpty()) {
                updateStatus("Please name your smoothie first!");
                return;
            }
            if (currentMug == null || !blenderController.hasIngredients()) {
                updateStatus("Please select a mug and add ingredients first!");
                return;
            }
            blenderPanel.startAnimation();
            performBlending(nameField.getText().trim());
            nameField.setText("");
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(blendBtn, BorderLayout.CENTER);
        return panel;
    }

    private void performBlending(String smoothieName) {
        if (currentMug == null) {
            updateStatus("Please select a mug first!");
            return;
        }
        
        blenderController.setMug(currentMug);
        String result = blenderController.blend(smoothieName);
        updateStatus(result);
        scoreLabel.setText("Score: " + blenderController.getLastScore());
    }

    private void updateStatus(String message) {
        statusArea.append(message + "\n");
    }
    private int getIngredientScore(String name) {
        switch (name) {
            case "Vanilla": return -4;
            case "Chocolate": return -5;
            case "Strawberry": return -3;
            case "Caramel": return -6;
            case "Banana": return 8;
            case "Apple": return 9;
            case "Kiwi": return 8;
            case "Pineapple": return 7;
            case "Berry": return 8;
            case "Mango": return 10;
            case "Nuts": return 5;
            case "Sprinkles": return -2;
            case "Choco Chips": return -4;
            case "Granola": return 6;
            case "Coconut Flakes": return 5;
            default: return 0;
        }
    }
}

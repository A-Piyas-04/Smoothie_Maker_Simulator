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

    public GamePanel() {
        blenderController = new BlenderController();
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.add(createIngredientsPanel());
        mainPanel.add(createMugSelectionPanel());
        mainPanel.add(createControlPanel());
        
        add(mainPanel, BorderLayout.CENTER);
        blenderPanel = new BlenderAnimationPanel();
        add(blenderPanel, BorderLayout.NORTH);
        
        statusArea = new JTextArea(5, 20);
        statusArea.setEditable(false);
        add(new JScrollPane(statusArea), BorderLayout.SOUTH);
    }

    private JPanel createIngredientsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.add(createCategoryPanel("Flavors", Arrays.asList("Vanilla", "Chocolate", "Strawberry")));
        panel.add(createCategoryPanel("Fruits", Arrays.asList("Banana", "Berry", "Mango")));
        panel.add(createCategoryPanel("Toppings", Arrays.asList("Nuts", "Sprinkles", "Choco Chips")));
        return panel;
    }

    private JPanel createCategoryPanel(String title, List<String> items) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JPanel buttonPanel = new JPanel(new GridLayout(items.size(), 1));
        
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
        JPanel panel = new JPanel();
        JButton blendBtn = new JButton("Blend!");
        blendBtn.addActionListener(e -> {
            if (currentMug == null || !blenderController.hasIngredients()) {
                updateStatus("Please select a mug and add ingredients first!");
                return;
            }
            blenderPanel.startAnimation();
            performBlending();
        });
        panel.add(blendBtn);
        return panel;
    }

    private void performBlending() {
        if (currentMug == null) {
            updateStatus("Please select a mug first!");                             
            return;
        }
        
        blenderController.setMug(currentMug);
        String result = blenderController.blend();
        updateStatus(result);
    }

    private void updateStatus(String message) {
        statusArea.append(message + "\n");
    }
    private int getIngredientScore(String name) {
        switch (name) {
            case "Vanilla":
            case "Chocolate": return -5;
            case "Strawberry": return -3;
            case "Banana": return 10;
            case "Berry": return 8;
            case "Mango": return 7;
            case "Nuts": return 5;
            case "Sprinkles": return -2;
            case "Choco Chips": return -4;
            default: return 0;
        }
    }
}

interface Ingredient {
    String getName();
    int getScore();
}

class BasicIngredient implements Ingredient {
    private String name;
    private int score;
    
    public BasicIngredient(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getScore() {
        return score;
    }
}

class BlenderController {
    private List<Ingredient> ingredients = new ArrayList<>();
    private Mug mug;
    
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    
    public void setMug(Mug mug) {
        this.mug = mug;
    }
    
    public String blend() {
        if (ingredients.isEmpty()) return "Add ingredients first!";
        
        int totalScore = 0;
        StringBuilder result = new StringBuilder("Blending in " + mug.getType() + ":\n");
        for (Ingredient i : ingredients) {
            result.append("- Mixing ").append(i.getName()).append("\n");
            totalScore += i.getScore();
        }
        result.append("Blend complete! Score: " + totalScore + ". Enjoy your ").append(mug.getType()).append(" smoothie!");
        ingredients.clear();
        return result.toString();
    }

    public boolean hasIngredients() {
        return !ingredients.isEmpty();
    }
}

interface Mug {
    String getType();
}

class PlasticMug implements Mug {
    @Override
    public String getType() { return "Plastic Mug"; }
}

class GlassMug implements Mug {
    @Override
    public String getType() { return "Glass Mug"; }
}

class MugFactory {
    public static Mug createMug(String type) {
        if (type.contains("Plastic")) return new PlasticMug();
        if (type.contains("Glass")) return new GlassMug();
        throw new IllegalArgumentException("Invalid mug type");
    }

}

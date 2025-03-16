package view;

import controller.GameController;
import model.Ingredient;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

public class IngredientSelectionPanel extends JPanel {
    private GameController controller;
    
    public IngredientSelectionPanel(GameController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        
        JTabbedPane categoryTabs = new JTabbedPane();
        categoryTabs.setFont(new Font("Arial", Font.BOLD, 16));

        addTabWithIngredients(categoryTabs, "Fruits", controller.getIngredientsByCategory("Fruits"));
        addTabWithIngredients(categoryTabs, "Vegetables", controller.getIngredientsByCategory("Vegetables"));
        addTabWithIngredients(categoryTabs, "Add-ons", controller.getIngredientsByCategory("Add-ons"));

        add(categoryTabs, BorderLayout.CENTER);
    }

    private void addTabWithIngredients(JTabbedPane tabs, String category, List<Ingredient> ingredients) {
        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        for (Ingredient ingredient : ingredients) {
            JPanel itemPanel = createIngredientItem(ingredient);
            panel.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getViewport().setBackground(new Color(240, 240, 240));
        tabs.addTab(category, scrollPane);
    }

    private JPanel createIngredientItem(Ingredient ingredient) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        try {
            BufferedImage img = ImageIO.read(new URL(ingredient.getImageUrl()));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Image not available");
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(errorLabel, BorderLayout.CENTER);
        }

        JCheckBox checkBox = new JCheckBox(ingredient.getName());
        checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(checkBox, BorderLayout.SOUTH);

        return panel;
    }
}
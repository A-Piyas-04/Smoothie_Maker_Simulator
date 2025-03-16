package view;

import controller.GameController;
import model.Ingredient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MixingPanel extends JPanel {
    private GameController controller;
    private JList<String> ingredientList;
    private JProgressBar progressBar;
    private JButton blendButton;
    private Timer blendingTimer;
    
    public MixingPanel(GameController controller) {
        this.controller = controller;
        initializeUI();
        setupBlendingLogic();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Selected ingredients display
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Selected Ingredients"));
        ingredientList = new JList<>();
        ingredientList.setCellRenderer(new IngredientListRenderer());
        topPanel.add(new JScrollPane(ingredientList), BorderLayout.CENTER);
        
        // Progress and controls
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        blendButton = new JButton("Blend Now!");
        blendButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        bottomPanel.add(progressBar, BorderLayout.CENTER);
        bottomPanel.add(blendButton, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupBlendingLogic() {
        blendingTimer = new Timer(50, e -> {
            int progress = progressBar.getValue() + 1;
            progressBar.setValue(progress);
            progressBar.setForeground(new Color(
                (int) (200 - progress * 1.5),
                (int) (50 + progress * 2),
                50
            ));
            
            if(progress >= 100) {
                blendingTimer.stop();
                blendButton.setEnabled(true);
                controller.updateScore(50);
                controller.updateCoins(10);
            }
        });

        blendButton.addActionListener(e -> {
            blendButton.setEnabled(false);
            progressBar.setValue(0);
            blendingTimer.start();
        });
    }

    public void updateIngredients(List<Ingredient> ingredients) {
        String[] names = ingredients.stream()
            .map(Ingredient::getName)
            .toArray(String[]::new);
        ingredientList.setListData(names);
    }

    private class IngredientListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            label.setIconTextGap(10);
            label.setIcon(new ImageIcon(System.getProperty("user.dir") + 
                "/src/assets/fruit_icon.png")); // Placeholder icon path
            return label;
        }
    }
}
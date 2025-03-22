package com.smoothiemaker.view;

import com.smoothiemaker.controller.GameStateManager;
import javax.swing.*;
import java.awt.*;

public class BlendControlPanel extends JPanel {
    private GameStateManager gameStateManager;
    private StatusPanel statusPanel;
    private JTextField smoothieNameField;
    private JButton blendButton;

    public BlendControlPanel(GameStateManager gameStateManager, StatusPanel statusPanel) {
        this.gameStateManager = gameStateManager;
        this.statusPanel = statusPanel;
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new FlowLayout());
        add(new JLabel("Smoothie Name:"));
        
        smoothieNameField = new JTextField(15);
        add(smoothieNameField);

        blendButton = new JButton("Blend!");
        blendButton.addActionListener(e -> {
            if (!gameStateManager.hasIngredients()) {
                statusPanel.updateStatus("Add ingredients first!");
                return;
            }
            
            String smoothieName = smoothieNameField.getText().trim();
            if (smoothieName.isEmpty()) {
                statusPanel.updateStatus("Please enter a smoothie name!");
                return;
            }

            blendButton.setEnabled(false);
            gameStateManager.getBlenderController().getBlenderPanel().startAnimation(() -> {
                String result = gameStateManager.blend(smoothieName);
                statusPanel.updateStatus(result);
                blendButton.setEnabled(true);
                smoothieNameField.setText("");
            });
        });
        add(blendButton);
    }
}
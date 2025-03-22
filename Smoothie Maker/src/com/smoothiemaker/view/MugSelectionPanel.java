package com.smoothiemaker.view;

import com.smoothiemaker.controller.GameStateManager;
import com.smoothiemaker.model.Mug;
import com.smoothiemaker.model.MugFactory;
import javax.swing.*;
import java.awt.*;

public class MugSelectionPanel extends JPanel {
    private GameStateManager gameStateManager;
    private StatusPanel statusPanel;

    public MugSelectionPanel(GameStateManager gameStateManager, StatusPanel statusPanel) {
        this.gameStateManager = gameStateManager;
        this.statusPanel = statusPanel;
        initializeMugSelector();
    }

    private void initializeMugSelector() {
        JComboBox<String> mugSelector = new JComboBox<>(new String[]{"Plastic Mug", "Glass Mug"});
        mugSelector.addActionListener(e -> {
            Mug selectedMug = MugFactory.createMug((String) mugSelector.getSelectedItem());
            gameStateManager.setMug(selectedMug);
            statusPanel.updateStatus("Selected: " + selectedMug.getType());
        });

        add(new JLabel("Select Mug:"));
        add(mugSelector);
    }
}
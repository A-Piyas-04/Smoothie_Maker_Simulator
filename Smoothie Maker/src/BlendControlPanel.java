import javax.swing.*;
import java.awt.*;

public class BlendControlPanel extends JPanel {
    private GameStateManager gameStateManager;
    private StatusPanel statusPanel;
    private JTextField playerNameField;
    private JTextField smoothieNameField;

    public BlendControlPanel(GameStateManager gameStateManager, StatusPanel statusPanel) {
        this.gameStateManager = gameStateManager;
        this.statusPanel = statusPanel;
        setLayout(new BorderLayout(10, 10));
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 3, 3));
        playerNameField = new JTextField();
        smoothieNameField = new JTextField();

        inputPanel.add(new JLabel("Player Name     :"));
        inputPanel.add(playerNameField);
        inputPanel.add(new JLabel("Smoothie Name   :"));
        inputPanel.add(smoothieNameField);

        JButton blendBtn = new JButton("Blend!");
        blendBtn.addActionListener(e -> handleBlendButtonClick());

        add(inputPanel, BorderLayout.NORTH);
        add(blendBtn, BorderLayout.CENTER);
    }

    private void handleBlendButtonClick() {
        String smoothieName = smoothieNameField.getText().trim();
        String playerName = playerNameField.getText().trim();
        
        if (smoothieName.isEmpty()) {
            statusPanel.updateStatus("Please name your smoothie first!");
            return;
        }
        if (playerName.isEmpty()) {
            statusPanel.updateStatus("Please enter your name first!");
            return;
        }
        if (gameStateManager.getCurrentMug() == null || !gameStateManager.hasIngredients()) {
            statusPanel.updateStatus("Please select a mug and add ingredients first!");
            return;
        }

        smoothieNameField.setText("");
        gameStateManager.setPlayerName(playerName);
        BlenderAnimationPanel blenderPanel = gameStateManager.getBlenderController().getBlenderPanel();
        blenderPanel.setOnAnimationComplete(() -> performBlending(smoothieName));
        blenderPanel.startAnimation();
    }

    private void performBlending(String smoothieName) {
        if (gameStateManager.getCurrentMug() == null) {
            statusPanel.updateStatus("Please select a mug first!");
            return;
        }

        String result = gameStateManager.blend(smoothieName);
        statusPanel.updateStatus(result);
    }
}
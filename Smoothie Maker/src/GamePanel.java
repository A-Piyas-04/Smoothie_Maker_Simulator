import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameStateManager gameStateManager;
    private StatusPanel statusPanel;
    private JLabel scoreLabel;

    public GamePanel() {
        gameStateManager = new GameStateManager();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 760));

        // Initialize components
        statusPanel = new StatusPanel();
        BlenderAnimationPanel blenderPanel = gameStateManager.getBlenderController().getBlenderPanel();
        blenderPanel.setPreferredSize(new Dimension(300, 200));

        // Create main panel with components
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.add(new IngredientPanel(gameStateManager, statusPanel));
        mainPanel.add(new MugSelectionPanel(gameStateManager, statusPanel));
        mainPanel.add(new BlendControlPanel(gameStateManager, statusPanel));

        // Setup title display
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("-- Make your own Smoothiee --");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Setup north panel with blender and title
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(blenderPanel, BorderLayout.CENTER);
        northPanel.add(titlePanel, BorderLayout.SOUTH);

        // Add panels to main layout
        add(mainPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        add(statusPanel, BorderLayout.SOUTH);

        // Setup score update listener
        gameStateManager.setScoreUpdateListener(score -> {
            String alias = AliasService.getAlias(score);
            scoreLabel.setText("Score: " + score + "  -||-  Alias: " + alias);
        });
    }
}

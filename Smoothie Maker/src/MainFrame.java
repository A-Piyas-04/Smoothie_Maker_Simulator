import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Smoothie Maker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Initialize panel manager
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Add game panel to card layout
        GamePanel gamePanel = new GamePanel();
        cardPanel.add(gamePanel, "play");
        
        // Create navigation panel
        NavigationPanel navigationPanel = new NavigationPanel(cardLayout, cardPanel);
        
        // Add components to frame
        add(navigationPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    public MainFrame() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        setTitle("Smoothie Maker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Create panels
        NavigationPanel navigationPanel = new NavigationPanel(this);
        SmoothieLogViewer smoothieLogViewer = new SmoothieLogViewer(this);
        
        // Add panels to card layout
        cardPanel.add(navigationPanel, "navigation");
        cardPanel.add(smoothieLogViewer, "smoothies");
        
        // Add components to frame
        add(cardPanel, BorderLayout.CENTER);
        
        // Show navigation panel initially
        cardLayout.show(cardPanel, "navigation");
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
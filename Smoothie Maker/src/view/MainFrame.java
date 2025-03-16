package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private OrderPanel orderPanel;

    public OrderPanel getOrderPanel() {
        return orderPanel;
    }
    private GameController controller;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private MixingPanel mixingPanel;
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    public MainFrame(GameController controller) {
        orderPanel = new OrderPanel(controller);
        add(orderPanel, BorderLayout.EAST);
        this.controller = controller;
        setTitle("Smoothie Maker Challenge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(new IngredientSelectionPanel(controller), "SELECTION");
        mainPanel.add(mixingPanel = new MixingPanel(controller), "MIXING");
        add(mainPanel);
    }



    public void switchView(String viewName) {
        cardLayout.show(mainPanel, viewName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameController controller = new GameController();
            MainFrame frame = new MainFrame(controller);
            frame.setVisible(true);
            controller.startGame();
        });
    }
}
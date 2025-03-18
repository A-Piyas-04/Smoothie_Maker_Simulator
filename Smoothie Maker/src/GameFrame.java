import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(MainFrame mainFrame) {
        setTitle("Smoothie Maker Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 760);
        setLocationRelativeTo(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setPreferredSize(new Dimension(100, 30));
        mainMenuButton.addActionListener(e -> {
            dispose();
            mainFrame.setVisible(true);
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(mainMenuButton);

        add(topPanel, BorderLayout.NORTH);
        add(new GamePanel(), BorderLayout.CENTER);
    }
}
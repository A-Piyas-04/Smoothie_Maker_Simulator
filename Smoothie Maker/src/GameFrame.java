import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(MainFrame mainFrame) {
        setTitle("Smoothie Maker Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });

        add(new GamePanel());
    }
}
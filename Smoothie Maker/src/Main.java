import javax.swing.*;

public class Main {
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    });
}

}
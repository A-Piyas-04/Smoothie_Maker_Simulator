import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SmoothieLogViewer extends JPanel {
    private static final String LOG_FILE = "D:\\Java\\Project OOP-2\\Smoothie Maker\\smoothies.txt";
    private JTextArea logArea;
    private MainFrame mainFrame;

    public SmoothieLogViewer(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        
        // Create text area with scroll pane
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.BOLD, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        
        // Create header panel with title and back button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(new JLabel("Smoothie Creation Logs", SwingConstants.CENTER), BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> mainFrame.getCardLayout().show(mainFrame.getCardPanel(), "navigation"));
        headerPanel.add(backButton, BorderLayout.EAST);
        
        // Add components
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Load and display logs
        loadLogs();
    }

    private void loadLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            logArea.setText(content.toString());
            logArea.setCaretPosition(0); // Scroll to top
        } catch (IOException e) {
            logArea.setText("Error reading log file: " + e.getMessage());
        }
    }

    public void refreshLogs() {
        loadLogs();
    }
}
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SmoothieLogViewer extends JPanel {
    private static final String LOG_FILE = "D:\\Java\\Project OOP-2\\Smoothie Maker\\smoothies.txt";
    private JTextArea logArea;

    public SmoothieLogViewer() {
        setLayout(new BorderLayout());
        
        // Create text area with scroll pane
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.BOLD, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        
        // Add components
        add(new JLabel("Smoothie Creation Logs", SwingConstants.CENTER), BorderLayout.NORTH);
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
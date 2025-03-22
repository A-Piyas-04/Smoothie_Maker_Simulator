import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SmoothieLogger {
    private static final String FILE_PATH = "smoothies.txt";
    private static final String HEADER = "| %-15s | %-20s | %-20s | %-20s | %-20s | %-12s | %-15s |";
    private static final String SEPARATOR = "+" + "-".repeat(17) + "+" + "-".repeat(22) + "+" + "-".repeat(22) + "+" + "-".repeat(22) + "+" + "-".repeat(22) + "+" + "-".repeat(14) + "+" + "-".repeat(17) + "+";

    static {
        initializeFile();
    }

    private static void initializeFile() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
                writer.println(SEPARATOR);
                writer.println(String.format(HEADER, "Player Name", "Smoothie Name", "Flavors (Count/Score)", "Fruits (Count/Score)", "Toppings (Count/Score)", "Total Score", "Alias"));
                writer.println(SEPARATOR);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void log(String playerName, String smoothieName, 
                          int flavorCount, int flavorScore,
                          int fruitCount, int fruitScore,
                          int toppingCount, int toppingScore,
                          int totalScore, String alias) {
        
        String entry = String.format(HEADER + "\n" + SEPARATOR,
            playerName,
            smoothieName,
            String.format("%d (%d)", flavorCount, flavorScore),
            String.format("%d (%d)", fruitCount, fruitScore),
            String.format("%d (%d)", toppingCount, toppingScore),
            totalScore,
            alias
        );

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
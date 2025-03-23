import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StatusLogger {
    private static final String LOG_FILE = "D:\\Java\\Project OOP-2\\Smoothie Maker\\smoothies.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String formatIngredientDetails(Ingredient ingredient) {
        return String.format("  - %s (%s, Type: %s, Score: %d)",
            ingredient.getName(),
            ingredient.getClass().getSimpleName(),
            ingredient.getType(),
            ingredient.getScore());
    }

    public void logBlend(String playerName, String smoothieName, List<Ingredient> ingredients, int score) {
        String alias = AliasService.getAlias(score);
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            StringBuilder logEntry = new StringBuilder();
            logEntry.append("\n=== Smoothie Creation Log ===\n");
            logEntry.append("Time: ").append(LocalDateTime.now().format(formatter)).append("\n");
            logEntry.append("Player: ").append(playerName).append("\n");
            logEntry.append("Smoothie: ").append(smoothieName).append("\n");
            logEntry.append("Ingredients:\n");
            
            for (Ingredient ingredient : ingredients) {
                logEntry.append(formatIngredientDetails(ingredient)).append("\n");
            }
            
            logEntry.append("Score: ").append(score).append("\n");
            logEntry.append("Alias: ").append(alias).append("\n");
            logEntry.append("==========================\n");
            
            writer.write(logEntry.toString());
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// простой логгер, который печатает и хранит историю действий
public class Logger {
    private static final List<String> HISTORY = new ArrayList<>();
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void log(String tag, String message) {
        String line = String.format("%s %s: %s", LocalDateTime.now().format(F), tag, message);
        System.out.println(line);
        HISTORY.add(line);
    }

    public static void log(String message) {
        log("[LOG]", message);
    }

    public static void printHistory() {
        System.out.println("\n===== ACTION HISTORY =====");
        HISTORY.forEach(System.out::println);
        System.out.println("===== END HISTORY =====\n");
    }

    public static List<String> getHistory() {
        return new ArrayList<>(HISTORY);
    }
}

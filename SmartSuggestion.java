package smart;

import util.Logger;

import java.util.HashMap;
import java.util.Map;

public class SmartSuggestion {
    private static final Map<String, Integer> usage = new HashMap<>();

    // вызывать при каждом включении/выключении устройства (или другом использовании)
    public static void recordUse(String deviceName) {
        usage.put(deviceName, usage.getOrDefault(deviceName, 0) + 1);
    }

    // анализируем использование и выводим рекомендации
    public static void analyze() {
        Logger.log("SmartSuggestion", "Analyzing usage...");
        for (Map.Entry<String, Integer> e : usage.entrySet()) {
            String device = e.getKey();
            int count = e.getValue();
            if (count >= 3) { // порог можно менять
                Logger.log("SmartSuggestion", "💡 Suggestion: " + device + " is used frequently (" + count + " times). Consider automating it.");
            }
        }
    }
}

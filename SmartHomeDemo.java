package facade;

import smart.SmartEnergySaver;
import smart.SmartScheduler;
import smart.SmartSuggestion;
import util.Logger;

public class SmartHomeDemo {
    public static void main(String[] args) {
        Logger.log("SYSTEM", "🚀 Запуск системы умного дома...");
        SmartHomeFacade home = new SmartHomeFacade();

        // 1) запустим автоматическую рутину по времени (пример)
        SmartScheduler.autoRoutine(home);
        pause(1000);

        // 2) демонстрация movie mode
        home.startMovieMode();
        pause(1000);

        // 3) проверка энергосбережения (симуляция)
        SmartEnergySaver.checkDevices(home.getAllDevices());
        pause(1000);

        // 4) демо — реакция на погоду
        home.weatherAdjustment(5);  // холодно
        pause(1000);
        home.weatherAdjustment(32); // жарко
        pause(1000);

        // 5) анализ предложений
        SmartSuggestion.analyze();

        Logger.printHistory();
    }

    private static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

import facade.SmartHomeFacade;
import util.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println(" SmartHome System Booting...");
        System.out.println("====================================\n");

        SmartHomeFacade home = new SmartHomeFacade();

        Logger.log("MAIN", "Начинаем демонстрацию сценариев умного дома...");

        home.morningRoutine();
        pause(2000);

        home.leaveHome();
        pause(2000);

        home.arriveHome();
        pause(2000);

        home.startMovieMode();
        pause(2000);

        home.weatherAdjustment(5);   // холодная погода
        pause(2000);

        System.out.println();
        Logger.log("MAIN", "Проверяем автоматическую замену лампы...");
        // проверка лампы — 10% шанс, что она сгорит
        home.getAllDevices().stream()
                .filter(d -> d instanceof devices.Light)
                .map(d -> (devices.Light) d)
                .findFirst()
                .ifPresent(light -> {
                    if (Math.random() < 0.1) {
                        light.burnOut();
                    } else {
                        System.out.println("[LOG]: [Лампа в спальне]  Всё работает нормально.");
                    }
                });

        home.autoDiagnostics();
        home.weatherAdjustment(28);  // жаркая погода
        pause(2000);

        home.nightRoutine();
        pause(2000);

        home.printStatusSummary();

        System.out.println("\n====================================");
        System.out.println(" SmartHome System Finished Execution.");
        System.out.println("====================================");
    }

    private static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

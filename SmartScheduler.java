package smart;

import facade.SmartHomeFacade;
import util.Logger;

import java.time.LocalTime;

public class SmartScheduler {

    // выбирает рутину в зависимости от текущего времени
    public static void autoRoutine(SmartHomeFacade home) {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();

        if (hour >= 6 && hour < 10) {
            Logger.log("SmartScheduler", "🕕 Detected morning — running morningRoutine()");
            home.morningRoutine();
        } else if (hour >= 10 && hour < 18) {
            Logger.log("SmartScheduler", "🏢 Daytime — running leaveHome()");
            home.leaveHome();
        } else if (hour >= 18 && hour < 23) {
            Logger.log("SmartScheduler", "🌇 Evening — running arriveHome()");
            home.arriveHome();
        } else {
            Logger.log("SmartScheduler", "🌙 Night — running nightRoutine()");
            home.nightRoutine();
        }
    }
}

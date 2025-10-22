package facade;

import devices.*;
import util.Logger;
import smart.SmartSuggestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Facade — теперь хранит список всех устройств и сообщает SmartSuggestion о использовании.

public class SmartHomeFacade {
    private final Light light;
    private final MusicSystem musicSystem;
    private final SecurityCamera camera;
    private final SmartDoorLock doorLock;
    private final Thermostat thermostat;
    private final CoffeeMachine coffeeMachine;
    private final Curtains curtains;
    private final SmartTV tv;
    private final AirConditioner ac;

    private final List<Device> allDevices = new ArrayList<>();

    public SmartHomeFacade() {
        this.light = new Light("Лампа в спальне");
        this.musicSystem = new MusicSystem("Музыкальная система");
        this.camera = new SecurityCamera("Камера у входа");
        this.doorLock = new SmartDoorLock("Замок входной двери");
        this.thermostat = new Thermostat("Термостат в гостиной");
        this.coffeeMachine = new CoffeeMachine("Кофемашина на кухне");
        this.curtains = new Curtains("Шторы в спальне");
        this.tv = new SmartTV("Гостиная TV");
        this.ac = new AirConditioner("Гостиная AC");

        // собираем все устройства в список для удобной обработки
        Collections.addAll(allDevices, light, musicSystem, camera, doorLock,
                thermostat, coffeeMachine, curtains, tv, ac);

        Logger.log("SmartHomeFacade", "умный дом инициализирован с " + allDevices.size() + " устройствами");
    }

    public List<Device> getAllDevices() {
        return new ArrayList<>(allDevices);
    }

    private void safeTurnOn(Device d) {
        d.turnOn();
        SmartSuggestion.recordUse(d.getName());
    }

    private void safeTurnOff(Device d) {
        d.turnOff();
        SmartSuggestion.recordUse(d.getName());
    }

    public void morningRoutine() {
        Logger.log("SmartHomeFacade", " Утренняя рутина запущена...");
        safeTurnOn(light);
        curtains.openFully();
        safeTurnOn(thermostat);
        safeTurnOn(coffeeMachine);
        safeTurnOn(musicSystem);
        Logger.log("SmartHomeFacade", "✅ Утренняя рутина завершена!");
    }

    public void nightRoutine() {
        Logger.log("SmartHomeFacade", " Ночная рутина запущена...");
        safeTurnOff(light);
        curtains.closeFully();
        safeTurnOff(musicSystem);
        safeTurnOff(thermostat);
        safeTurnOn(camera);
        doorLock.lock();
        SmartSuggestion.recordUse(doorLock.getName());
        Logger.log("SmartHomeFacade", " Ночная рутина завершена!");
    }

    public void leaveHome() {
        Logger.log("SmartHomeFacade", " Режим 'Ушел из дома' активирован...");
        safeTurnOff(light);
        safeTurnOff(musicSystem);
        curtains.closeFully();
        safeTurnOff(thermostat);
        safeTurnOff(coffeeMachine);
        safeTurnOn(camera);
        doorLock.lock();
        SmartSuggestion.recordUse(doorLock.getName());
        Logger.log("SmartHomeFacade", " Дом защищен!");
    }

    public void arriveHome() {
        Logger.log("SmartHomeFacade", " Режим 'Дома' активирован...");
        doorLock.unlock("0000"); // добавлен пин
        SmartSuggestion.recordUse(doorLock.getName());
        safeTurnOff(camera);
        safeTurnOn(light);
        curtains.openFully();
        safeTurnOn(thermostat);
        safeTurnOn(musicSystem);
        Logger.log("SmartHomeFacade", " Добро пожаловать домой!");
    }

    public void startMovieMode() {
        Logger.log("SmartHomeFacade", " Movie mode starting...");
        light.setBrightness(20);
        if (!light.isOn()) safeTurnOn(light);

        curtains.closeFully();

        tv.turnOn();
        tv.setInput("HDMI2");
        tv.setVolume(15);
        SmartSuggestion.recordUse(tv.getName());

        if (musicSystem.isPlaying()) {
            musicSystem.setVolume(15);
            SmartSuggestion.recordUse(musicSystem.getName());
        }

        camera.turnOff();
        Logger.log("SmartHomeFacade", " Movie mode is ON. Enjoy!");
    }

    public void weatherAdjustment(int outsideTemp) {
        Logger.log("SmartHomeFacade", " Weather check: outside temperature = " + outsideTemp + "°C");
        if (outsideTemp < 10) {
            Logger.log("SmartHomeFacade", "🔧 It's cold outside — turning on heating.");
            thermostat.turnOn();
            thermostat.setTemperature(23);
            ac.turnOff();
            SmartSuggestion.recordUse(thermostat.getName());
        } else if (outsideTemp > 25) {
            Logger.log("SmartHomeFacade", "🔧 It's hot outside — turning on AC (cooling).");
            ac.turnOn();
            ac.setTemperature(20);
            ac.setMode("COOL");
            thermostat.turnOff();
            SmartSuggestion.recordUse(ac.getName());
        } else {
            Logger.log("SmartHomeFacade", "🔧 Comfortable outside — switching to eco/minimal climate.");
            thermostat.setTemperature(21);
            thermostat.enableEcoMode();
            SmartSuggestion.recordUse(thermostat.getName());
        }
    }

    public void printStatusSummary() {
        Logger.log("SmartHomeFacade", "----- STATUS SUMMARY -----");
        for (Device d : allDevices) {
            Logger.log("SmartHomeFacade", d.getName() + " | on=" + d.isOn());
        }
        Logger.log("SmartHomeFacade", "--------------------------");
    }
    public void autoDiagnostics() {
        Logger.log("SmartHomeFacade", " Автопроверка всех устройств...");
        for (Device d : allDevices) {
            if (d instanceof Light light) {
                if (light.isBurnedOut()) {
                    Logger.log("SmartHomeFacade", "[DIAG]: Обнаружена сгоревшая лампа: " + light.getName());
                    light.replace();
                }
            }
        }
        Logger.log("SmartHomeFacade", "✅ Диагностика завершена!");
    }
}

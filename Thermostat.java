package devices;

import util.Logger;

//Thermostat — управление температурой с eco/normal режимами и safety checks

public class Thermostat implements Device {
    private final String location;
    private boolean isOn;
    private int temperature; // current target temp
    private boolean ecoMode; // if true, lower target for saving energy

    public Thermostat(String location) {
        this.location = location;
        this.isOn = true;        // считаем, что термостат обычно активен
        this.temperature = 22;
        this.ecoMode = false;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            Logger.log("[" + location + "] Thermostat turned ON");
        } else {
            Logger.log("[" + location + "] Thermostat already ON");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            Logger.log("[" + location + "] Thermostat turned OFF");
        } else {
            Logger.log("[" + location + "] Thermostat already OFF");
        }
    }

    @Override
    public void performFunction() {
        if (isOn) {
            Logger.log("[" + location + "] Maintaining temperature: " + temperature + "°C"
                    + (ecoMode ? " (Eco Mode)" : ""));
        } else {
            Logger.log("[" + location + "] Thermostat idle.");
        }
    }

    public void setTemperature(int temp) {
        if (temp < 10 || temp > 30) {
            Logger.log("[" + location + "] Temperature " + temp + "°C out of supported range (10-30)");
            return;
        }
        temperature = temp;
        Logger.log("[" + location + "] Target temperature set to " + temperature + "°C");
    }

    public int getTemperature() {
        return temperature;
    }

    public void enableEcoMode() {
        ecoMode = true;
        temperature = Math.max(16, temperature - 3); // gently reduce
        Logger.log("[" + location + "] Eco Mode enabled — target adjusted to " + temperature + "°C");
    }

    public void disableEcoMode() {
        ecoMode = false;
        Logger.log("[" + location + "] Eco Mode disabled");
    }

    public boolean isEcoMode() {
        return ecoMode;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getName() {
        return "Thermostat (" + location + ")";
    }
}

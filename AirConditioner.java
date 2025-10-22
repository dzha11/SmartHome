package devices;

import util.Logger;

public class AirConditioner implements Device {
    private final String name;
    private boolean on;
    private int temperature;
    private String mode;

    public AirConditioner(String name) {
        this.name = name;
        this.on = false;
        this.temperature = 22;
        this.mode = "COOL";
    }

    @Override
    public void performFunction() {
        if (!on) {
            Logger.log(name + " выключен.");
            return;
        }
        Logger.log(name + " работает в режиме " + mode + " при температуре " + temperature + "°C.");
    }

    @Override
    public void turnOn() {
        if (!on) {
            on = true;
            Logger.log(name + " включён.");
        } else {
            Logger.log(name + " уже был включён.");
        }
    }

    @Override
    public void turnOff() {
        if (on) {
            on = false;
            Logger.log(name + " выключен.");
        } else {
            Logger.log(name + " уже был выключен.");
        }
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        Logger.log(name + " температура установлена на " + temperature + "°C");
    }

    public void setMode(String mode) {
        this.mode = mode;
        Logger.log(name + " режим установлен на " + mode);
    }
}

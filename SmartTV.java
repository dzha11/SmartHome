package devices;

import util.Logger;

public class SmartTV implements Device {
    private final String name;
    private boolean on;
    private int volume;
    private String input;

    public SmartTV(String name) {
        this.name = name;
        this.on = false;
        this.volume = 20;
        this.input = "HDMI1";
    }

    @Override
    public void performFunction() {
        if (!on) {
            Logger.log(name + " выключен.");
            return;
        }
        Logger.log(name + " показывает контент на " + input + " с громкостью " + volume + ".");
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

    public void setVolume(int volume) {
        this.volume = volume;
        Logger.log(name + " громкость установлена на " + volume);
    }

    public void setInput(String input) {
        this.input = input;
        Logger.log(name + " переключён на " + input);
    }
}

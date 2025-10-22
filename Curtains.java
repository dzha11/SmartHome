package devices;

import util.Logger;

/**
 * Curtains — шторы с положением 0..100 и возможностью плавного открытия/закрытия.
 */
public class Curtains implements Device {
    private final String location;
    private boolean isOn;   // powered
    private int position;   // 0 closed, 100 open
    private boolean moving; // if true, simulating movement

    public Curtains(String location) {
        this.location = location;
        this.isOn = true; // motor powered by default
        this.position = 0;
        this.moving = false;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            Logger.log("[" + location + "] Curtains motor powered ON.");
        } else {
            Logger.log("[" + location + "] Curtains motor already ON.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            Logger.log("[" + location + "] Curtains motor powered OFF.");
        } else {
            Logger.log("[" + location + "] Curtains motor already OFF.");
        }
    }

    @Override
    public void performFunction() {
        Logger.log("[" + location + "] Curtains position: " + position + "% " + (moving ? "(moving)" : ""));
    }

    public void openFully() {
        if (!isOn) {
            Logger.log("[" + location + "] Cannot open: motor OFF.");
            return;
        }
        moving = true;
        position = 100;
        moving = false;
        Logger.log("[" + location + "] Curtains opened fully.");
    }

    public void closeFully() {
        if (!isOn) {
            Logger.log("[" + location + "] Cannot close: motor OFF.");
            return;
        }
        moving = true;
        position = 0;
        moving = false;
        Logger.log("[" + location + "] Curtains closed fully.");
    }

    public void setPosition(int pos) {
        if (!isOn) {
            Logger.log("[" + location + "] Cannot set position: motor OFF.");
            return;
        }
        int p = Math.max(0, Math.min(100, pos));
        moving = true;
        position = p;
        moving = false;
        Logger.log("[" + location + "] Curtains position set to " + position + "%.");
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getName() {
        return "Curtains (" + location + ")";
    }
}

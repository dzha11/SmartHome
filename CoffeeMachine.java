package devices;

import util.Logger;

public class CoffeeMachine implements Device {
    private final String location;
    private boolean isOn;
    private boolean brewing;
    private int waterLevel; // 0–100

    public CoffeeMachine(String location) {
        this.location = location;
        this.isOn = false;
        this.brewing = false;
        this.waterLevel = 80;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            Logger.log("[" + location + "] Coffee Machine powered ON.");
        } else {
            Logger.log("[" + location + "] Coffee Machine already ON.");
        }
    }

    @Override
    public void turnOff() {
        if (brewing) {
            Logger.log("[" + location + "] Stopping brewing before shutdown.");
            brewing = false;
        }
        if (isOn) {
            isOn = false;
            Logger.log("[" + location + "] Coffee Machine powered OFF.");
        } else {
            Logger.log("[" + location + "] Coffee Machine already OFF.");
        }
    }

    @Override
    public void performFunction() {
        if (!isOn) {
            Logger.log("[" + location + "] Coffee Machine is OFF.");
            return;
        }
        if (brewing) {
            Logger.log("[" + location + "] Brewing coffee... (water: " + waterLevel + "%)");
        } else {
            Logger.log("[" + location + "] Idle. Water level: " + waterLevel + "%");
        }
    }

    public void brew() {
        if (!isOn) {
            Logger.log("[" + location + "] Can't brew: machine is OFF.");
            return;
        }
        if (waterLevel < 10) {
            Logger.log("[" + location + "] Can't brew: low water (" + waterLevel + "%). Please refill.");
            return;
        }
        brewing = true;
        Logger.log("[" + location + "] Brewing started ☕...");
        waterLevel -= 10;
        brewing = false;
        Logger.log("[" + location + "] Coffee ready! Water left: " + waterLevel + "%");
    }

    public void refillWater() {
        waterLevel = 100;
        Logger.log("[" + location + "] Water tank refilled to 100%");
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getName() {
        return "Coffee Machine (" + location + ")";
    }
}

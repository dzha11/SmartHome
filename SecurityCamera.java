package devices;

import util.Logger;

//SecurityCamera — камера с состоянием power + recording + night mode simulation.

public class SecurityCamera implements Device {
    private final String location;
    private boolean isOn;
    private boolean recording;
    private boolean nightMode;

    public SecurityCamera(String location) {
        this.location = location;
        this.isOn = false;
        this.recording = false;
        this.nightMode = false;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            recording = true;
            Logger.log("[" + location + "] Security Camera powered ON and recording.");
        } else {
            Logger.log("[" + location + "] Security Camera already ON.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            recording = false;
            Logger.log("[" + location + "] Security Camera powered OFF.");
        } else {
            Logger.log("[" + location + "] Security Camera already OFF.");
        }
    }

    @Override
    public void performFunction() {
        if (isOn) {
            Logger.log("[" + location + "] Camera recording=" + recording + (nightMode ? " (Night Mode)" : ""));
        } else {
            Logger.log("[" + location + "] Camera idle.");
        }
    }

    public void enableNightMode() {
        nightMode = true;
        Logger.log("[" + location + "] Camera Night Mode enabled (better low-light recording).");
    }

    public void disableNightMode() {
        nightMode = false;
        Logger.log("[" + location + "] Camera Night Mode disabled.");
    }

    public boolean isRecording() {
        return recording;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getName() {
        return "Security Camera (" + location + ")";
    }
}

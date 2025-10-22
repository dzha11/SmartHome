package devices;

import util.Logger;

/**
 * Light — лампа с состоянием on/off, яркостью и режимом dim.
 * Теперь с возможностью сгореть и быть заменённой 💡
 */
public class Light implements Device {
    private final String location;
    private boolean isOn;
    private int brightness;   // 0..100
    private boolean dimMode;  // если true — плавное приглушение включено
    private boolean isBurnedOut; // новая фича: лампа может сгореть

    public Light(String location) {
        this.location = location;
        this.isOn = false;
        this.brightness = 70;
        this.dimMode = false;
        this.isBurnedOut = false;
    }

    @Override
    public void turnOn() {
        if (isBurnedOut) {
            Logger.log("[" + location + "] ❌ Лампа не включается — она сгорела!");
            return;
        }
        if (!isOn) {
            isOn = true;
            Logger.log("[" + location + "] Light turned ON. Brightness: " + brightness + "%");
            if (dimMode) Logger.log("[" + location + "] Light is in DIM mode (smooth).");
        } else {
            Logger.log("[" + location + "] Light already ON.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            Logger.log("[" + location + "] Light turned OFF.");
        } else {
            Logger.log("[" + location + "] Light already OFF.");
        }
    }

    @Override
    public void performFunction() {
        if (isBurnedOut) {
            Logger.log("[" + location + "] ⚠️ Лампа не работает (сгорела).");
        } else if (isOn) {
            Logger.log("[" + location + "] Light is shining at " + brightness + "%");
        } else {
            Logger.log("[" + location + "] Light is off");
        }
    }

    public void setBrightness(int value) {
        if (isBurnedOut) {
            Logger.log("[" + location + "] ⚠️ Нельзя изменить яркость — лампа сгорела.");
            return;
        }
        int v = Math.max(0, Math.min(100, value));
        this.brightness = v;
        Logger.log("[" + location + "] Brightness set to " + brightness + "%");
        if (!isOn) Logger.log("[" + location + "] Note: brightness changed while light is OFF");
    }

    public int getBrightness() {
        return brightness;
    }

    public void enableDimMode() {
        dimMode = true;
        Logger.log("[" + location + "] Dim mode enabled");
    }

    public void disableDimMode() {
        dimMode = false;
        Logger.log("[" + location + "] Dim mode disabled");
    }

    public boolean isDimMode() {
        return dimMode;
    }

    public void burnOut() {
        if (!isBurnedOut) {
            isBurnedOut = true;
            isOn = false;
            Logger.log("[" + location + "] 💥 Лампа сгорела!");
        }
    }

    public void replace() {
        if (isBurnedOut) {
            isBurnedOut = false;
            Logger.log("[" + location + "] 🔧 Лампа заменена на новую 💡");
        } else {
            Logger.log("[" + location + "] Лампа в порядке, замена не требуется");
        }
    }

    public boolean isBurnedOut() {
        return isBurnedOut;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getName() {
        return "Light (" + location + ")";
    }
}

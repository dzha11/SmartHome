package devices;

import util.Logger;

//SmartDoorLock — умный замок с PIN-кодом, автоматической блокировкой и журналом.

public class SmartDoorLock implements Device {
    private final String location;
    private boolean isOn;
    private boolean locked;
    private String pinCode;
    private int failedAttempts;

    public SmartDoorLock(String location) {
        this.location = location;
        this.isOn = true;
        this.locked = true;
        this.pinCode = pinCode;
        this.failedAttempts = 0;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            Logger.log("[" + location + "] Smart Door Lock activated.");
        } else {
            Logger.log("[" + location + "] Smart Door Lock already active.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            Logger.log("[" + location + "] Smart Door Lock deactivated (manual mode).");
        } else {
            Logger.log("[" + location + "] Smart Door Lock already off.");
        }
    }

    @Override
    public void performFunction() {
        Logger.log("[" + location + "] Door is currently " + (locked ? "LOCKED" : "UNLOCKED"));
    }

    public void unlock(String pin) {
        if (!isOn) {
            Logger.log("[" + location + "] Lock inactive. Can't unlock.");
            return;
        }
        if (pin.equals(pinCode)) {
            locked = false;
            failedAttempts = 0;
            Logger.log("[" + location + "] Door unlocked ✅");
        } else {
            failedAttempts++;
            Logger.log("[" + location + "] Wrong PIN (" + failedAttempts + " failed attempts)");
            if (failedAttempts >= 3) {
                Logger.log("[" + location + "] Security alert 🚨 Too many failed attempts!");
            }
        }
    }

    public void lock() {
        if (!isOn) {
            Logger.log("[" + location + "] Lock inactive. Can't lock.");
            return;
        }
        if (!locked) {
            locked = true;
            Logger.log("[" + location + "] Door locked 🔒");
        } else {
            Logger.log("[" + location + "] Door already locked.");
        }
    }

    public void changePin(String oldPin, String newPin) {
        if (!oldPin.equals(pinCode)) {
            Logger.log("[" + location + "] Incorrect old PIN. Can't change.");
            return;
        }
        pinCode = newPin;
        Logger.log("[" + location + "] PIN successfully changed.");
    }

    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getName() {
        return "Smart Door Lock (" + location + ")";
    }
}

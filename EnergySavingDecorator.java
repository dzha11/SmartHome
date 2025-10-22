package decorators;

import devices.Device;
import util.Logger;

public class EnergySavingDecorator extends DeviceDecorator {
    private boolean ecoMode;

    public EnergySavingDecorator(Device device) {
        super(device);
        this.ecoMode = false;
    }

    public void enableEcoMode() {
        ecoMode = true;
        Logger.log("[EnergySaving]", device.getName() + " eco mode enabled");
    }

    public void disableEcoMode() {
        ecoMode = false;
        Logger.log("[EnergySaving]", device.getName() + " eco mode disabled");
    }

    public boolean isEcoMode() {
        return ecoMode;
    }

    public void simulateMinutePassed() {
        if (ecoMode && device.isOn()) {
            Logger.log("[EnergySaving]", device.getName() + " is conserving energy...");
        }
    }

    @Override
    public void performFunction() {
        if (ecoMode) {
            Logger.log("[EnergySaving]", "Performing in low-power mode: " + device.getName());
        }
        super.performFunction();
    }
}

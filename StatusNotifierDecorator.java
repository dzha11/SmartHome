package decorators;

import devices.Device;
import util.Logger;

public class StatusNotifierDecorator extends DeviceDecorator {
    private final String notificationMethod; // например, "SMS", "App", "Email"

    public StatusNotifierDecorator(Device device, String notificationMethod) {
        super(device);
        this.notificationMethod = notificationMethod;
    }

    private void alert(String message) {
        Logger.log("[Notifier-" + notificationMethod + "]", "Notification: " + message);
    }

    @Override
    public void turnOn() {
        super.turnOn();
        alert(device.getName() + " has been turned ON");
    }

    @Override
    public void turnOff() {
        super.turnOff();
        alert(device.getName() + " has been turned OFF");
    }

    @Override
    public void performFunction() {
        super.performFunction();
        alert(device.getName() + " performed its function");
    }
}

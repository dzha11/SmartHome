package decorators;

import devices.Device;
import util.Logger;

public abstract class DeviceDecorator implements Device {
    protected final Device device;

    public DeviceDecorator(Device device) {
        this.device = device;
    }

    @Override
    public void turnOn() {
        device.turnOn();
        Logger.log("[Decorator]", device.getName() + " turned on");
    }

    @Override
    public void turnOff() {
        device.turnOff();
        Logger.log("[Decorator]", device.getName() + " turned off");
    }

    @Override
    public void performFunction() {
        device.performFunction();
        Logger.log("[Decorator]", device.getName() + " performed function");
    }

    @Override
    public boolean isOn() {
        return device.isOn();
    }

    @Override
    public String getName() {
        return device.getName();
    }
}

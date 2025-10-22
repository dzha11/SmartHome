package decorators;

import devices.Device;
import util.Logger;

public class RemoteAccessDecorator extends DeviceDecorator {
    private boolean remoteEnabled;

    public RemoteAccessDecorator(Device device) {
        super(device);
        this.remoteEnabled = false;
    }

    public void enableRemoteAccess(String user) {
        remoteEnabled = true;
        Logger.log("[RemoteAccess]", "Remote access enabled for " + user);
    }

    public void disableRemoteAccess() {
        remoteEnabled = false;
        Logger.log("[RemoteAccess]", "Remote access disabled");
    }

    public boolean isRemoteAccessEnabled() {
        return remoteEnabled;
    }

    public void remoteCommand(String command) {
        if (!remoteEnabled) {
            Logger.log("[RemoteAccess]", "Access denied: remote control disabled");
            return;
        }
        Logger.log("[RemoteAccess]", "Executing remote command: " + command);
        switch (command.toLowerCase()) {
            case "turn on" -> device.turnOn();
            case "turn off" -> device.turnOff();
            case "perform" -> device.performFunction();
            default -> Logger.log("[RemoteAccess]", "Unknown command: " + command);
        }
    }
}

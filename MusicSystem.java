package devices;

import util.Logger;

//MusicSystem — проигрыватель с громкостью, плейлистом и режимами.
//Поддерживает play/stop, setVolume, setTrack. Логирует действия.
 
public class MusicSystem implements Device {
    private final String location;
    private boolean isOn;
    private int volume;          // 0..100
    private String currentTrack;
    private boolean shuffleMode;

    public MusicSystem(String location) {
        this.location = location;
        this.isOn = false;
        this.volume = 50;
        this.currentTrack = "No track";
        this.shuffleMode = false;
    }

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            Logger.log("[" + location + "] Music System turned ON (volume: " + volume + "%)");
        } else {
            Logger.log("[" + location + "] Music System already ON.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            Logger.log("[" + location + "] Music System turned OFF.");
        } else {
            Logger.log("[" + location + "] Music System already OFF.");
        }
    }

    @Override
    public void performFunction() {
        if (isOn) {
            Logger.log("[" + location + "] Playing '" + currentTrack + "' at " + volume + "%"
                    + (shuffleMode ? " (shuffle ON)" : ""));
        } else {
            Logger.log("[" + location + "] Music System idle.");
        }
    }

    public void playTrack(String track) {
        this.currentTrack = track;
        if (!isOn) turnOn();
        Logger.log("[" + location + "] Track changed to: " + track);
    }

    public void stop() {
        if (isOn) {
            isOn = false;
            Logger.log("[" + location + "] Music stopped.");
        } else {
            Logger.log("[" + location + "] Music already stopped.");
        }
    }

    public void setVolume(int vol) {
        volume = Math.max(0, Math.min(100, vol));
        Logger.log("[" + location + "] Volume set to " + volume + "%");
    }

    public int getVolume() {
        return volume;
    }

    public boolean isPlaying() {
        return isOn;
    }

    public void enableShuffle() {
        shuffleMode = true;
        Logger.log("[" + location + "] Shuffle mode enabled.");
    }

    public void disableShuffle() {
        shuffleMode = false;
        Logger.log("[" + location + "] Shuffle mode disabled.");
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getName() {
        return "Music System (" + location + ")";
    }
}

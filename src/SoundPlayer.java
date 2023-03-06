

public class SoundPlayer implements Runnable {
    private Sound soundManager;
    private String soundName;

    public SoundPlayer(Sound soundManager, String soundName) {
        this.soundManager = soundManager;
        this.soundName = soundName;
    }

    public void run() {
        soundManager.playSound(soundName);
    }
}

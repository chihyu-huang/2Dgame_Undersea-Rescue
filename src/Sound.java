import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;
import java.util.*;


public class Sound {

//    Clip clip;
//    URL soundURL[] = new URL[30];

    private Map<String, Clip> sounds;

    public Sound() {
        sounds = new HashMap<String, Clip>();
//        soundURL[0] = getClass().getResource("sound/start.wav");
//        soundURL[1] = getClass().getResource("sound/game.wav");
    }
//
//    public void setFile(int i){
//        try{
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL[i]);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioIn);
//        } catch (UnsupportedAudioFileException e) {
//            throw new RuntimeException(e);
//        } catch (LineUnavailableException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void play(){
//        clip.start();
//    }
//
//    public void loop(){
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//    }
//
//    public void stop(){
//        clip.stop();
//    }


    public void loadSound(String name, String filename) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            sounds.put(name, clip);
        } catch (Exception e) {
            System.err.println("Error loading sound " + filename + ": " + e.getMessage());
        }
    }

    public void playSound(String name) {
        Clip clip = sounds.get(name);
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }





//    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
//
//        // Load the audio file
//        AudioInputStream audioStream = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream("sound/DarkTwinklySubSong1.wav"));
//
//        // Create an audio clip
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioStream);
//
//        // Play the audio clip
//        clip.start();
//
//        // Wait for the clip to finish playing
//        Thread.sleep(clip.getMicrosecondLength() / 1000);
//    }

}

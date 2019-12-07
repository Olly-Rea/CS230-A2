package utils;

//JavaFX imports
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Alex Melenikos, Olly Rea
 */
public class SoundHandler {

    private static final String AUDIO_PATH = "./assets/sounds/";
    private String[] sounds = {"Cave", "songMenu1"};

    private MediaPlayer mediaPlayer;

    /**
     * Constructor of the class
     */
    public SoundHandler () {
        File file = new File(AUDIO_PATH + "songMenu1.mp3");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        setVol(0.5);
        play();
    }

    /**
     * Method to play the audio that is selected.
     */
    public void play () {
        mediaPlayer.setAutoPlay(true);
    }

    /**
     * A method to stop the audio that is playing
     */
    public void stop() {
        mediaPlayer.stop();
    }

    /**
     * A method to mute the current song/ambience that is playing
     */
    public void mute() {
        mediaPlayer.setMute(true);
    }

    public void setVol(double newVol) {
        mediaPlayer.setVolume(newVol);
    }

    /**
     * A method to play a sound effect
     *
     * @param soundRef the sound reference for the String array sounds
     */
    public void playSoundEffect(int soundRef) {
        Media media = new Media("file://" + AUDIO_PATH + sounds[soundRef] + ".mp3");
        play();
    }
}

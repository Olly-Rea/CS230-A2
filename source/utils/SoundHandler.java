package utils;

//Java imports
import java.io.File;

//JavaFX imports
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

/**
 * @author Alexandros Melenikos, Olly Rea
 */
public class SoundHandler {

    private static final String AUDIO_PATH = "./assets/sounds/";
    private static final String EXTRAS = "Effects/";

    private static final MediaPlayer BUMP_SOUND;
    private final MediaPlayer tokenSound;
    private static MediaPlayer menuMusic;
    private static final MediaPlayer CAVE_AMBIENCE;

    static {
        BUMP_SOUND = new MediaPlayer(
            new Media(new File(AUDIO_PATH + EXTRAS + "wall_bump.wav").toURI().toString()));
        // Add the menu music and allow it to loop indefinitely
        menuMusic = new MediaPlayer(
            new Media(new File(AUDIO_PATH + "Menu/songMenu1.wav").toURI().toString())); 
        menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        // Add the cave ambience and allow it to loop indefinitely
        CAVE_AMBIENCE = new MediaPlayer(
            new Media(new File(AUDIO_PATH + "Ambience/Cave.wav").toURI().toString())); 
        CAVE_AMBIENCE.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * Constructor of the class
     */
    public SoundHandler() {
        //Initialise the token pickup sound
        tokenSound = new MediaPlayer(
            new Media(new File(AUDIO_PATH + EXTRAS + "collect_gem.mp3").toURI().toString()));
        tokenSound.setVolume(0.2);
        tokenSound.setRate(1.5);

        // Play the menu music
        play(menuMusic);
    }

    /**
     * Method to play any sound
     * 
     * @param mp the MediaPlayer to play the audio from
     */
    public static void playSound(MediaPlayer mp) {
        mp.play();
        mp.setOnEndOfMedia(() -> {
            mp.seek(mp.getStartTime());
            mp.stop();
        });        
    }

    /**
     * Method to play the ambient sound
     */
    public void playAmbience() {
        fadeOut(2, menuMusic);
        play(CAVE_AMBIENCE);
        fadeIn(2, CAVE_AMBIENCE);       
    }
    
    /**
     * Method to play a "bump" sound
     */
    public static void playBump() {
        playSound(BUMP_SOUND);
    }

    /**
     * Method to play a "collect token" sound
     */
    public void playTokenCollect() {
        playSound(tokenSound);
    }

    /**
     * Method to play the audio that is selected.
     */
    public void play(MediaPlayer mp) {
        mp.setAutoPlay(true);
        fadeIn(4, mp);
    }

    /**
     * A method to stop the audio that is playing
     */
    public void stop(MediaPlayer mp) {
        mp.setAutoPlay(false);
        mp.stop();
    }

    /**
     * Method to fade music out
     * 
     * @param length the duration to fade the music out
     */
    public void fadeOut(int length, MediaPlayer mp) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(length), new KeyValue(mp.volumeProperty(), 0)));
        timeline.play();
    }

    /**
     * Method to fade music in
     * 
     * @param length the duration to fade the music in
     */
    public void fadeIn(int length, MediaPlayer mp) {
        mp.setVolume(0);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(length), new KeyValue(mp.volumeProperty(), 0.5)));
        timeline.play();
    }

    /**
     * A method to mute the current song/ambience that is playing
     */
    public void mute(MediaPlayer mp) {
        mp.setMute(true);
    }

    /**
     * A method to set the volume of a MediaPlayer
     * 
     * @param newVol the new volume value (double - range 0 to 1)
     */
    public void setVol(double newVol, MediaPlayer mp) {
        mp.setVolume(newVol);
    }
}

package utils;

import java.io.File;
//JavaFX imports
import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

/**
 * @author Alex Melenikos, Olly Rea
 */
public class SoundHandler {

    private static final String AUDIO_PATH = "./assets/sounds/";
    private static final String EXTRAS = "Extra sounds/";

    private static final MediaPlayer bumpSound;
    private static final MediaPlayer tokenSound;

    static {
        bumpSound = new MediaPlayer(
            new Media(new File(AUDIO_PATH + EXTRAS + "bumping_wall_sound.wav").toURI().toString()));
        tokenSound = new MediaPlayer(
            new Media(new File(AUDIO_PATH + EXTRAS + "collect_gem_sound.mp3").toURI().toString()));
        
    }

    private String[] sounds = { "Cave", "songMenu1" };
    private MediaPlayer mediaPlayer;

    /**
     * Constructor of the class
     */
    public SoundHandler() {
        File file = new File(AUDIO_PATH + "songMenu1.mp3");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        setVol(0.5);
        play();
    }

    public static void playSound(MediaPlayer mp) {
        if (!mp.getStatus().equals(Status.PLAYING)) {
            mp.play();
            mp.setOnEndOfMedia(() -> {
                mp.seek(mp.getStartTime());
                mp.stop();
            });
        }
    }

    public static void playBump() {
        playSound(bumpSound);
    }

    public static void playTokenCollect() {
        tokenSound.setVolume(0.2);
        playSound(tokenSound);
    }

    /**
     * Method to play the audio that is selected.
     */
    public void play() {
        mediaPlayer.setAutoPlay(true);
    }

    /**
     * A method to stop the audio that is playing
     */
    public void stop() {
        mediaPlayer.stop();
    }

    public void fadeOut(int length) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(length), new KeyValue(mediaPlayer.volumeProperty(), 0)));
        timeline.play();
    }

    public void fadeIn(int length) {
        mediaPlayer.setVolume(0);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(length), new KeyValue(mediaPlayer.volumeProperty(), 1)));
        timeline.play();
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

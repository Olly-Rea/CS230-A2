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
    private static final String EXTRAS = "Effects/";

    private static final MediaPlayer bumpSound;
    private static final MediaPlayer tokenSound;

    static {
        bumpSound = new MediaPlayer(
            new Media(new File(AUDIO_PATH + EXTRAS + "wall_bump.wav").toURI().toString()));
        tokenSound = new MediaPlayer(
            new Media(new File(AUDIO_PATH + EXTRAS + "collect_gem.mp3").toURI().toString()));

    }

    private String[] sounds = {"Ambience/Cave.wav", "Menu/songMenu1.wav" };
    private MediaPlayer mediaPlayer;

    /**
     * Constructor of the class
     */
    public SoundHandler() {
        File file = new File(AUDIO_PATH + sounds[1]);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        setVol(0.5);
        play();
    }

    /**
     * Method to play any sound
     *
     * @param mp the MediaPlayer to play the audio from
     */
    public static void playSound(MediaPlayer mp) {
        if (!mp.getStatus().equals(Status.PLAYING)) {
            mp.play();
            mp.setOnEndOfMedia(() -> {
                mp.seek(mp.getStartTime());
                mp.stop();
            });
        }
    }

    /**
     * Method to play a "bump" sound
     */
    public static void playBump() {
        playSound(bumpSound);
    }

    /**
     * Method to play a "collect token" sound
     */
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

    /**
     * Method to fade music out
     *
     * @param length the duration to fade the music out
     */
    public void fadeOut(int length) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(length), new KeyValue(mediaPlayer.volumeProperty(), 0)));
        timeline.play();
    }

    /**
     * Method to fade music in
     *
     * @param length the duration to fade the music in
     */
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

    /**
     * A method to set the volume of a MediaPlayer
     *
     * @param newVol the new volume value (double - range 0 to 1)
     */
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

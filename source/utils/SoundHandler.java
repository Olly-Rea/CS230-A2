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
    private static final MediaPlayer menuMusic;
    private static final MediaPlayer caveAmbience;


    static {
        bumpSound = new MediaPlayer(
            new Media(new File(AUDIO_PATH + EXTRAS + "wall_bump.wav").toURI().toString()));
        tokenSound = new MediaPlayer(
            new Media(new File(AUDIO_PATH + EXTRAS + "collect_gem.mp3").toURI().toString()));
        menuMusic = new MediaPlayer(
            new Media(new File(AUDIO_PATH + "Menu/songMenu1.wav").toURI().toString())); 
        caveAmbience = new MediaPlayer(
            new Media(new File(AUDIO_PATH + "Ambience/Cave.wav").toURI().toString())); 
    }

    /**
     * Constructor of the class
     */
    public SoundHandler() {
        play(menuMusic);
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

    public void playAmbience() {
        fadeOut(2, menuMusic);
        play(caveAmbience);
        fadeIn(2, caveAmbience);
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
    public void play(MediaPlayer mp) {
        mp.setAutoPlay(true);
        fadeIn(4, mp);
    }

    /**
     * A method to stop the audio that is playing
     */
    public void stop(MediaPlayer mp) {
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

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
    private String[] music = {"Menu/songMenu1.wav", "Ambience/Cave.wav"};
    private String[] soundEffects = {"click_press", "collect_boots", "collect_gem", "failed_level", "wall_bump", "water_splash", "win_level"};

    //Create the two media players required for music and sound effects
    private MediaPlayer mediaPlayerMusic;
    private MediaPlayer mediaPlayerEffect;

    /**
     * Constructor of the SoundHandler class
     */
    public SoundHandler () {
        File file = new File(AUDIO_PATH + music[1]);
        Media media = new Media(file.toURI().toString());
        mediaPlayerMusic = new MediaPlayer(media);
        setMusicVol(0.5);
        playMusic();
    }
    
    /**
     * Methods to play the audio that is selected.
     */
    public void playMusic () {
        mediaPlayerMusic.setAutoPlay(true);
    }
    public void playEffect () {
        mediaPlayerEffect.setAutoPlay(true);
    }

    /**
     * Methods to stop the current song/ambience that is playing
     */
    public void stopMusic() {
        mediaPlayerMusic.stop();
    }
    public void stopEffect() {
        mediaPlayerEffect.stop();
    }
    
    /**
     * Methods to mute the current song/ambience that is playing
     */
    public void muteMusic() {
        mediaPlayerMusic.setMute(true);
    }
    public void muteEffect() {
        mediaPlayerEffect.setMute(true);
    }
    
    /**
     * Methods to set the volume for music or an effect
     * 
     * @param newVol the double value to set as the new volume
     */
    public void setMusicVol(double newVol) {
        mediaPlayerMusic.setVolume(newVol);
    }
    public void setEffectVol(double newVol) {
        mediaPlayerEffect.setVolume(newVol);
    }

    /**
     * A method to play a new music file
     *
     * @param musicRef the sound reference for the String array sounds
     */
    public void playNewMusic(int musicRef) {
        File file = new File(AUDIO_PATH + music[musicRef] + ".wav");
        Media media = new Media(file.toURI().toString());
        mediaPlayerMusic = new MediaPlayer(media);
        playMusic();
    }
    /**
     * A method to play a sound effect
     *
     * @param effectRef the sound reference for the String array sounds
     */
    public void playSoundEffect(int effectRef) {
        File file = new File(AUDIO_PATH + "Effect/" + soundEffects[effectRef] + ".wav");
        Media media = new Media(file.toURI().toString());
        mediaPlayerMusic = new MediaPlayer(media);
        playEffect();
    }
}

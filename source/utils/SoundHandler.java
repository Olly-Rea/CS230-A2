package utils;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SoundHandler extends Application {

    private MediaPlayer mediaPlayer;

    //Constructor of the class
    public SoundHandler (String path) {
        File f = new File(path);
        Media media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    //A static method to play the audio that is selected.
    public void play () {
        mediaPlayer.setAutoPlay(true);
    }

    //A static method to stop the audio that is playing
    public void stop() {
        mediaPlayer.pause();
        // mediaPlayer.stop();
    }
    
    //A static method to mute the current song/ambience that is playing
    public void mute() {
        mediaPlayer.setMute(true);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SoundHandler sh = new SoundHandler("./assets/sounds/Menu/songMenu1.mp3");
        sh.play();
    }

}
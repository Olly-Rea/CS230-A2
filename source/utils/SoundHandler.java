package utils;

import javafx.scene.media;

public class SoundHandler {

    //Constructor of the class
    public SoundHandler (String path) {
        Media media = new Media("C:/GitHub/CS230-A2/assets/sounds/Cave background ambience/Cave.mp3");
        mediaPlayer = new MediaPlayer(media);
    }

    //A static method to play the audio that is selected.
    public static void play () {
        mediaPlayer.setAutoPlay(true);
    }

    //A static method to stop the audio that is playing
    public static void stop() {
        mediaPlayer.setOnStopped();
    }
    
    //A static method to mute the current song/ambience that is playing
    public static void mute() {
        mediaPlayer.SetMute(true);
    }

    

      

}

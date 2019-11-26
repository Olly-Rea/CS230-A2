package controllers;

import javafx.scene.input.KeyEvent;
import sun.java2d.cmm.Profile;

/**
 * 
 */
public class GameController {

    private static final String PROFILE_PATH = "...";
    private static final String MAP_DIR = "...";
    private static final String SAVE_DIR = "...";
    private static final String LEADERBOARD_DIR = "...";

    

    private MapController mapController;
    private PlayerController playerController;
    private EntityController entityController;
    private Profile currentProfile;
    private int startTime;
    private String currentMap;

    /**
     * 
     */
    public GameController() {

    }

    /**
     * Loads a path to a map file and to generate objects for use in the game.
     * @param path Path to the map file.
     */
    public void loadGame(String path) {

    }

    /**
     * 
     * @param path
     */
    public void saveGame(String path) {

    }

    /**
     * Returns a list of profiles from the file at {@code PROFILE_PATH}.
     * @return array of profiles retrieved from {@code PROFILE_PATH}.
     */
    public Profile[] loadProfiles() {
        return null;
    }

    /**
     * Adds a profile to the file at {@code PROFILE_PATH} of the name {@code name}.
     * @param name name to be added to the profile list.
     */
    public void addProfile(String name) {
        
    }

    /**
     * Deletes the specific profile from the file at {@code PROFILE_PATH}.
     * @param profile The profile to be deleted.
     */
    public void deleteProfile(Profile profile) {

    }

    /**
     * Progresses the game 1 step and handles the key pressed.
     * @param ke Key Event that was pressed by the user.
     */
    public void gameStep(KeyEvent ke) {

    }

    /**
     * Shows a leaderboard for a specific map in {@code LEADERBOARD_DIR}.
     * @param path The file path inside {@code LEADERBOARD_DIR} for the map.
     */
    public void showLeaderboard(String path) {

    }

    /**
     * adds a time to the map time file in {@code LEADERBOARD_DIR}.
     * @param path THe file path for the map.
     */
    public void addMapTime(String path) {

    }

    /**
     * Loads the next map file.
     */
    private void nextLevel() {

    }
}
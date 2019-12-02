package controllers;

//JavaFX imports
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
//Local imports
import java.util.Scanner;
import cells.Cell;
import entities.Entity;
import entities.Item;
import misc.Profile;
import misc.Menu;
import utils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 */
public class GameController {

    private static final String PROFILE_PATH = "./profile/profiles.txt";
    private static final String MAP_DIR = "...";
    private static final String SAVE_DIR = "...";
    private static final String LEADERBOARD_DIR = "...";
    public static final double SCALE_VAL = 0.6;

    private MapController mapController;
    private PlayerController playerController;
    private EntityController entityController;
    private Menu menu = new Menu(this);
    private Profile currentProfile;
    private int startTime;
    private String currentMap;

    // X and Y variables for render translate methods
    private double renderX = 0;
    private double renderY = 0;
    
    private Group root;
    private Group gameGroup = new Group();

    /**
     *
     */
    public GameController(Group root) {
        this.root = root;
        root.getChildren().add(gameGroup);
        root.getChildren().add(menu.render());
        loadGame("./levelfiles/test3.txt");
    }

    public void restart() {
        loadGame("./levelfiles/test3.txt");
        
        gameGroup.getChildren().clear();
        render();
    }
    
    /**
     * Creates a 2d Entity Map and Cell Map and stores them in the mapController
     * and entityController
     *
     * @param fh The Handler reading the Map/LoadFile
     * @return A 2d array of cells to construct the MapController with
     */
    private void makeControllers(FileHandler fh) {
        String init = fh.nextLine();
        Scanner sc = new Scanner(init);
        int mapWidth = sc.nextInt();
        int mapHeight = sc.nextInt();

        Cell[][] map = new Cell[mapHeight][mapWidth];
        Entity[][] entityMap = new Entity[mapHeight][mapWidth];
        entityController = new EntityController(entityMap);

        for (int y = 0; y < mapHeight; y++) {
            String row = fh.nextLine();
            for (int x = 0; x < mapWidth; x++) {
                char c = row.charAt(x);
                map[y][x] = MapController.makeCell(x, y, c);
                Item e = EntityController.makeItem(x, y, c);
                entityController.addItem(e);
            }
        }
        sc.close();

        mapController = new MapController(map, mapWidth, mapHeight);
        mapController.autotile();
    }

    private void handleSpecific(String line) {
        Scanner sc = new Scanner(line);
        sc.useDelimiter(" ");
        String keyword = sc.next();

        switch (keyword) {
            case "PLAYER":
                playerController = new PlayerController(EntityController.makePlayer(sc));
                break;
            case "ENEMY":
                entityController.addEnemy(EntityController.makeEnemy(sc, playerController.getPlayer()));
                break;
            case "TELEPORTER":
                mapController.linkTeleporters(sc);
                break;
            case "DOOR":
                mapController.initDoor(sc);
                break;
            case "INVENTORY" :
                playerController.createInventory(sc);
                break;
        }

        sc.close();
    }

    /**
     * Loads a path to a map file and to generate objects for use in the game.
     *
     * @param path Path to the map file.
     */
    public void loadGame(String path) {
        FileHandler fh = new FileHandler(path);
        makeControllers(fh);

        // Specific Details
        while (fh.hasNext()) {
            handleSpecific(fh.nextLine());
        }
    }

    /**
     *
     * @param path
     */
    public void saveGame(String path) {
        String[] mapExport = mapController.exportMap(entityController);
        String[] mapSpecific = mapController.exportSpecific();
        String[] playerExport = playerController.export();
        String[] entityExport = entityController.export();

        FileHandler.writeFile(path, mapExport, false);
        FileHandler.writeFile(path, playerExport, true);
        FileHandler.writeFile(path, mapSpecific, true);
        FileHandler.writeFile(path, entityExport, true);
    }

    /**
     * Returns a list of profiles from the file at {@code PROFILE_PATH}.
     *
     * @return array of profiles retrieved from {@code PROFILE_PATH}.
     */
    public Profile[] loadProfiles() {
        // Get total number of profiles
        FileHandler counter = new FileHandler(PROFILE_PATH);
        int arraySize = 0;
        while (counter.hasNext()) {
            arraySize++;
            counter.nextLine();
        }

        Profile[] profileList = new Profile[arraySize];
        FileHandler reader = new FileHandler(PROFILE_PATH);
        int iterate = 0;
        while (reader.hasNext()) {
            String profileString = reader.nextLine();
            if (profileString != "") {
                String[] parts = profileString.split(",");
                String name = parts[0];
                String levelString = parts[1];
                int levelNum = Integer.parseInt(levelString);
                Profile newProfile = new Profile(name, levelNum);
                profileList[iterate] = newProfile;
                iterate++;
            }
        }
        return profileList;
    }

    /**
     * Adds a profile to the file at {@code PROFILE_PATH} of the name
     * {@code name}.
     *
     * @param name name to be added to the profile list.
     */
    public void addProfile(String name) {
        Profile newProfile = new Profile(name, 0, PROFILE_PATH);
    }

    /**
     * Deletes the specific profile from the file at {@code PROFILE_PATH}.
     *
     * @param profile The profile to be deleted.
     */
    public void deleteProfile(Profile profile) {
        String toDelete = profile.getName();
        Profile[] oldList = loadProfiles();
        String[] newList = new String[oldList.length - 1];
        int j = 0;
        for (int i = 0; i < oldList.length; i++) {
            if (oldList[i].getName().equals(toDelete) == false) {
                newList[j] = oldList[i].getName() + "," + oldList[i].getLevel();
                j++;
            }
        }
        FileHandler deleter = new FileHandler(PROFILE_PATH);
        deleter.writeFile(PROFILE_PATH, newList, false);
    }

    /**
     * Progresses the game 1 step and handles the key pressed.
     *
     * @param ke Key Event that was pressed by the user.
     */
    public void gameStep(KeyEvent e) {
        // Get the firection to move in
        Direction dir = null;
        switch (e.getCode()) {
            case W:
            case UP:
                dir = Direction.UP;
                break;
            case A:
            case LEFT:
                dir = Direction.LEFT;
                break;
            case S:
            case DOWN:
                dir = Direction.DOWN;
                break;
            case D:
            case RIGHT:
                dir = Direction.RIGHT;
                break;
            case ESCAPE:
                menu.toggle();
                return;
            default:
                return;
        }
        if (menu.isVisible()) {
            return;
        }

        //Make the move based on this direction
        playerController.move(dir, mapController);
        renderPlayer();

        // Check entity grid
        entityController.checkItem(playerController.getPlayer());

        // Update enemies
        entityController.moveEnemies(mapController);

        // Check if player is dead
        if (playerController.checkStatus(mapController)
                || entityController.enemyCollision(playerController.getPlayer())) {

            System.out.println("YOU DIED");
            restart();
        }

        // Check if game is won
        if (playerController.checkGoal(mapController)) {
            System.out.println("YOU WIN");
            // Win game
        }
    }

    /**
     * Shows a leaderboard for a specific map in {@code LEADERBOARD_DIR}.
     *
     * @param path The file path inside {@code LEADERBOARD_DIR} for the map.
     */
    public void showLeaderboard(String path) {

    }

    /**
     * adds a time to the map time file in {@code LEADERBOARD_DIR}.
     *
     * @param path THe file path for the map.
     */
    public void addMapTime(String path) {

    }

    /**
     * Initial render method to display the map and orient it to the player 
     * start position
     * 
     */
    public void render() {

        // Group 1 ("world layer")
        Group worldGroup = new Group();
        // Render map layer First
        GridPane mapLayer = mapController.renderMap();
        mapLayer.getTransforms().add(new Scale(SCALE_VAL, SCALE_VAL, 0, 0));
        worldGroup.getChildren().add(mapLayer);
        // Render Entity layer Second (on top of Map)
        GridPane entityLayer = entityController.renderEntities();
        entityLayer.getTransforms().add(new Scale(SCALE_VAL, SCALE_VAL, 0, 0));
        worldGroup.getChildren().add(entityLayer);

        
        // Group 2 ("player layer")
        Group playerGroup = new Group();
        // Render Player in center of screen last
        GridPane playerLayer = playerController.renderPlayer();
        playerLayer.getTransforms().add(new Scale(SCALE_VAL, SCALE_VAL, 0, 0));
        playerGroup.getChildren().add(playerLayer);
        // Render the feather-edge effect around the outside of the screen
        Image assetImg;
        try {
            assetImg = new Image(new FileInputStream("./assets/visuals/Feather_Edge.png"));
        } catch (FileNotFoundException e) {
            assetImg = null;
            System.err.println("Feather_Edge.png path not found");
        }
        ImageView featherEdge = new ImageView(assetImg);
        featherEdge.getTransforms().add(new Scale(SCALE_VAL, SCALE_VAL, 0, 0));
        playerGroup.getChildren().add(featherEdge);

        
        //Add the two layers to the gameGroup layer
        gameGroup.getChildren().add(worldGroup);
        gameGroup.getChildren().add(playerGroup);
        renderPlayer();

    }

    public void renderPlayer() {

        //Calculate the value the playerLayer offsets the player by
        double playerOffset = 400*SCALE_VAL;
        //Offset the map to focus on the player start position
        if (playerController.getPlayerPos().getX() > 1) {
            renderX = ((playerController.getPlayerPos().getX()-1)*(-200*SCALE_VAL)) + playerOffset;
        } else {
            renderX = (playerController.getPlayerPos().getX()) + playerOffset;
        }
        if (playerController.getPlayerPos().getY() > 1) {
            renderY = ((playerController.getPlayerPos().getY()-1)*(-200*SCALE_VAL)) + playerOffset;
        } else {
            renderY = (playerController.getPlayerPos().getY()) + playerOffset;
        }

        ((Group)root.getChildren().get(0)).getChildren().get(0).setLayoutX(renderX);
        ((Group)root.getChildren().get(0)).getChildren().get(0).setLayoutY(renderY);

    }
}

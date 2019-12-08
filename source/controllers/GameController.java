package controllers;

//JavaFX imports
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
//Local imports
import java.util.Scanner;
import cells.Cell;
import entities.Entity;
import entities.Item;
import misc.Leaderboard;
import misc.Profile;
import menus.*;
import utils.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Game controller manages the logic of the game. It creates the other three
 * controllers and contains the methods to access them. It is also responsible
 * for out of game functions such as: saving and loading, profile manipulation,
 * Leaderboard creation and player rendering
 *
 * @version 1.0
 * @author Scott Barr, Olly Rea, Daniel Clenaghan
 */
public class GameController {

    private static final String PROFILE_PATH = "./profile/profiles.txt";
    private static final String MAP_DIR = "...";
    private static final String SAVE_DIR = "./savefiles/";
    private static final String LEADERBOARD_DIR = "./leaderboards/";
    public static final double SCALE_VAL = 0.6;

    // Create the game controllers
    private MapController mapController;
    private PlayerController playerController;
    private EntityController entityController;

    // Create the utility handler classes
    private SoundHandler soundHandler;
    // Create the game menus
    private GameMenu gameMenu = new GameMenu(this);
    private SelectProfileMenu selectProfileMenu = new SelectProfileMenu(this);
    private LeaderboardMenu leaderboardMenu = new LeaderboardMenu(this);
    private LevelMenu levelMenu = new LevelMenu(this, soundHandler);
    private CreateProfileMenu createProfileMenu = new CreateProfileMenu(this);
    private SplashScreen splashScreen;

    // Create all other variables required for game runtime
    private Profile currentProfile;
    private double startTime;
    private double loadTime;
    private String currentMap;
    private int level;
    private boolean runtime = false;

    // X and Y variables for render translate methods
    private double renderX = 0;
    private double renderY = 0;

    private Group root;
    private Group gameGroup = new Group();

    /**
     * Constructor for the GameController class
     */
    public GameController(Group root) {
        // Create the motd handler and add it to the SplashScreen
        String motdURL = "http://cswebcat.swan.ac.uk";
        String puzzle = RequestHandler.get(motdURL + "/puzzle");
        String code = RequestHandler.decipher(puzzle);
        final String motd = RequestHandler.get(motdURL + "/message?solution=" + code);
        splashScreen = new SplashScreen(this, motd);

        this.root = root;
        root.getChildren().add(gameGroup);
        root.getChildren().add(gameMenu.render());
        root.getChildren().add(levelMenu.render());
        root.getChildren().add(leaderboardMenu.render());
        root.getChildren().add(selectProfileMenu.render());
        root.getChildren().add(createProfileMenu.render());
        root.getChildren().add(splashScreen.render());
        // Display the splashScreen
        splashScreen.toggle(selectProfileMenu);
        // Instantiate the soundHandler
        soundHandler = new SoundHandler();
    }

    /**
     * Restarts the game from the last loaded file - if save is loaded it will
     * restart from the saved location and not the level itsefl.
     */
    public void restart() {
        loadGame(currentMap);
    }

    /**
     * Creates a 2d Entity Map and Cell Map and stores them in the mapController and
     * entityController. The first integer is the level number - this is used to
     * allow us to get the next level number which is stored in the levelMenu static
     * array. Next 2 integers are the map width and map height.
     *
     * @param fh The Handler reading the Map/LoadFile
     * @return A 2d array of cells to construct the MapController with
     */
    private void makeControllers(FileHandler fh) {
        String init = fh.nextLine();
        Scanner sc = new Scanner(init);
        level = sc.nextInt();
        sc.close();
        init = fh.nextLine();
        sc = new Scanner(init);
        int mapWidth = sc.nextInt();
        int mapHeight = sc.nextInt();

        Cell[][] map = new Cell[mapHeight][mapWidth]; // Initialise the 2d Cell array
        Entity[][] entityMap = new Entity[mapHeight][mapWidth]; // Initialise the 2d Entity array
        entityController = new EntityController(entityMap); // Initialise the entity controller with the empty 2d Entity
                                                            // array

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

    /**
     * Handles the map details described after the main map has been created.
     * PLAYER, ENEMY, TELEPORTER, DOOR, INVENTORY and TIME specifics are handled.
     *
     * @param line The string being used to determine what type of specific is being
     *             created.
     */
    private void handleSpecific(String line) {
        Scanner sc = new Scanner(line);
        sc.useDelimiter(" ");
        String keyword = sc.next();

        switch (keyword) {
        case "PLAYER":
            playerController = new PlayerController(EntityController.makePlayer(sc), soundHandler);
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
        case "INVENTORY":
            playerController.createInventory(sc);
            break;
        case "TIME":
            loadTime(sc);
            break;
        }

        sc.close();
    }

    /**
     * Loads a path to a map file and to generate objects for use in the game.
     * Initialises the controllers and handles the map specifics.
     *
     * @param path Path to the map file.
     */
    public void loadGame(String path) {
        loadTime = 0;
        FileHandler fh = new FileHandler(path);
        makeControllers(fh);

        // Specific Details
        while (fh.hasNext()) {
            handleSpecific(fh.nextLine());
        }

        // sets the currentMap to the path used - this is to restart to saves if a save
        // is loaded
        currentMap = path;
        if (levelMenu.isVisible()) {
            levelMenu.toggle();
        }

        // if this is the first game load, replace the menu music with the ambience music
        if (runtime == false) {
            soundHandler.playAmbience();
        }

        startTime = currentTimeMillis();
        render();
    }

    /**
     * Method to return a new savefile. Gets all controllers exports and writes them
     * all to one file including the current time spent on the level.
     *
     * @param path path to save data to
     */
    public void saveGame(String saveName) {
        String[] mapExport = mapController.exportMap(entityController);
        String[] mapSpecific = mapController.exportSpecific();
        String[] playerExport = playerController.export();
        String[] entityExport = entityController.export();

        String path = SAVE_DIR + currentProfile.getName() + "/" + saveName + ".txt";

        FileHandler.writeFile(path, level, false);
        FileHandler.writeFile(path, mapExport, true);
        FileHandler.writeFile(path, playerExport, true);
        FileHandler.writeFile(path, mapSpecific, true);
        FileHandler.writeFile(path, entityExport, true);
        addMapTime(path);
    }

    /**
     * Method which sets the profile being used and also proceeds to the next stage
     * after profile selection which is the level selection.
     *
     * @param p The profile object being used for the game.
     */
    public void setProfile(Profile p) {
        this.currentProfile = p;
        levelMenu.loadLevels(p.getLevel());
        levelMenu.toggle();
    }

    /**
     * Method to change the selectProfileMenu to the createProfileMenu.
     */
    public void createProfile() {
        selectProfileMenu.toggle();
        createProfileMenu.toggle();
    }

    /**
     * This method loads the levelMenu with all the saves under the current profile.
     */
    public void loadSaves() {
        levelMenu.loadSaves(currentProfile);
        levelMenu.toggle();
    }

    /**
     * Toggles the leaderboardMenu then loads the levels up to the profiles maximum
     * level and toggles the levelMenu itself.
     */
    public void toLevelSelect() {
        leaderboardMenu.toggle();
        levelMenu.loadLevels(currentProfile.getLevel());
        levelMenu.toggle();
    }

    /**
     * Converts current system time to Integer
     */
    public static int currentTimeMillis() {
        return (int) (System.currentTimeMillis());
    }

    /**
     * Loads saved time from map file
     *
     * @param sc scanner
     */
    public void loadTime(Scanner sc) {
        loadTime = sc.nextDouble();
    }

    /**
     * Toggles the leaderboardMenu and loads the next level.
     */
    public void nextLevel() {

        leaderboardMenu.toggle();

        // If the level is currently higher than the profile being used then increment
        // the profiles highest level.
        if (level + 1 > currentProfile.getLevel()) {
            currentProfile.incLevel(level);
            currentProfile.deleteProfile();
            currentProfile.saveProfile();
        }

        if (level == LevelMenu.levels.length) {
            restart();
            return;
        } else {
            currentMap = "./levelfiles/" + LevelMenu.levels[level] + ".txt";
            loadGame(currentMap);
        }
    }

    /**
     * Progresses the game 1 step and handles the key pressed.
     *
     * @param e Key Event that was pressed by the user.
     */
    public void gameStep(KeyEvent e) {

        if (!runtime) {
                // Add the back button to the level select menu for future appearences
                levelMenu.addBackBtn(leaderboardMenu);
                //Change the menu music to the gameplay ambience music
                runtime = true;
        }

        // Asserts that no menu is visible to continue
        if (splashScreen.isVisible() || levelMenu.isVisible() || leaderboardMenu.isVisible()) {
            return;
        }

        // Get the direction to move in
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
            gameMenu.toggle();
            return;
        default:
            return;
        }

        if (gameMenu.isVisible()) {
            return;
        }

        // Make the move based on this direction
        playerController.move(dir, mapController);
        // Update the player asset so that the player is facing the last direction moved
        playerController.getPlayer().updatePlayerAsset(dir);
        playerController.renderPlayer();
        renderPlayer();

        // Check entity grid
        entityController.checkItem(playerController.getPlayer(), soundHandler);
        playerController.renderPlayer();
        renderPlayer();

        // Update enemies
        entityController.moveEnemies(mapController, entityController);

        // Check if player is dead
        if (playerController.checkStatus(mapController)
                || entityController.enemyCollision(playerController.getPlayer())) {
            restart();
        }

        // Check if game is won
        if (playerController.checkGoal(mapController)) {
            System.out.println("YOU WIN");
            double time = 0.00;
            time = (currentTimeMillis() - startTime + loadTime) / 1000;
            System.out.println("You took " + time + " seconds!");

            addTime(time);
            leaderboardMenu.displayPlayer(currentProfile, time);
            leaderboardMenu.loadLeaderboard(level, this);
            leaderboardMenu.toggle();
        }
    }

    /**
     * Adds the time to the leaderboard file for the level
     *
     * @param time The time in seconds
     */
    public void addTime(double time) {
        String fullPath = LEADERBOARD_DIR + "Level_" + level + "_lb";
        System.out.println(level);
        Leaderboard lb = new Leaderboard(fullPath);

        lb.addTime(currentProfile, time);
    }

    /**
     * Shows a leaderboard for a specific map in {@code LEADERBOARD_DIR}.
     *
     * @param path The file path inside {@code LEADERBOARD_DIR} for the map.
     */
    public ArrayList<String> getLeaderboard() {
        if (level == 0) {
            String fullPath = LEADERBOARD_DIR + "Level_1" + "_lb";
            Leaderboard lb = new Leaderboard(fullPath);
            return lb.displayBoard();
        } else {
            String fullPath = LEADERBOARD_DIR + "Level_" + level + "_lb";
            Leaderboard lb = new Leaderboard(fullPath);
            return lb.displayBoard();
        }
    }

    /**
     * adds a time to the map time file in {@code LEADERBOARD_DIR}.
     *
     * @param path THe file path for the map.
     */
    public void addMapTime(String path) {
        double saveTime = currentTimeMillis() - startTime;
        // String timeAsString = Integer.toString(saveTime);
        // String[] output = { "TIME", timeAsString };
        FileHandler.writeFile(path, "TIME " + saveTime, true);
    }

    /**
     * Initial render method to display the map and orient it to the player start
     * position
     */
    public void render() {
        if (currentMap != null) {
            gameGroup.getChildren().clear();

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
            playerLayer.getTransforms().add(new Scale(SCALE_VAL + 0.2, SCALE_VAL + 0.2, 0, 0));
            playerGroup.getChildren().add(playerLayer);
            playerGroup.getChildren().get(0).setLayoutX(-200 * SCALE_VAL + 0.2);
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

            // Add the two layers to the gameGroup layer
            gameGroup.getChildren().add(worldGroup);
            gameGroup.getChildren().add(playerGroup);
            renderPlayer();
        }
    }

    /**
     * Changes the position of the map under the player level based on the players
     * location. Calculate half the width of the Players GridPane and then translate
     * the map and Entity GridPane the x and y value of the "offset".
     */
    public void renderPlayer() {
        // Calculate the value the playerLayer offsets the player by
        double playerOffset = (400 * 1.2) * SCALE_VAL + 0.2;
        // Offset the map to focus on the player start position
        if (playerController.getPlayerPos().getX() > 1) {
            renderX = ((playerController.getPlayerPos().getX() - 1) * (-200 * SCALE_VAL)) + playerOffset;
        } else {
            renderX = (playerController.getPlayerPos().getX()) + playerOffset;
        }
        if (playerController.getPlayerPos().getY() > 1) {
            renderY = ((playerController.getPlayerPos().getY() - 1) * (-200 * SCALE_VAL)) + playerOffset;
        } else {
            renderY = (playerController.getPlayerPos().getY()) + playerOffset;
        }
        // render the map and entity layer beehind the player - adjusted for current
        // scaling values
        ((Group) root.getChildren().get(0)).getChildren().get(0).setLayoutX(renderX - 30);
        ((Group) root.getChildren().get(0)).getChildren().get(0).setLayoutY(renderY + 10);
    }
}

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
import misc.LeaderboardMenu;
import misc.Profile;
import misc.SelectProfileMenu;
import misc.GameMenu;
import misc.LevelMenu;
import utils.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

    private static final String SAVE_DIR = "./savefiles/";
    private static final String LEADERBOARD_DIR = "./leaderboards/";
    public static final double SCALE_VAL = 0.6;

    private MapController mapController;
    private PlayerController playerController;
    private EntityController entityController;
    private GameMenu gameMenu = new GameMenu(this);
    private LevelMenu levelMenu = new LevelMenu(this);
    
    private SelectProfileMenu selectProfileMenu = new SelectProfileMenu(this);
    private LeaderboardMenu leaderboardMenu = new LeaderboardMenu(this);
    private Profile currentProfile;
    private int startTime;
    private int loadTime;
    private String currentMap;
    private int level;

    // X and Y variables for render translate methods
    private double renderX = 0;
    private double renderY = 0;

    private Group root;
    private Group gameGroup = new Group();

    /**
     * Constructor for the GameController class
     */
    public GameController(Group root) {
        this.root = root;
        root.getChildren().add(gameGroup);
        root.getChildren().add(gameMenu.render());
        root.getChildren().add(levelMenu.render());
        root.getChildren().add(leaderboardMenu.render());
        root.getChildren().add(selectProfileMenu.render());

        selectProfileMenu.toggle();
    }

    public void restart() {
        loadGame(currentMap);
    }
    
    public void toLevelSelect() {
		leaderboardMenu.toggle();
		levelMenu.toggle();
	}

    /**
     * Creates a 2d Entity Map and Cell Map and stores them in the mapController and
     * entityController
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

        currentMap = path;
        if (levelMenu.isVisible()) {
            levelMenu.toggle();
        }

        startTime = currentTimeMillis();
        render();
    }

    /**
     * Method to return a new savefile
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

    public void setProfile(Profile p) {
        this.currentProfile = p;
        selectProfileMenu.toggle();
        levelMenu.loadLevels(p.getLevel());
        levelMenu.toggle();
    }

    public void loadSaves() {
        levelMenu.loadSaves(currentProfile);
        levelMenu.toggle();
    }

    /**
     * Converts current system time to Integer
     *
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
        loadTime = sc.nextInt();
    }

    public void nextLevel() {

        leaderboardMenu.toggle();

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
            gameMenu.toggle();
            return;
        case F1:
            levelMenu.toggle();
            return;
        case F2:
            selectProfileMenu.toggle();
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
        entityController.checkItem(playerController.getPlayer());
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

            int time = currentTimeMillis() - startTime + loadTime;
            System.out.println("You took " + time / 1000 + " seconds!");

            addTime(time);
			leaderboardMenu.displayPlayer(currentProfile, time);
			leaderboardMenu.loadLeaderboard(level,this);
			leaderboardMenu.toggle();
        }
    }
    public void addTime(int time) {
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
        int saveTime = currentTimeMillis() - startTime;
        // String timeAsString = Integer.toString(saveTime);
        // String[] output = { "TIME", timeAsString };
        FileHandler.writeFile(path, "TIME "+saveTime, true);
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

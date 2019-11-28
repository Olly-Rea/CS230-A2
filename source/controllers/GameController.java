package controllers;

//JavaFX imports
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;
//Local imports
import java.util.Scanner;
import cells.Cell;
import entities.Entity;
import misc.Profile;
import utils.*;

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
        loadGame("./savefiles/Test_File.txt");
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

        for (int y = 0; y < mapHeight; y++) {
            String row = fh.nextLine();
            for (int x = 0; x < mapWidth; x++) {
                char c = row.charAt(x);
                map[y][x] = MapController.makeCell(x, y, c);
                entityMap[y][x] = EntityController.makeEntity(x, y, c);
            }
        }
        sc.close();

        mapController = new MapController(map, mapWidth, mapHeight);
        entityController = new EntityController(entityMap);
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
        // Get MapController Export
        // Get PlayerController Export
        // Get EntityController Export
    }

    /**
     * Returns a list of profiles from the file at {@code PROFILE_PATH}.
     *
     * @return array of profiles retrieved from {@code PROFILE_PATH}.
     */
    public Profile[] loadProfiles() {
        return null;
    }

    /**
     * Adds a profile to the file at {@code PROFILE_PATH} of the name
     * {@code name}.
     *
     * @param name name to be added to the profile list.
     */
    public void addProfile(String name) {

    }

    /**
     * Deletes the specific profile from the file at {@code PROFILE_PATH}.
     *
     * @param profile The profile to be deleted.
     */
    public void deleteProfile(Profile profile) {

    }

    /**
     * Progresses the game 1 step and handles the key pressed.
     * @param ke Key Event that was pressed by the user.
     */
    public void gameStep(KeyEvent ke) {

      // Move player
      switch (ke.getCode()) {
        case RIGHT:
          playerController.move(Direction.RIGHT, mapController);
          break;
        case LEFT:
          playerController.move(Direction.LEFT, mapController);
          break;
        case UP:
          playerController.move(Direction.UP, mapController);
          break;
        case DOWN:
          playerController.move(Direction.DOWN, mapController);
          break;
        case ESCAPE:
          // Bring up menu?
          break;
        default:
          // Do nothing
          break;

      // Move enemy
        entityController.moveEnemies(map);


          // Check if player is dead

        // if (playerController.checkStatus(mapController) ||
        // entityController.enemyCollision(playerController.getPlayer())){
        //   // Restart game
        // }

        // event.consume;

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

    public void render(Group root) {

        // Group ("layer") 1
        // Render map layer First
        GridPane mapLayer = mapController.renderMap();
        mapLayer.getTransforms().add(new Scale(0.35, 0.35, 0, 0));
        root.getChildren().add(mapLayer);
        // Render Entity layer Second (on top of Map)
        GridPane entityLayer = entityController.renderEntities();
        entityLayer.getTransforms().add(new Scale(0.35, 0.35, 0, 0));
        root.getChildren().add(entityLayer);

        // Group ("layer") 2
        // Render Player in center of screen last
    }
}

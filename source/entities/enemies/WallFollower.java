package entities.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//Local imports
import cells.Cell;
import cells.CellType;
import cells.Ground;
import cells.Wall;
import controllers.EntityController;
import controllers.MapController;
import utils.Direction;
import utils.Rotation;
import utils.Vector;
import entities.Enemy;
import entities.Player;
//JavaFX imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class describing a WallFollower Enemy which follows a wall anticlockwise or
 * clockwise.
 *
 * @author Scott Barr
 * @version 1.0
 */
public class WallFollower extends Enemy {

    /**
     * Path to the WallFollower image
     */
    private static final String ASSET_PATH = "./assets/visuals/entities/enemies/wallFollower.png";
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("WallFollower image path not found");
        }
    }

    private Direction dir;
    private Rotation type;

    /**
     * Creates a new WallFollower enemy at position {@code pos}.
     *
     * @param pos the initial position of the enemy
     * @param dir the initial facing direction of the enemy
     * @param type the type of enemy, anticlockwise or clockwise
     */
    public WallFollower(Vector pos, Player player, Direction dir, Rotation type) {
        super(pos, player);
        this.dir = dir;
        this.type = type;
    }

    /**
     * Calculates the WallFollowers next move based on the map.
     *
     * @param map MapController used to figure out the surroundings of the
     * enemy.
     */
    public void algorithm(MapController map, EntityController ec) {
        while (checkWall(map, ec) && (!(map.getNextCell(pos, dir) instanceof Ground) ||  ec.entityPresent(pos, dir))) {
            turn(type.reverse());
        }

        if (!checkWall(map, ec)) {
            turn(type);
        }

        if (!pos.equals(player.getPos())) {
            this.pos.add(dir);
        }
    }

    /**
     * Checks the wall based off the type of WallFollower and it's position
     *
     * @param map MapController used to find the cell in the direction needed
     * @return True if there is a wall, false if there is not a wall.
     */
    private boolean checkWall(MapController map, EntityController ec) {
        Direction checkDir = type == Rotation.ACW ? dir.acw() : dir.cw();
        Cell checkCell = map.getNextCell(pos, checkDir);
        boolean existsEntity = ec.entityPresent(pos, checkDir);
        return (!(checkCell instanceof Ground) || existsEntity);
    }

    /**
     * Turns the facing direction in the direction specified.
     *
     * @param rot the direction to turn
     */
    private void turn(Rotation rot) {
        if (rot == Rotation.ACW) {
            dir = dir.acw();
        }
        if (rot == Rotation.CW) {
            dir = dir.cw();
        }
    }
    
    /**
     * Generates a string containing this enemies direction, location and type
     * 
     * @return String
     */
    public String export() {
        return String.format("WF %d %d %s %s", pos.getX(), pos.getY(), dir, type);
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }

}

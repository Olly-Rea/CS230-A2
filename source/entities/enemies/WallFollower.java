package entities.enemies;

import cells.Cell;
import cells.CellType;
//Local imports
import controllers.MapController;
import utils.Direction;
import utils.Rotation;
import utils.Vector;
import entities.Enemy;
//JavaFX imports
import javafx.scene.image.Image;

/**
 * A class describing a WallFollower Enemy which follows a wall anticlockwise or clockwise.
 * 
 * @author ???, Scott Barr
 */
public class WallFollower extends Enemy {

    /**
     * Path to the WallFollower image
     */
    private static Image sprite = new Image("...");

    private Direction dir;
    private Rotation type;
    private Vector pos;

    /**
     * Creates a new WallFollower enemy at position {@code pos}.
     *
     * @param pos the initial position of the enemy
     * @param dir the initial facing direction of the enemy
     * @param type the type of enemy, anticlockwise or clockwise 
     */
    public WallFollower(Vector pos, Direction dir, Rotation type) {
        super(pos);
        this.pos = pos;
        this.type = type;
    }

    /**
     * Calculates the WallFollowers next move based on the map.
     *
     * @param map MapController used to figure out the surroundings of the
     * enemy.
     */
    public void algorithm(MapController map) {
        Cell next = map.getCell(pos, dir);
        while (checkWall(map) && next.getType() == CellType.WALL) {
            turn(type.reverse());
        }

        if (!checkWall(map)) turn(type);

        this.pos.add(dir);
    }

    /**
     * Checks the wall based off the type of WallFollower and it's position
     * 
     * @param map MapController used to find the cell in the direction needed
     * @return True if there is a wall, false if there is not a wall.
     */
    private boolean checkWall(MapController map) {
        Direction checkDir = type == Rotation.ACW ? dir.acw() : dir.cw();
        Cell checkCell = map.getCell(pos, checkDir);
        return checkCell.getType() == CellType.WALL;
    }

    /**
     * Turns the facing direction in the direction specified.
     * 
     * @param rot the direction to turn
     */
    private void turn(Rotation rot) {
        if (rot == Rotation.ACW) { dir = dir.acw(); }
        if (rot == Rotation.CW) { dir = dir.cw(); }
    }

    /**
     * Renders the enemy at the enemies position
     */
    public void render() {

    }

}

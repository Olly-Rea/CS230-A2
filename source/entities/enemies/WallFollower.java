package entities.enemies;

//Local imports
import cells.Cell;
import cells.Ground;
import controllers.EntityController;
import controllers.MapController;
import utils.Direction;
import utils.Rotation;
import utils.Vector;
import entities.Enemy;
import entities.Player;

//Java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//JavaFX imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Subclass of the Enemy class; WallFollower A class describing a WallFollower
 * Enemy which follows a wall anticlockwise or clockwise.
 *
 * @author Scott Barr
 * @version 1.0
 */
public class WallFollower extends Enemy {

    /**
     * Path to the WallFollower image
     */
    private Image image;

    private Direction dir;
    private Rotation type;

    /**
     * Creates a new WallFollower enemy at position {@code pos}.
     *
     * @param pos  the initial position of the enemy
     * @param player The Player object used to check if the player is in contact with the enemy
     * @param dir  the initial facing direction of the enemy
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
     * @param map MapController used to figure out the surroundings of the enemy.
     * @param ec  The entityController primarily used for checking if items are in
     *            the enemies way.
     */
    public void algorithm(MapController map, EntityController ec) {
        while (checkWall(map, ec) && (!(map.getNextCell(pos, dir) instanceof Ground) || ec.entityPresent(pos, dir))) {
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
     * Exports the SmartTargeter enemy as a single lined description.
     * 
     * @return The description of the enemy in X Y WF DIRECTION ROTATION where X Y
     *         is the horizontal and vertical position of the enemy, DIRECTION is
     *         the current facing direction and rotation is the type of wall
     *         follower (ACW or CW).
     */
    public String export() {
        return String.format("%d %d WF %s %s", pos.getX(), pos.getY(), dir, type);
    }

    /**
     * Renders the Enemy to the screen
     * 
     * @return The ImageView of the WallFollowers current asset.
     */
    public ImageView render() {
        String currAsset = ASSET_PATH + "Wall/";
        if (dir != null) {
            switch (dir) {
            case UP:
                currAsset += "Scorpion_UP";
                break;
            case DOWN:
                currAsset += "Scorpion_DOWN";
                break;
            case LEFT:
                currAsset += "Scorpion_LEFT";
                break;
            case RIGHT:
                currAsset += "Scorpion_RIGHT";
                break;
            }
        } else {
            currAsset += "Scorpion_DOWN";
        }

        try {
            image = new Image(new FileInputStream(currAsset + ".png"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("SmartTargeter image path not found");
        }

        return new ImageView(image);
    }

}

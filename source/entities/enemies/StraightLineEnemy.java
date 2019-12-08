package entities.enemies;

//Local imports
import cells.Cell;
import cells.Ground;
import controllers.EntityController;
import controllers.MapController;
import entities.Enemy;
import entities.Player;
import utils.Direction;
import utils.Vector;

//Java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//JavaFX imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Subclass of the Enemy class; StraightLineEnemy A class describing a straight
 * line enemy which can either move horizontally or vertically
 *
 * @author Scott Barr
 * @version 1.0
 */
public class StraightLineEnemy extends Enemy {

    private static Image image;

    private Direction dir;

    /**
     * Static Constructor for the static image of the straight line follower .
     */
    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + "straightLine.png"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("StraightLineEnemy image path not found");
        }
    }

    /**
     * Straight line.
     *
     * @param vector the position of the straight line enemy
     * @param dir    The direction the enemy is facing initially
     */
    public StraightLineEnemy(Vector pos, Player player, Direction dir) {
        super(pos, player);
        this.dir = dir;
    }

    /**
     * The algorithm used by the enemy to calculate next move, checks whether the
     * next cell in the direction it is facing is a wall, if so it turns around.
     *
     * @param map The MapController needed to check if the next move is valid.
     * @param ec  EntityController used to check if other enemies are in the
     *            vicinity.
     */
    public void algorithm(MapController map, EntityController ec) {
        Cell next = map.getNextCell(pos, dir);
        boolean existsEntity = ec.entityPresent(pos, dir);
        if (!(next instanceof Ground) || existsEntity) {
            dir = dir.cw().cw();
        }

        if (!pos.equals(player.getPos())) {
            this.pos.add(dir);
        }
    }

    /**
     * Exports the SmartTargeter enemy as a single lined description.
     * 
     * @return The description of the enemy in X Y ST DIRECTION where X Y is the
     *         horizontal and vertical position of the enemy, DIRECTION is the
     *         current facing direction.
     */
    public String export() {
        return String.format("%d %d SL %s", pos.getX(), pos.getY(), dir);
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}

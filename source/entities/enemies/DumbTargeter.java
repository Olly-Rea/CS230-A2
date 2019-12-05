package entities.enemies;

//Local imports
import controllers.MapController;
import controllers.EntityController;
import cells.Cell;
import cells.Ground;
import entities.Enemy;
import entities.Player;
import utils.Direction;
import utils.Vector;

//Java imports
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//JavaFX imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Subclass of the Enemy class; DumbTargeter
 * 
 * @author Scott Barr
 */
public class DumbTargeter extends Enemy {

    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + "Dumb/Mummy_Up.png"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("DumbTargeter image path not found");
        }
    }

    /**
     * The player.
     */
    private Direction dir;

    /**
     * Instantiates a new targeting enemy.
     *
     * @param vector the position of targeting enemy
     */
    public DumbTargeter(Vector vector, Player p) {
        super(vector, p);
    }

    /**
     * Algorithm calculates based off the map and the player where it will
     * attempt to move next.
     *
     * @param map The MapController to obtain the details of the environment
     */
    public void algorithm(MapController map, EntityController ec) {

        ArrayList<Direction> potential = getDirection();
        dir = null;
        // For any potential directions check whether that direction is a valid move
        for (int i = 0; i < potential.size(); i++) {
            Direction d = potential.get(i);
            Cell next = map.getNextCell(pos, d); 
            boolean existsEntity = ec.entityPresent(pos, d);
            if (next instanceof Ground && !existsEntity) {
                dir = d;
            }
        }
        
        if (!player.getPos().equals(pos) && dir != null) {
            pos.add(dir);
        }
    }

    /**
     * Method to get the current direction of the DumbTargeter
     * 
     * @return an ArrayList of potential directions the DumbTargeter could head
     */
    private ArrayList<Direction> getDirection() {
        // Initialise variables
        Vector playerPos = player.getPos();
        ArrayList<Direction> potential = new ArrayList<>();
        // If players x position > enemy pos then RIGHT is potential
        if (playerPos.getX() > pos.getX()) {
            potential.add(Direction.RIGHT);
        } else if (playerPos.getX() < pos.getX()) { // If player x position < enemy pos then LEFT potential
            potential.add(dir = Direction.LEFT);
        }

        // If players y position > enemy pos then UP is potential
        if (playerPos.getY() > pos.getY()) {
            potential.add(Direction.DOWN);
        } else if (playerPos.getY() < pos.getY()) { // If players y position > enemy pos then UP is potential
            potential.add(dir = Direction.UP);
        }


        return potential;
    }

    /**
     * Method to export the specifics of this DumbTargeter to a String
     * 
     * @return a String containing the specifics of this enemy, as required by 
     *         the map file format 
     */
    public String export() {
        return String.format("%d %d DT", pos.getX(), pos.getY());
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }

}

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
 * Will always move in the lateral direction of the player
 *
 * @author Scott Barr
 * @version 1.0
 */
public class DumbTargeter extends Enemy {

    private Image image;

    private Direction dir = null;

    /**
     * Instantiates a DumbTargeter enemy.
     *
     * @param vector the position of targeting enemy
     * @param p the player it will move towards
     */
    public DumbTargeter(Vector vector, Player p) {
        super(vector, p);
        try {
            image = new Image(new FileInputStream(ASSET_PATH + "Dumb/Mummy_Up.png"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("DumbTargeter image path not found");
        }
    }

    /**
     * Algorithm calculates based off the map and the player where it will
     * attempt to move next.
     *
     * @param map The MapController to obtain the details of the environment
     * @param ec  The entityController primarily used for checking if items are in
     *            the enemies way.
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
     * 
     * @return The ImageView of the DumbTargeters current asset.
     */
    public ImageView render() {
        String currAsset = "Dumb/";
        if (dir != null) {
            switch (dir) {
                case UP:
                    currAsset += "Mummy_Up";
                    break;
                case DOWN:
                    currAsset += "Mummy_Down";
                    break;
                case LEFT:
                    currAsset += "Mummy_Left";
                    break;
                case RIGHT:
                    currAsset += "Mummy_Right";
                    break;
            } 
        } else {
            currAsset += "Mummy_Down";
        }

        try {
            image = new Image(new FileInputStream(ASSET_PATH + currAsset + ".png"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("DumbTargeter image path not found");
        }

        return new ImageView(image);
    }
}

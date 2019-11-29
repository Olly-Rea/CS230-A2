package entities.enemies;

//Local imports
import controllers.MapController;
import cells.Cell;
import cells.CellType;
import entities.Enemy;
import entities.Player;
import utils.Direction;
import utils.Vector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//Java imports
import java.util.ArrayList;
//JavaFX imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DumbTargeter extends Enemy {

    private static final String ASSET_PATH = "./assets/visuals/entities/enemies/dumbTargeter.png";
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("DumbTargeter image path not found");
        }
    }

    /**
     * The player.
     */
    private Player player;
    private Direction dir;

    /**
     * Instantiates a new targeting enemy.
     *
     * @param vector the position of targeting enemy
     */
    public DumbTargeter(Vector vector, Player p) {
        super(vector);
        this.player = p;
    }

    /**
     * Algorithm calculates based off the map and the player where it will
     * attempt to move next.
     *
     * @param map The MapController to obtain the details of the environment
     */
    public void algorithm(MapController map) {
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

        // For any potential directions check whether that direction is a valid move
        for (int i = 0; i < potential.size(); i++) {
            Direction d = potential.get(i);
            Cell next = map.getNextCell(pos, d);
            if (next.getType() == CellType.GROUND) {
                dir = d;
            }
        }

        pos.add(dir);
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        ImageView imageNode = new ImageView(image);
        return imageNode;
    }

}

package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to render the goal cell
 * 
 * @version 1.0
 * @author Scott Barr
 */
public class Goal extends Cell {

    // Static
    private static final String IMAGE_NAME = "Goal";
    private static Image image;

    /**
     * Static constructor to instnatiate the static image
     */
    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Goal image path not found");
        }
    }

    /**
     * Implementation of isLightSource
     * 
     * @return false
     */
    public boolean isLightSource() {
        return false;
    }

    /**
     * Instantiates a new Goal cell.
     * 
     * @param x The horizontal position of the cell
     * @param y The vertical position of the cell
     */
    public Goal(int x, int y) {
        super(CellType.GOAL, x, y);
    }

    /**
     * Return the character used in the map file for this cell
     * 
     * @return char
     */
    public char getChar() {
        return '!';
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}

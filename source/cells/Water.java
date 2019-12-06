package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to render the water cell
 * @version 1.0
 * @author TODO
 */

public class Water extends Cell {

    private static final String IMAGE_NAME = "Water";
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Water image path not found");
        }
    }
    
    /**
     * Create a water cell at location (x,y)
     * @param x
     * @param y
     */
    public Water(int x, int y) {
        super(CellType.WATER, x, y);
    }

    /**
     * Return the character used in the map file for this cell
     * @return char
     */
    public char getChar() {
        return 'W';
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}
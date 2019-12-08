package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to render the fire cell
 * @version 1.0
 * @author Scott Barr
 */
public class Fire extends Cell {

    // Static path to the fire image
    private static final String IMAGE_NAME = "Fire";
    private static Image image;

    /**
     *  Static constructor to instantiate the static image.
     */  
    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Water image path not found");
        }
    }

    /**
     * Create a fire cell at location (x,y)
     * @param x horizontal position of the cell
     * @param y vertical position of the cell
     */
    public Fire(int x, int y) {
        super(CellType.FIRE, x, y);
    }

    /**
     * Implementation of isLightSource 
     * 
     * @return true (fire is a light source)
     */
    public boolean isLightSource() {
        return true;
    }

    /**
     * Return the character used in the map file for this cell
     * @return char
     */
    public char getChar() {
        return 'F';
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}
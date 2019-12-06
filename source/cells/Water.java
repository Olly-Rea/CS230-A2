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

    private Image image;

    /**
     * Set image from file location
     * @param path
     */
    public void setImage(String path) {
        // String path = ASSET_PATH + "walls/" + imageName;
        try {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Wall image path not found at '" + path + "'");
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
    public boolean isLightSource() {
        return false;
    }

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

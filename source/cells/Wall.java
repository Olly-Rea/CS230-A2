package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to render the wall cell
 * @version 1.0
 * @author TODO
 */

public class Wall extends Cell {

    private Image image = null;

    /**
     * Create a wall cell at location (x,y)
     * @param x The horizontal position of the cell
     * @param y The vertical position of the cell
     */
    public Wall(int x, int y) {
        super(CellType.WALL, x, y);
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
    * Set image from a file location
    * @param path The path to wall asset
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
     * Return the character used in the map file for this cell
     * @return char
     */
    public char getChar() {
        return '#';
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}

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
     * @param x
     * @param y
     */
    public Wall(int x, int y) {
        super(CellType.WALL, x, y);
    }

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
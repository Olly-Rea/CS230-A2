package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to render the fire cell
 * @version 1.0
 * @author TODO
 */
public class Fire extends Cell {

    private static final String IMAGE_NAME = "Fire";
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
     * Create a fire cell at location (x,y)
     * @param x
     * @param y
     */
    public Fire(int x, int y) {
        super(CellType.FIRE, x, y);
    }

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
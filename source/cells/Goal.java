package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to render the goal cell
 * @version 1.0
 * @author TODO
 */
public class Goal extends Cell {

    private static final String IMAGE_NAME = "Goal";
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Goal image path not found");
        }
    }
    
    /**
     * Create a goal cell at location (x,y)
     * @param x
     * @param y
     */
    public Goal(int x, int y) {
        super(CellType.GOAL, x, y);
    }

    /**
     * Return the character used in the map file for this cell
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
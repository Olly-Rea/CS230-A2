package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to render the ground cell
 * @version 1.0
 * @author TODO
 */

public class Ground extends Cell {

    private Image image;

    /**
     * Create a ground cell at a given location (x,y)
     * @param x
     * @param y
     */
    public Ground(int x, int y) {
        super(CellType.GROUND, x, y);
    }
    
    public boolean isLightSource() {
        return false;
    }
    
    public void setImage(String path) {
        try {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Ground image path not found at '" + path + "'");
        }
    }

    /**
     * The char used to generate this cell in the map text file
     * @return char
     */
    public char getChar() {
        return ' ';
    }

    /**
     * Renders the Ground cell to the screen
     *
     * @return an ImageView of the ground cell asset
     */
    public ImageView render() {
        return new ImageView(image);
    }

    /**
     * Method to change the asset of a ground tile to have debris after a
     * boulder has been broken
     */
    public void addDebris() {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + "Boulders/Boulder_Broken.jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Boulder_Broken image path not found");
        }
    }
}

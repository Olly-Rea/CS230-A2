package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//Local Imports
import utils.Vector;

/**
 * Teleporter.java
 *
 * @version 1.0.0
 * @author Daniel Clenaghan
 */
/**
 * The class for the cell type Teleporter. It will store a Vector for both
 * itself and a linked Teleporter
 */
public class Teleporter extends Cell {

    private static final String IMAGE_NAME = "Teleporter";
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Ground image path not found");
        }
    }

    private Teleporter linkedTele; // Vector location of linked teleporter

    /**
     * Create a cell of type teleporter with a given position in the map
     */
    public Teleporter(int x, int y) {
        super(CellType.TELEPORTER, x, y);
    }

    public boolean isLightSource() {
        return false;
    }
    
    /**
     * Method to link two teleporters together
     *
     * @param t1 The teleporter 1 to be linked with t2
     * @param t2 The teleporter 2 to be linked with t1
     */
    public static void link(Teleporter t1, Teleporter t2) {
        t1.linkedTele = t2;
        t2.linkedTele = t1;
    }

    /**
     * Method to link two teleporters together
     *
     * @param tele The teleporter to be linked
     */
    public void linkTele(Vector tele) {

    }

    /**
     * Method to get the linked teleporter's location
     *
     * @return linkedTele The vector of the linked teleporter
     */
    public Teleporter getLinked() {
        return linkedTele;
    }

    public char getChar() {
        return 'T';
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }

}

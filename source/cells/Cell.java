package cells;

import utils.Vector;

//JavaFX imports
import javafx.scene.image.ImageView;

/**
 * The super class for all cell types. Has general methods to return location and cell type
 * 
 * @version 1.0
 * @author Scott Barr
 */
public abstract class Cell {

    //The type of cell (i.e ground, wall)
    private final CellType type;
    //The Vector for the Cell
    protected final Vector cellPos;
    //The asset path for all cells
    protected static final String ASSET_PATH = "./assets/visuals/cells/";

    /**
     * Cell Constructor; Instantiates a new cell.
     *
     * @param type the type of cell being instantiated
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     */
    public Cell(CellType type, int x, int y) {
        this.type = type;
        cellPos = new Vector(x, y);
    }

    /**
     * Methods to return the asset image as a JavaFX 'Image' for the cell
     *
     * @return the ImageView node for the MapController GridPane
     */
    public abstract ImageView render();
    public abstract boolean isLightSource();
    
    /**
     * Method to return the type of cell.
     *
     * @return the type of cell in question
     */
    public CellType getType() {
        return type;
    }

    /**
     * Method to return the vector position of the Cell
     *
     * @return the cells position within the map
     */
    public Vector getPos() {
        return cellPos;
    }
    
    /**
     * Return the character used in the map file for this cell
     * @return char
     */
    public abstract char getChar();
}

package cells;

//Local imports
import utils.Vector;

//JavaFX imports
import javafx.scene.image.ImageView;

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
     * @param x the x ordinate of the cell
     * @param y the y ordinate of the cell
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
    
    public abstract char getChar();
}

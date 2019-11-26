package cells;

import javafx.scene.image.Image;
import utils.Vector;

public class Cell {

	//The type of cell (i.e ground, wall)
	private CellType type;

    // The Vector for the Cell
    protected final Vector cellPos;

    // The asset path for all cells
    private String assetPath = "../../assets/visuals/cells/";

    // The JavaFX image that the asset image will be stored as
    private Image assetImg;

    /**
     * Cell Constructor; Instantiates a new cell.
     *
     * @param type the type of cell being instantiated
     * @param x
     * @param y
     */
    public Cell(CellType type, int x, int y) {
        this.type = type;
        cellPos = new Vector(x, y);
        // Assign asset Image dependent on cell type
        switch (this.type) {
        case WALL:
            assetPath += "wall_1";
            break;
        case GROUND:
            assetPath += "Floor_Dark";
            break;
        case FIRE:
            assetPath += "Fire";
            break;
        case WATER:
            assetPath += "Water";
            break;
        case TELEPORTER:
            // assetPath += " " ;
            break;
        case DOOR:
            assetPath += "Boulder";
            break;
        case GOAL:
            assetPath += "Goal";
            break;
        default:
            assetPath += "Floor_Dark";
            break;
        }
        // Add the filetype to the end of the path
        assetPath += ".jpg";
        // Create the asset Image for the render method
        assetImg = new Image(assetPath, true);
    }

    /**
     * Method to return the asset image as a JavaFX 'Image' for the cell
     */
    public Image render() {
        return assetImg;
    }

    // Overloaded method to overwrite assetPath (used purely for wall cells atm)
    public Image render(String assetPath) {
        assetImg = new Image(assetPath, true);
        return assetImg;
    }

    /**
     * Gets the type of cell.
     *
     * @return the type of cell in question
     */
    public CellType getType() {
        return type;
    }

    public Vector getVector() {
        return cellPos;
    }
}

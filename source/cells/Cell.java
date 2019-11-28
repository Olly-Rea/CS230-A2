package cells;

//Local imports
import utils.Vector;
//JavaFX imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell {

    //The type of cell (i.e ground, wall)
    private final CellType type;
    //The Vector for the Cell
    protected final Vector cellPos;
    //The asset path for all cells
    private String assetPath = "../../assets/visuals/cells/";
    //The JavaFX image that the asset image will be stored as
    private Image assetImg;

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
        //Assign asset Image dependent on cell type
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
                //assetPath += " " ;
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
        //Add the filetype to the end of the path
        assetPath += ".jpg";
        //Create the asset Image for the render method
        assetImg = new Image(assetPath, true);
    }

    /**
     * Methods to return the asset image as a JavaFX 'Image' for the cell
     *
     * @return the ImageView node for the MapController GridPane
     */
    public ImageView render() {
        ImageView imageNode = new ImageView();
        imageNode.setImage(assetImg);
        return imageNode;
    }

    //Overloaded method to overwrite assetPath (used purely for wall cells atm)
    public ImageView render(String assetPath) {
        assetImg = new Image(assetPath, true);
        ImageView imageNode = new ImageView();
        imageNode.setImage(assetImg);
        return imageNode;
    }

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
}

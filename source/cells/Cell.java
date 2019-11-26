package cells;

import javafx.scene.image.Image;

public class Cell {

	//The size of the cell
	public static final int SIZE = 10000000;

	//The type of cell (i.e ground, wall)
	private CellType type;

	public final int x;
	public final int y;
        public String assetPath = "../../assets/visuals/cells/";
        Image assetImg;

	/**
	 * Instantiates a new cell.
	 *
	 * @param type the type of cell being instantiated
	 */
	public Cell (CellType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
                switch (this.type) {
                        case WALL:
                            assetPath += "wall_1" ;
                            break;
                        case GROUND:
                            assetPath += "Floor_Dark";
                            break;
                        case FIRE:
                            //assetPath += " " ;
                            break;
                        case WATER:
                            //assetPath += " " ;
                            break;
                        case TELEPORTER:
                            //assetPath += " " ;
                            break;
                        case DOOR:
                            //assetPath += " " ;
                            break;
                        case GOAL:
                            //assetPath += " " ;
                            break;
                        default:
                            //assetPath += " " ;
                            break;
                    }
                //Add the filetype to the end of the path
                assetPath += ".jpg";
                //Create the asset Image for the render method
                assetImg = new Image(assetPath, true);
	}

	/**
	 * Method to return the asset image as a JavaFX 'Image' for the cell
	 */
	public Image render() {
            return assetImg;
            
	}
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
            return null;
	}
}

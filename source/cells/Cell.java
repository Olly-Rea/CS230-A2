package cells;

public class Cell {

    //The type of cell (i.e ground, wall)
    private CellType type;
    private String assetPath;

    /**
     * Cell constructor; Instantiates a new cell.
     *
     * @param type the type of cell being instantiated
     */
    public Cell(CellType type) {

    }

    //Using a javaFX gridPane means we don't need an x or y value for the render method
    
    /**
     * Method(s) to render the cell
     */
    public void render() {
        //Funky JavaFX stuff - best to return a JavaFX object
        switch (type) {
            case WALL:
                //
                break;
            case GROUND:
                //
                break;
            case FIRE:
                //
                break;
            case WATER:
                //
                break;
            case TELEPORTER:
                //
                break;
            case DOOR:
                //
                break;
            case GOAL:
                //
                break;
            default:
                //
                break;
        }

    }
    //Second render method for special cases
    public void render(String assetPath) {
        this.assetPath = assetPath;
        //Funky JavaFX stuff
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

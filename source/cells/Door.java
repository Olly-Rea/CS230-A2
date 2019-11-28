package cells;

import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Door.
 *
 * @author advait kumar
 * @version 1.00
 */
public class Door extends Cell {

    //The door type
    public final DoorType doorType;

    //The token amount
    private int tokens = 0;

    /**
     * Door Constructor; Instantiates a new door.
     *
     * @param cellType
     * @param doorType
     * @param x
     * @param y
     */
    public Door(int x, int y, DoorType doorType) {
        super(CellType.DOOR, x ,y);
        this.doorType = doorType;
        // this.CellType = DOOR;
    }

    // public Door(CellType type, int x, int y, int tokens) {
    //     // DOOR_TYPE = TOKEN;
    //     // this.tokens = tokens;
    // }

    /**
     * Sets the number of tokens.
     *
     * @param tokens the new number of tokens
     */
    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    /**
     * Gets the tokens.
     *
     * @return the tokens
     */
    public int getTokens() {
        return tokens;
    }

    /**
     * Gets the door type.
     *
     * @return the door type
     */
    public DoorType getDoorType() {
        return doorType;
    }

}

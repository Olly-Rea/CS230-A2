package cells;

import entities.Player;

/**
 * The super class for cells of type door.
 *
 * @author Advait Kumar
 * @version 1.00
 */
public abstract class Door extends Cell {

    /**
     * Door Constructor; Instantiates a new door.
     *
     * @param cellType
     * @param x
     * @param y
     */
    public Door(int x, int y) {
        super(CellType.DOOR, x, y);
    }

    /**
     * Checks a player's inventory to see if they have the requirements to open a door
     * @param p
     * @return boolean 
     */
    public abstract boolean isOpenable(Player p);
}

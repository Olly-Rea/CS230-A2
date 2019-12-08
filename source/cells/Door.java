package cells;

import entities.Player;

/**
 * The super class for cells of type door.
 *
 * @author Advait Kumar, Scott Barr
 * @version 1.00
 */
public abstract class Door extends Cell {

    /**
     * Door Super Constructor; Instantiates a new door.
     *
     * @param x horizontal position in the map grid of the door
     * @param y vertical position in the map grid of the door
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

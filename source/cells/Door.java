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
     * @param x The horizontal position of the cell
     * @param y The vertical position of the cell
     */
    public Door(int x, int y) {
        super(CellType.DOOR, x, y);
    }

    /**
     * Checks a player's inventory to see if they have the requirements to open a
     * door
     * 
     * @param p The player object used to check if the playe can open the door
     * @return True if the player can open the door, otherwise false.
     */
    public abstract boolean isOpenable(Player p);
}

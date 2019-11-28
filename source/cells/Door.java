package cells;

import entities.Player;

/**
 * The Class Door.
 *
 * @author advait kumar
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

    public abstract boolean isOpenable(Player p);
}

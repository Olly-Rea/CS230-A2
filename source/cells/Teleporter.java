package cells;

//Local Imports
import utils.Vector;

/**
 * Teleporter.java
 *
 * @version 1.0.0
 * @author Daniel Clenaghan
 */
/**
 * The class for the cell type Teleporter. It will store a Vector for both
 * itself and a linked Teleporter
 */
public class Teleporter extends Cell {

    private Teleporter linkedTele; // Vector location of linked teleporter

    /**
     * Create a cell of type teleporter with a given position in the map
     */
    public Teleporter(int x, int y) {
        super(CellType.TELEPORTER, x, y);
    }

    /**
     * Method to link two teleporters together
     *
     * @param t1 The teleporter 1 to be linked with t2
     * @param t2 The teleporter 2 to be linked with t1
     */
    public static void link(Teleporter t1, Teleporter t2) {
        t1.linkedTele = t2;
        t2.linkedTele = t1;
    }

    /**
     * Method to link two teleporters together
     *
     * @param tele The teleporter to be linked
     */
    public void linkTele(Vector tele) {

    }

    /**
     * Method to get the linked teleporter's location
     *
     * @return linkedTele The vector of the linked teleporter
     */
    public Teleporter getLinked() {
        return linkedTele;
    }
}

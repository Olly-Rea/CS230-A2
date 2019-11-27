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

    private Vector position; // Vector location of this teleporter
    private Vector linkedTele; // Vector location of linked teleporter

    /**
     * Create a cell of type Teleporter with a given position in the map
     *
     * @param position
     * @param teleporter
     */
    public Teleporter(Vector position, cellType teleporter) {
        super.cellType = TELEPORTER;

    }

    /**
     * Method to link two Teleporters together
     *
     * @param tele The Teleporter to be linked
     */
    public void linkTele(Vector tele) {

    }

    /**
     * Method to get the Teleporter's location
     *
     * @return position this Teleporter's location
     */
    public Vector getPos() {
        return position;
    }

    /**
     * Method to get the linked Teleporter's location
     *
     * @return linkedTele The vector of the linked Teleporter
     */
    public Vector getLinkedPos() {
        return linkedTele;
    }
}

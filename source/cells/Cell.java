package cells;

import javafx.scene.paint.Color;

public class Cell {

	/** The size of the cell*/
	public static final int SIZE = 10000000;

	/** The colour of the cell. */
	private Color colour;

	/** The type of cell (i.e ground, wall). */
	private CellType type;

	public final int x;
	public final int y;

	/**
	 * Instantiates a new cell.
	 *
	 * @param type the type of cell being instantiated
	 */
	public Cell (CellType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	/**
	 * Renders the cell
	 *
	 * @param x the x coordinate where the cell is being placed
	 * @param y the y coordinate where the cell is being placed
	 */
	public void render(int x, int y) {

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

package entities;

import utils.Vector;

/**
 * @author James Hogg
 * @version 1.0 An Entity is a non-cell sometimes non-static object within the
 *          environment
 *
 */
public abstract class Entity {

	/**
	 * Holds the current position of the entity on the Map
	 */
	protected Vector pos;

	/**
	 * Constructs an entity at a position taken from the Map
	 *
	 * @param pos The position at which the entity will be created
	 */
	public Entity(int x, int y) {
		this.pos = new Vector(x, y);
	}

	/**
	 * Constructs a entity with a vector
	 *
	 * @param vector
	 */
	public Entity(Vector vector) {
		this.pos = vector;
	}

	/**
	 * Gets the current position of the entity from the Map
	 *
	 * @return gives position as type Vector
	 */
	public Vector getPos() {
		return pos;
	}

	/**
	 * Renders the entity on the screen
	 */
	public abstract void render();

}

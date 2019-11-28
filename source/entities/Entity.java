package entities;

import utils.Vector;

/**
 * @author James Hogg
 * @version 1.0 An Entity is a non-cell sometimes non-static object within the
 * environment
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
     * @param x The x position value for the entity
     * @param y The y position value for the entity
     */
    public Entity(int x, int y) {
        this.pos = new Vector(x, y);
    }

    /**
     * Constructs an enttiy from a vector
     * 
     * @param pos Position vector of the entity
     */
    public Entity(Vector pos) {
        this.pos = pos;
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

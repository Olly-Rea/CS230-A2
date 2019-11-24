package entities;

/**
 * @author James Hogg
 * @version 1.0 An Entity is a non-cell sometimes non-static object within the
 * environment
 *
 */
public class Entity {

    /**
     * Holds the current position of the entity on the Map
     */
    protected Vector Pos;

    /**
     * Gets the current positon of the entity from the Map
     *
     * @return gives position as type Vector
     */
    public Vector getPos() {
        return null;
    }

    /**
     * Renders the entity on the screen
     */
    public void render() {
        
    }

    /**
     * Constructs an entity at a postion taken from the Map
     *
     * @param x
     * @param Pos The positon at which the entity will be created
     */
    public Entity(int x int y) int y) {
        Pos = getPos();
    }

}

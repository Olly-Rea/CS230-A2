package entities;

import javafx.scene.image.Image;
import utils.Direction;

public class WallFollower extends Enemy {
    
    /** Needed to find whether the wall follower's rotation is clockwise or anti clockwise */
    public enum Rotation { CW, ACW; }

    /** Path to the WallFollower image */
    private static Image sprite = new Image("...");

    private Direction dir = Direction.UP;
    private Vector pos;
    private Rotation type;

    /**
     * Creates a new WallFollower enemy at position {@code pos}.
     * @param pos
     */
    public WallFollower(Vector pos, Rotation type) {
        this.pos = pos;
        this.type = type;
    }    

    /**
     * Calculates the wallfollowers next move based on the map.
     * @param map MapController used to figure out the surroundings of the enemy.
     */
    public void algorithm(MapController map) {
        
    }

    /**
     * Renders the enemy at the enemies position 
     */
    public void render() {

    }

}
package entities;

import controllers.MapController;
import controllers.PlayerController;
import utils.Vector;

/**
 * @author James Hogg
 * @version 1.0 An Enemy is a player-hostile entity that moves according to an
 * algorithm based on the environment
 */

public abstract class Enemy extends Entity{

    /**
     * Constructs an enemy
     */
    public Enemy(Vector pos) {
        super(pos);
    }
    /**
     * Provides the next move based on the current map state
     *
     * @param Map The current map state
     * @return Returns a vector containing the next position
     */
    public abstract void algorithm(MapController map);
    
    /**
     * Checks to see if the enemy has collided with the player
     */
    public boolean PlayerCheck(PlayerController playerCon) {
        return this.pos == playerCon.getPlayerPos() ? true : false;
    }

}

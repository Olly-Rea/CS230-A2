package entities;

import controllers.*;
import utils.Vector;

/**
 * @author James Hogg
 * @version 1.0 An Enemy is a player-hostile entity that moves according to an
 * algorithm based on the environment
 */

public class Enemy {

    /**
     * Provides the next move based on the current map state
     *
     * @param Map The current map state
     * @return Returns a vector containing the next position
     */
    public Vector pathingAlgorithm(Mapcontroller mapC) {
        return null;
    }
    
    /**
     * Checks to see if the enemy has collided with the player
     */
    protected boolean PlayerCheck(MapController mapC, PlayerController PlayerC) {
        return false;
    }

    /**
     * Constructs an enemy
     */
    public Enemy() {
    }
}

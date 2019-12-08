package entities;

import controllers.EntityController;
import controllers.MapController;
import controllers.PlayerController;
import utils.Vector;

/**
 * An Enemy is a player-hostile entity that moves according to an algorithm
 * based on the environment
 *
 * @author James Hogg, Scott Barr
 * @version 1.0
 */
public abstract class Enemy extends Entity {

    protected Player player;
    protected static final String ASSET_PATH = "./assets/visuals/entities/Enemies/";

    /**
     * Instaniates a new Enemy
     * 
     * @param pos    The Vector position of the Enemy
     * @param player The Reference to the player
     */
    public Enemy(Vector pos, Player player) {
        super(pos);
        this.player = player;
    }

    /**
     * Checks to see if the enemy has collided with the player
     * 
     * @param playerCon The playerController needed to check if the enemy is in
     *                  contact with the player
     * @return A boolean value, true if the player is in contact with the current
     *         enemy.
     */
    public boolean playerCheck(PlayerController playerCon) {
        return this.pos == playerCon.getPlayerPos() ? true : false;
    }

    /**
     * Provides the next move based on the current map state
     *
     * @param map The current map state
     * @param ec  The entityController primarily used for checking if items are in
     *            the enemies way.
     */
    public abstract void algorithm(MapController map, EntityController ec);

    /**
     * Export method to create a definition of the current enemy.
     * 
     * @return A string form of the Enemy with all the specific details needed to
     *         recreate.
     */
    public abstract String export();
}

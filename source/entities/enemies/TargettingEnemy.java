package entities.enemies;

import entities.Player;
import utils.Vector;

public abstract class TargettingEnemy extends Enemy{

	/** The player. */
	private Player player;

	/**
	 * Instantiates a new targetting enemy.
	 *
	 * @param vector the position of targetting enemy
	 */
	public TargettingEnemy(Vector vector, Player p) {
		super(vector);
		this.player = p;
	}

}

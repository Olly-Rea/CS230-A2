package entities.enemies;

import cells.Cell;
import cells.CellType;
import controllers.MapController;
import entities.Enemy;
import utils.Direction;
import utils.Vector;

public class StraightLineEnemy extends Enemy {

	private Direction dir;

	/**
	 * Straight line.
	 *
	 * @param vector the position of th straight line enemy
	 */
	public StraightLineEnemy(Vector pos, Direction dir) {
		super(pos);
		this.dir = dir;
	}

	/**
	 * The algorithm used by the enemy to calculate next move
	 *
	 * @param map the map
	 */
	public void algorithm(MapController map) {
		Cell next = map.getCell(pos, dir);
		if (next.getType() == CellType.WALL) {
			dir = dir.cw().cw();
		}

		this.pos.add(dir);
	}

    /**
     * Renders the enemy
     */
    public void render() {

	}
}

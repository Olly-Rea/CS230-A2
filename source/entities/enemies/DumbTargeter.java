package entities.enemies;

import java.util.ArrayList;

import cells.Cell;
import cells.CellType;
import controllers.MapController;
import entities.Enemy;
import entities.Player;
import utils.Direction;
import utils.Vector;
import javafx.scene.image.Image;

public abstract class DumbTargeter extends Enemy {

	private static final Image SPRITE = new Image("...");

	/** The player. */
	private Player player;
	private Direction dir;

	/**
	 * Instantiates a new targetting enemy.
	 *
	 * @param vector the position of targetting enemy
	 */
	public DumbTargeter(Vector vector, Player p) {
		super(vector);
		this.player = p;
	}

	/**
	 * Algorithm calculates based off the map and the player where it will attempt
	 * to move next.
	 * 
	 * @param map The MapController to obtain the details of the environment
	 */
	public void algorithm(MapController map) {
		// Initialise variables
		Vector playerPos = player.getPos();
		ArrayList<Direction> potential = new ArrayList<>();

		// If players x position > enemy pos then RIGHT is potential
		if (playerPos.getX() > pos.getX()) {
			potential.add(Direction.RIGHT);
		} else if (playerPos.getX() < pos.getX()) { // If player x position < enemy pos then LEFT potential
			potential.add(dir = Direction.LEFT);
		}

		// If players y position > enemy pos then UP is potential
		if (playerPos.getY() > pos.getY()) {
			potential.add(Direction.DOWN);
		} else if (playerPos.getY() < pos.getY()) { // If players y position > enemy pos then UP is potential
			potential.add(dir = Direction.UP);
		}

		// For any potential directions check whether that direction is a valid move
		for (int i = 0; i < potential.size(); i++) {
			Direction d = potential.get(i);
			Cell next = map.getCell(pos, d);
			if (next.getType() == CellType.GROUND) {
				dir = d;
			}
		}

		pos.add(dir);
	}

	/**
	 * Renders the Enemy to the screen
	 */
	public void render() {

	}

}

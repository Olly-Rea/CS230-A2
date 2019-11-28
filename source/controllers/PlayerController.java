package controllers;

import entities.*;
import cells.*;
import utils.*;

/**
 * Class to handle player movement and rendering
 *
 * @author Scott, Danny
 */
public class PlayerController {

	private Player player;

	public Player getPlayer() {
		return player;
	}

	/**
	 * Takes a direction and determines if the player can move into the desired cell
	 * based on their inventory
	 *
	 * @param dir the direction the player is attempting to move
	 * @param mc  the map controller to find the Cell in the dir
	 * @author Danny
	 */
	public void move(Direction dir, MapController mc) {
		Vector current = getPlayerPos();
		// Find cell to be tested
		Vector desired = null;
		if (dir == Direction.UP) {
			desired = new Vector(current.getX(), current.getY() + 1);
		} else if (dir == Direction.DOWN) {
			desired = new Vector(current.getX(), current.getY() - 1);
		} else if (dir == Direction.LEFT) {
			desired = new Vector(current.getX() - 1, current.getY());
		} else if (dir == Direction.RIGHT) {
			desired = new Vector(current.getX() + 1, current.getY());
		}
		Cell desiredCell = mc.getCell(desired.getX(), desired.getY());
		// If move is valid, update map
		if (validMove(desiredCell)) {
			player.setPos(desired);
			// mc.moveMap(dir);
		}
	}

	/**
	 * Private method to determine if a move is valid based on player inventory
	 *
	 * @param cell the cell in the direction the player wants to move
	 * @return a boolean value true if the move is valid, i.e not walikng into a
	 *         wall, false if the move is invalid and not possible
	 * @author Danny
	 */
	private boolean validMove(Cell targetCell) {
		CellType moveType = targetCell.getType();
		Boolean valid = null;
		if (moveType == (CellType.GROUND) || moveType == (CellType.FIRE) || moveType == (CellType.WATER)) {
			valid = true;
		} else if (moveType == (CellType.TELEPORTER)) {
			// valid = false but calls map and player to update location separately?
			// player.setPos(Cell.getLinkedPos());
		} else if (moveType == (CellType.DOOR)) {
			Door targetDoor = ((Door) targetCell);
			if (targetDoor.getDoorType() == DoorType.RED) {
				Item redKey = new Item(ItemType.REDKEY, 0, 0);
				valid = player.useItem(redKey);
			} else if (targetDoor.getDoorType() == DoorType.BLUE) {
				Item blueKey = new Item(ItemType.BLUEKEY, 0, 0);
				valid = player.useItem(blueKey);
			} else if (targetDoor.getDoorType() == DoorType.YELLOW) {
				Item yellowKey = new Item(ItemType.YELLOWKEY, 0, 0);
				valid = player.useItem(yellowKey);
			} else if (targetDoor.getDoorType() == DoorType.GREEN) {
				Item greenKey = new Item(ItemType.GREENKEY, 0, 0);
				valid = player.useItem(greenKey);
			} else if (targetDoor.getDoorType() == DoorType.TOKEN) {
				valid = player.useTokens(((Door) targetCell).getTokens());
			}
		}
		return valid;
	}

	/**
	 * Check if the cell requires a shoe type and if the player posseses it
	 *
	 * @param cell the cell being checked against the player, if a certain item is
	 *             required for that cell type
	 * @return a boolean value, if true, the player is on a block which kills the
	 *         player, otherwise player is still alive
	 * @author Danny
	 */
	public boolean checkStatus(Cell cell) {
		Boolean isDead = false;
		if (cell.getType() == CellType.FIRE) {
			if (player.hasFireBoots() == false) {
				isDead = true;
			}
		} else if (cell.getType() == CellType.WATER) {
			if (player.hasFlippers() == false) {
				isDead = true;
			}
		}
		return isDead;
	}

	/**
	 * @return Returns the vector of the players current position
	 * @author Danny
	 */
	public Vector getPlayerPos() {
		return player.getPos();
	}

	/**
	 * Renders the player
	 *
	 * @author xxxxx
	 */
	public void render() {
	}

	/**
	 * Method to get a int array {X,Y,fireShoes, flippers,tokens, red,
	 * green,blue,yellow}
	 *
	 * @return an array of ints representing the players position and inventory
	 * @author Danny
	 */
	public int[] export() {
		return player.export();
	}
}

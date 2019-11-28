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
		Cell target = mc.getNextCell(player.getPos(), dir);
		if (target.getType() == CellType.DOOR) {
			if (((Door)target).isOpenable(player)) {
				mc.openDoor(player.getPos().getX(), player.getPos().getY());
			}
		}

		if (validMove(target)) {
			Vector pos = player.getPos();
			if (target.getType() == CellType.TELEPORTER) {
				pos = ((Teleporter)target).getLinked().getPos();
			} 
			player.setPos(new Vector(pos.getX() + dir.X, pos.getY() + dir.Y));
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
		switch (moveType) {
			case WALL : return false;
			case DOOR : return false;
			default : return true;
		}
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

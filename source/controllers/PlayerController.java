package controllers;

import entities.*;
import cells.*;
import utils.*;

/**ss
 * Class to handle player movement and rendering
 *s
 * @author Scott, Danny
 */
public class PlayerController {

    public static enum MOVES {
        UP, RIGHT, DOWN, LEFT
    }

    private Player player;

  public Player getPlayer(){
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
	public void move(MOVES dir, MapController mc) {
		Vector current = getPlayerPos();
		Vector desired;
		if (dir == MOVES.UP) {
			desired = new Vector(current.getX(), current.getY() + 1);
		} else if (dir == MOVES.DOWN) {
			desired = new Vector(current.getX(), current.getY() - 1);
		} else if (dir == MOVES.LEFT) {
			desired = new Vector(current.getX() - 1, current.getY());
		} else if (dir == MOVES.RIGHT) {
			desired = new Vector(current.getX() + 1, current.getY());
		}
		Cell desiredCell = mc.getCell(desired.getX(), desired.getY());
		if (validMove(desiredCell)) {
			player.setPos(desired);
      mc.moveMap(dir);
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
		CellType move = targetCell.getType();
		Boolean valid = null;
		if (move == (CellType.GROUND) || move == (CellType.FIRE) || move == (CellType.WATER)) {
			valid = true;
    } else if (move == (CellType.TELEPORTER)) {
			// valid = true;
			// player.setPos(Cell.getLinkedPos());
		} else if (move == (CellType.Door)) {
      if (move.ReplaceThis() == RED){
			   Item redKey = new Item(ItemType.REDKEY);
			      valid = player.useItem(redKey);
      } else if (move.ReplaceThis() == BLUE){
        Item blueKey = new Item(ItemType.BLUEKEY);
  			   valid = player.useItem(blueKey);
      } else if (move.ReplaceThis() == YELLOW){
        Item blueKey = new Item(ItemType.BLUEKEY);
  			valid = player.useItem(blueKey);
      } else if (move.ReplaceThis() == GREEN){
        Item greenKey = new Item(ItemType.GREENKEY);
  			valid = player.useItem(greenKey);
      } else if (move.ReplaceThis() == TOKEN){
        valid = player.useTokens(move.GETTOKENNUMBER());
      }
		return valid;
	}
 }


    /**
     * Check if the cell requires a shoe type and if the player posseses it
     *
     * @param cell the cell being checked against the player, if a certain item
     * is required for that cell type
     * @return a boolean value, if true, the player is on a block which kills
     * the player, otherwise player is still alive
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

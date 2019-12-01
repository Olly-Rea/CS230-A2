package controllers;

import entities.*;
import cells.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import utils.*;

/**
 * Class to handle player movement and rendering
 *
 * @author Scott, Danny
 */
public class PlayerController {

    private Player player;

    /**
     * @param player the player object to be controlled
     * @author Danny
     */
    public PlayerController(Player player) {
        this.player = player;
    }

    /**
     * Takes a direction and determines if the player can move into the desired
     * cell based on their inventory
     *
     * @param dir the direction the player is attempting to move
     * @param mc the map controller to find the Cell in the dir
     * @author Danny
     */
    public boolean move(Direction dir, MapController mc) {
        Cell target = mc.getNextCell(player.getPos(), dir);
        // System.out.print("Cell: " + target.getType());
        if (target.getType() == CellType.DOOR) {
            if (((Door) target).isOpenable(player)) {
                mc.openDoor(target.getPos().getX(), target.getPos().getY());
            }
        }

        if (validMove(target)) {
            Vector pos = player.getPos();
            if (target.getType() == CellType.TELEPORTER) {
                pos = ((Teleporter) target).getLinked().getPos();
                player.setPos(pos);
                return true;
            }
            player.setPos(new Vector(pos.getX() + dir.X, pos.getY() + dir.Y));
            return true;
        }
        return false;
    }

    /**
     * Private method to determine if a move is valid based on player inventory
     *
     * @param cell the cell in the direction the player wants to move
     * @return a boolean value true if the move is valid, i.e not walikng into a
     * wall, false if the move is invalid and not possible
     * @author Danny
     */
    private boolean validMove(Cell targetCell) {
        CellType moveType = targetCell.getType();
        if (moveType == CellType.WALL || moveType == CellType.DOOR) {
            return false;
        }
        return true;
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
    public boolean checkStatus(MapController map) {
        Cell current = map.getCell(getPlayerPos());
        if (current.getType() == CellType.FIRE && (player.hasFireBoots() == false)) {
            return true;
        } else if (current.getType() == CellType.WATER && (player.hasFlippers() == false)) {
            return true;
        }
        return false;
    }

    /**
     * Check if player is on a goal cell
     *
     * @param map
     */
    public boolean checkGoal(MapController map) {
        Cell current = map.getCell(player.getPos());
        if (current.getType() == CellType.GOAL) {
            return true;
        }
        return false;
    }

    /**
     * @return Returns the vector of the players current position
     * @author Danny
     */
    public Vector getPlayerPos() {
        return player.getPos();
    }

    /**
     * Returns the object of the player
     *
     * @return the player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Renders the player
     *
     * @author xxxxx
     */
    public GridPane renderPlayer() {
        // Create the Player GridPane
        GridPane playerGridPane = new GridPane();

        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                if (x == 3 && y == 3) {
                    playerGridPane.add(player.render(), x, y);
                } else {
                    // Create and add a blank pane
                    // - used to create correct spacing in the rendered GridPane
                    Pane blankSpace = new Pane();
                    blankSpace.setMinSize(200, 200);
                    playerGridPane.add(blankSpace, x, y);
                }
            }
        }

        return playerGridPane;
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

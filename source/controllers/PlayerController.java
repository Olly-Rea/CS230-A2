package controllers;

import entities.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

import cells.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import utils.*;

/**
 * Class to handle player movement and rendering
 *
 * @author Scott, Danny, Olly
 */
public class PlayerController {

    private Player player;

    // Create the Player GridPane
    GridPane playerGridPane = new GridPane();

    /**
     * @param player the player object to be controlled
     */
    public PlayerController(Player player) {
        this.player = player;
    }

    /**
     * Takes a direction and determines if the player can move into the desired
     * cell based on their inventory
     *
     * @param dir the direction the player is attempting to move
     * @param mc the map controller to find the Cell in that dir
     */
    public void move(Direction dir, MapController mc) {
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
            }
            player.setPos(new Vector(pos.getX() + dir.X, pos.getY() + dir.Y));
        }
    }

    /**
     * Private method to determine if a move is valid based on player inventory
     *
     * @param cell the cell in the direction the player wants to move
     * @return a boolean value true if the move is valid, i.e not walikng into a
     * wall, false if the move is invalid and not possible
     */
    private boolean validMove(Cell targetCell) {
        CellType moveType = targetCell.getType();
        if (moveType == CellType.WALL || moveType == CellType.DOOR) {
            return false;
        }
        return true;
    }

    /**
     * Method to create the player inventory
     * @param sc 
     */
    public void createInventory(Scanner sc) {
        int[] inventory = new int[7];
        try {
            for (int i = 0; i < 7; i++) {
                inventory[i] = sc.nextInt();
            }
            player.setInventory(inventory);
        } catch (NoSuchElementException e) {
            System.err.println("Inventory declaration is invalid");
        }

    }

    /**
     * Check if the cell requires a shoe type and if the player posseses it
     *
     * @return a boolean value, if true, the player is on a block which kills
     * the player, otherwise player is still alive
     */
    public boolean checkStatus(MapController map) {
        Cell current = map.getCell(getPlayerPos());

        if (current instanceof Fire && !player.hasFireBoots()) {
            return true;
        } else if (current instanceof Water && !player.hasFlippers()) {
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
     */
    public GridPane renderPlayer() {
        playerGridPane.getChildren().clear();

        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                if (x == 3 && y == 3) {
                    playerGridPane.add(player.render(), x, y);
                } else {
                    // Create and add a blank pane
                    // - used to create correct spacing in the rendered GridPane
                    Pane blankSpace = new Pane();
                    blankSpace.setMinSize(200, 150);
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
     */
    public String[] export() {
        String[] export = new String[2];

        String inv = "";
        for (int i : player.getInventory()) {
            inv += i + " ";
        }

        export[0] = String.format("PLAYER %d %d", player.getPos().getX(), player.getPos().getY());
        export[1] = String.format("INVENTORY " + inv);

        return export;
    }
}

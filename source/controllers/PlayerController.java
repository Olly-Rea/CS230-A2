package controllers;

import entities.Player;
import utils.Vector;

/**
 * PlayerController.java
 * 
 * @author xxxxx
 */
public class PlayerController {
        
    public static enum MOVES {
        UP, RIGHT, DOWN, LEFT
    }

    private final Player player;

    /**
     * @param player the player object to be controlled
     * @author xxxxx
     */
    public PlayerController(Player player) {
        this.player = player;
    }

    /**
     * @param dir the direction the player is attempting to move
     * @param mc  the map controller to find the Cell in the dir
     * @author xxxxx
     */
    public void move(MOVES dir, MapController mc) {
        
    }

    /**
     * Method to check the validity of a move made by the player
     * @param cell the cell in the direction the player wants to move
     * 
     * @return a boolean value true if the move is valid, i.e not walikng into a
     *         wall, false if the move is invalid and not possible
     */
    private boolean validMove(Cell cell) {
        
    }

    /**
     * Method to check the alive/dead status of the player
     * 
     * @param cell the cell being checked against the player, e.g. if a certain 
     *             item is required for that cell type
     * 
     * @return boolean value; indicating if the player is dead or alive
     */
    public boolean checkStatus(Cell cell) {
        return true;
    }

    /**
     * Method to return the vector position of the Player
     * 
     * @return Returns the vector of the players current position
     */
    public Vector getPlayerPos() {
        return player.getPos();
    }

    /**
     * Renders the players position
     * 
     * @author xxxxx
     */
    public void render() {
    }

    /**
     * @return an array of strings representing the players position and inventory
     * @author xxxxx
     */
    public String[] export() {
    }
}
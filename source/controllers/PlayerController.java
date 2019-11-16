package controllers;

/**
 * PlayerController.java
 * 
 * @author xxxxx
 */
public class PlayerController {
    public static enum MOVES {
        UP, RIGHT, DOWN, LEFT
    }

    private Player player;

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
     * @param cell the cell in the direction the player wants to move
     * @return a boolean value true if the move is valid, i.e not walikng into a
     *         wall, false if the move is invalid and not possible
     * @author xxxxx
     */
    private boolean validMove(Cell cell) {
    }

    /**
     * @param cell the cell being checked against the player, if a certain item is
     *             required for that cell type
     * @return a boolean value, if true, the player is on a block which kills the
     *         player, otherwise player is still alive
     * @author xxxxx
     */
    public boolean checkStatus(Cell cell) {
    }

    /**
     * @return Returns the vector of the players current position
     * @author xxxxx
     */
    public Vector getPlayerPos() {
    }

    /**
     * @return an array of strings representing the players position and inventory
     * @author xxxxx
     */
    public String[] export() {
    }

    /**
     * Renders the players position
     * 
     * @author xxxxx
     */
    public void render() {
    }
}
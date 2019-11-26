package controllers;

import java.util.ArrayList;
import entities.*;

public class EntityController {
    private Entity[][] entityGrid;
    private ArrayList<Enemy> enemies;

    /**
     * Sets entityGrid and enemies
     * 
     * @param entityGrid 2d Array of Enemy and Item Entity SubClasses.
     * @param enemies
     */
    public EntityController(Entity[][] entityGrid, ArrayList<Enemy> enemies) {

    }

    /**
     * Checks if the player is standing on a item. Add the item if an item collision
     * has occurred.
     * 
     * @param player The Player object for position reference.
     */
    public void checkItem(Player player) {

    }

    /**
     * Checks whether a player has collided with an enemy.
     * 
     * @param player The player object where the position will be checked in the
     *               entityGrid.
     * @return True if the player has collided with an enemy. False otherwise.
     */
    public boolean enemyCollision(Player player) {

    }

    /**
     * Removes item from the entityGrid at position x,y
     * @param x The horizontal position of the item in the grid.
     * @param y The vertical position of the item in the grid.
     */
    private void removeItem(int x, int y) {

    }

    /**
     * Iterates through each enemy and moves them to their next position.
     * @param map the map will be passed through to each enemy to assist their next move calculation.
     */
    public void moveEnemies(MapController map) {

    }

    /**
     * Renders 7x7 grid around x,y.
     * @param x the middle x position.
     * @param y the middle y position.
     */
    public void render(int x, int y) {

    }

    /**
     * Returns an array of Strings of the state of the entities in the game.
     * @return String array, 1 for each entity defining their details.
     */
    public String[] export() {

    }

}
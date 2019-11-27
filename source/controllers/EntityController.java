package controllers;

/**
 * @author Scott Barr, James Hogg
 * @version 1.1
 */
import java.util.ArrayList;
import entities.*;
import utils.*;

public class EntityController {

    private Entity[][] entityGrid;
    private ArrayList<Enemy> enemies;
    private ArrayList<String> items = new ArrayList<String>() {
        {
            add("r");
            add("g");
            add("b");
            add("y");
            add("w");
            add("f");
            add("*");
        }
    };

    /**
     * Sets entityGrid and enemies
     *
     * @param entityGrid 2d Array of Enemy and Item Entity SubClasses.
     * @param enemies
     */
    public EntityController(Entity[][] entityGrid, ArrayList<Enemy> enemies) {

    }

    /**
     * Checks if the player is standing on a item. Add the item if an item
     * collision has occurred.
     *
     * @param player The Player object for position reference.
     */
    public void checkItem(Player player) {
    private Vector playerPos = player.getPos();
    for (int i;

    i< items.size ();
    i

    
        ++) {
        if entityGrid[playerPos.x][playerPos.y] == items.get(i)    {
            player.addItem(items.get(i));
        }
    }

}

/**
 * Checks whether a player has collided with an enemy.
 *
 * @param player The player object where the position will be checked in the
 * entityGrid.
 * @return True if the player has collided with an enemy. False otherwise.
 */
public boolean enemyCollision(Player player) {
      private Vector playerPos = player.getPos();
      for (int i; i < enemies.size(); i++) {
        if entityGrid[playerPos.x][playerPos.y] == (enemies.get(i)).getPos() {
          return true;
        } else {
          return false;
        }
      }
    }

    /**
     * Removes item from the entityGrid at position x,y
     * @param x The horizontal position of the item in the grid.
     * @param y The vertical position of the item in the grid.
     */
    private void removeItem(int x, int y) {
      entityGrid[x][y] == " ";
    }

    /**
     * Iterates through each enemy and moves them to their next position.
     * @param map the map will be passed through to each enemy to assist their next move calculation.
     */
    public moveEnemies(MapController map) {
      private Vector enemyPos;
      private Vector newEnemyPos;
      for (int i; i < enemies.size(); i++) {
        enemyPos = (enemies.get(i)).getPos();
        newEnemyPos = (enemies.get(i)).pathingAlgorithm(map);
        entityGrid[newEnemyPos.x][newEnemyPos.y] == enemy.get(i);
        entityGrid[enemyPos.x][enemyPos.y] == " ";
      }
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
      private String[] exportArray;
      private int numberOfColumns = entityGrid.length();
      private int numberOfRows;
      private int exportArrayIndex = 0;
      private String exportData;
      for (int i; i < numberOfColumns; i++) {
        numberOfRows = entityGrid[i].length();
        for (int j; j < numberOfRows; j++) {
          if entityGrid[i][j] != " " {
            exportData = entityGrid[i][j] + " " + (entityGrid[i][j]).getPos();
            exportArray[exportArrayIndex] = exportData;
            exportArrayIndex++;
          }
        }
      } return exportArray;
    }

}

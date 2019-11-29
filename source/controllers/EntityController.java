package controllers;
/**
* @author Scott Barr, James Hogg
* @version 1.1
*/
import java.util.ArrayList;
import entities.*;

public class EntityController {
    private Entity[][] entityGrid;
    private ArrayList<Enemy> enemies;
    private final ArrayList<String> items = new ArrayList<String>() {
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
     //this constructor needs implementing correctly
    public EntityController(Entity[][] entityGrid, ArrayList<Enemy> enemies) {
      Entity[][] entityGrid = new Entity[][] {{}};
      ArrayList<Enemy> enemies = new Enemy[] {};
      int numberOfColumns = entityGrid.length();
      int numberOfRows;
     for (int i = 0; i < numberOfColumns; i++) {
       numberOfRows = entityGrid[i].length();
       for (int j = 0; j < numberOfRows; j++) {
         for (int k = 0; k < items.size(); k++){
          if (entityGrid[i][j] != items.get(k)) {
           enemies.add(entityGrid[i][j]);
         }
        }
       }
      }
    }

    /**
     * Checks if the player is standing on a item. Add the item if an item collision
     * has occurred.
     *
     * @param player The Player object for position reference.
     */
    public void checkItem(Player player) {
      private Vector playerPos = player.getPos();
      for (int i = 0; i < items.size(); i++) {
        if (entityGrid[playerPos.x][playerPos.y] == items.get(i)) {
          player.addItem(items.get(i));
        }
      }

    }

    /**
     * Checks whether a player has collided with an enemy.
     *
     * @param player The player object where the position will be checked in the
     *               entityGrid.
     * @return True if the player has collided with an enemy. False otherwise.
     */
    public boolean enemyCollision(Player player) {
      Vector playerPos = player.getPos();
      for (int i = 0; i < enemies.size(); i++) {
        if (entityGrid[playerPos.x][playerPos.y] == (enemies.get(i)).getPos()) {
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
      entityGrid[x][y] = new String(" ");
    }

    /**
     * Iterates through each enemy and moves them to their next position.
     * @param map the map will be passed through to each enemy to assist their next move calculation.
     */
    public moveEnemies(MapController map) {
      Vector enemyPos;
      Vector newEnemyPos;
      for (int i = 0; i < enemies.size(); i++) {
        enemyPos = (enemies.get(i)).getPos();
        newEnemyPos = (enemies.get(i)).pathingAlgorithm(map);
        entityGrid[newEnemyPos.x][newEnemyPos.y] = new String(enemy.get(i));
        removeItem(enemyPos.x,enemyPos.y);
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
      String[] exportArray;
      int numberOfColumns = entityGrid.length();
      int numberOfRows;
      int exportArrayIndex = 0;
      String exportData;
      for (int i = 0; i < numberOfColumns; i++) {
        numberOfRows = entityGrid[i].length();
        for (int j = 0; j < numberOfRows; j++) {
          if (entityGrid[i][j] != " ") {
            exportData = entityGrid[i][j] + " " + (entityGrid[i][j]).getPos();
            exportArray[exportArrayIndex] = exportData;
            exportArrayIndex++;
          }
        }
      } return exportArray;
    }

}

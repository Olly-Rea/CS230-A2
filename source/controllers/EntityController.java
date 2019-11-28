package controllers;

/**
 * @author Scott Barr, James Hogg
 * @version 1.1
 */
import java.util.ArrayList;
import java.util.Scanner;

import entities.*;
import entities.enemies.DumbTargeter;
import entities.enemies.SmartTargeter;
import entities.enemies.StraightLineEnemy;
import entities.enemies.WallFollower;
import utils.Direction;
import utils.Rotation;
import utils.Vector;

public class EntityController {

    private Entity[][] entityGrid;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Sets entityGrid and enemies
     *
     * @param entityGrid 2d Array of Enemy and Item Entity SubClasses.
     * @param enemies
     */
    public EntityController(Entity[][] entityGrid) {
        this.entityGrid = entityGrid;
    }

    /**
     * Checks if the player is standing on a item. Add the item if an item collision
     * has occurred.
     *
     * @param player The Player object for position reference.
     */
    public void checkItem(Player player) {
        // Vector playerPos = player.getPos();
        // for (int i; i < items.size(); i++) {
        // if entityGrid[playerPos.x][playerPos.y] == items.get(i) {
        // player.addItem(items.get(i));
        // }
        // }

    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Checks whether a player has collided with an enemy.
     *
     * @param player The player object where the position will be checked in the
     *               entityGrid.
     * @return True if the player has collided with an enemy. False otherwise.
     */
    public boolean enemyCollision(Player player) {
        // private Vector playerPos = player.getPos();
        // for (int i; i < enemies.size(); i++) {
        // if entityGrid[playerPos.x][playerPos.y] == (enemies.get(i)).getPos() {
        // return true;
        // } else {
        // return false;
        // }
        // }
        return false;
    }

    /**
     * Removes item from the entityGrid at position x,y
     *
     * @param x The horizontal position of the item in the grid.
     * @param y The vertical position of the item in the grid.
     */
    private void removeItem(int x, int y) {
        entityGrid[y][x] = null;
    }

    /**
     * Iterates through each enemy and moves them to their next position.
     *
     * @param map the map will be passed through to each enemy to assist their next
     *            move calculation.
     */
    public void moveEnemies(MapController map) {
        // private Vector enemyPos;
        // private Vector newEnemyPos;
        // for (int i; i < enemies.size(); i++) {
        // enemyPos = (enemies.get(i)).getPos();
        // newEnemyPos = (enemies.get(i)).pathingAlgorithm(map);
        // entityGrid[newEnemyPos.x][newEnemyPos.y] == enemy.get(i);
        // entityGrid[enemyPos.x][enemyPos.y] == " ";
        // }
    }

    /**
     * Renders 7x7 grid around x,y.
     *
     * @param x the middle x position.
     * @param y the middle y position.
     */
    public void render(int x, int y) {

    }

    /**
     * Returns an array of Strings of the state of the entities in the game.
     *
     * @return String array, 1 for each entity defining their details.
     */
    public String[] export() {
        // private String[] exportArray;
        // private int numberOfColumns = entityGrid.length();
        // private int numberOfRows;
        // private int exportArrayIndex = 0;
        // private String exportData;
        // for (int i; i < numberOfColumns; i++) {
        // numberOfRows = entityGrid[i].length();
        // for (int j; j < numberOfRows; j++) {
        // if entityGrid[i][j] != " " {
        // exportData = entityGrid[i][j] + " " + (entityGrid[i][j]).getPos();
        // exportArray[exportArrayIndex] = exportData;
        // exportArrayIndex++;
        // }
        // }
        // } return exportArray;s
        return null;
    }

    /**
     * Static method to create a new Player object
     * 
     * @param line the scanner of a line with the player information
     * @return the Player object
     */
    public static Player makePlayer(Scanner line) {
        int x = line.nextInt();
        int y = line.nextInt();
        return new Player(x, y);
    }

    /**
     * Creates a new straight line enemy from a scanner and position
     * 
     * @param line the scanner of the rest of the line including specific details
     * @param pos  the position the enemy is created at
     * @return the instance of the StraightLineEnemy
     */
    private static StraightLineEnemy makeSL(Scanner line, Vector pos) {
        String faceDir = line.next();
        switch (faceDir) {
        case "UP":
            return new StraightLineEnemy(pos, Direction.UP);
        case "RIGHT":
            return new StraightLineEnemy(pos, Direction.RIGHT);
        case "DOWN":
            return new StraightLineEnemy(pos, Direction.DOWN);
        case "LEFT":
            return new StraightLineEnemy(pos, Direction.LEFT);
        default:
            return new StraightLineEnemy(pos, Direction.UP);
        }
    }

    /**
     * Creates a new straight line enemy from a scanner and position
     * 
     * @param line the scanner of the rest of the line including specific details
     * @param pos  the position the enemy is created at
     * @return the instance of the StraightLineEnemy
     */
    private static WallFollower makeWF(Scanner line, Vector pos) {
        Direction dir;
        Rotation rot;

        String faceDir = line.next();
        String rotation = line.next();

        switch (faceDir) {
        case "UP":
            dir = Direction.UP;
        case "RIGHT":
            dir = Direction.RIGHT;
        case "DOWN":
            dir = Direction.DOWN;
        case "LEFT":
            dir = Direction.LEFT;
        default:
            dir = Direction.UP;
        }

        switch (rotation) {
        case "CW":
            rot = Rotation.CW;
        case "ACW":
            rot = Rotation.ACW;
        default:
            rot = Rotation.ACW;
        }

        return new WallFollower(pos, dir, rot);
    }

    /**
     * 
     * @param line a scanner of the line with the enemy details
     * @return
     */
    public static Enemy makeEnemy(Scanner line, Player p) {
        int x = line.nextInt();
        int y = line.nextInt();
        String type = line.next();
        switch (type) {
        case "SL":
            return makeSL(line, new Vector(x, y));
        case "WF":
            return makeWF(line, new Vector(x, y));
        case "DT":
            return new DumbTargeter(new Vector(x, y), p);
        case "ST":
            return new SmartTargeter(new Vector(x, y), p);
        default:
            return null;
        }
    }

    /**
     * Returns a new item based on the char in the map text file
     * 
     * @param x the x position of the item
     * @param y the y positio of the item
     * @param c the character in the map file
     * @return an instance of an item depending on the character given
     */
    public static Entity makeEntity(int x, int y, char c) {
        switch (c) {
        case 'r':
            return new Item(ItemType.REDKEY, x, y);
        case 'g':
            return new Item(ItemType.GREENKEY, x, y);
        case 'b':
            return new Item(ItemType.BLUEKEY, x, y);
        case 'y':
            return new Item(ItemType.YELLOWKEY, x, y);
        case 'f':
            return new Item(ItemType.FIREBOOTS, x, y);
        case 'w':
            return new Item(ItemType.FLIPPERS, x, y);
        case '*':
            return new Item(ItemType.TOKEN, x, y);
        default:
            return null;
        }
    }

}

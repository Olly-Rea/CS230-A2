package controllers;

/**
 * @author Scott Barr, James Hogg
 * @version 1.1
 */
import java.util.ArrayList;
import java.util.Scanner;

import entities.*;
import entities.enemies.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import utils.Direction;
import utils.Rotation;
import utils.Vector;

public class EntityController {

    private Entity[][] entityGrid;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    GridPane entityGridPane = new GridPane();
    
    /**
     * Sets entityGrid and enemies
     *
     * @param entityGrid 2d Array of Enemy and Item Entity SubClasses.
     */
    public EntityController(Entity[][] entityGrid) {
        this.entityGrid = entityGrid;
    }

    public Entity[][] getGrid() {
        return entityGrid;
    }

    /**
     * Checks if the player is standing on a item. Add the item if an item
     * collision has occurred.
     *
     * @param player The Player object for position reference.
     */
    public void checkItem(Player player) {
        Vector playerPos = player.getPos();
        // System.out.println(", Item: " +
        // entityGrid[playerPos.getY()][playerPos.getX()]);
        if (entityGrid[playerPos.getY()][playerPos.getX()] != null) {
            Entity NewEntity = entityGrid[playerPos.getY()][playerPos.getX()];
            if (NewEntity instanceof Item) {
                Entity newItem = entityGrid[playerPos.getY()][playerPos.getX()];
                System.out.println("Found: " + ((Item) newItem).getType());
                player.addItem((Item) newItem);
                removeItem(playerPos.getY(), playerPos.getX());
            }
        }
    }

    public boolean entityPresent(Vector pos, Direction dir) {
        return entityGrid[pos.getY() + dir.Y][pos.getX() + dir.X] instanceof Entity;
    }

    /**
     * Adds an enemy to the entity and also to the enemies list
     *
     * @param enemy the enemy being added
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        Vector ePos = enemy.getPos();
        entityGrid[ePos.getY()][ePos.getX()] = enemy;
    }

    /**
     * Adds an item to the entity grid and also to the items list
     *
     * @param i the item being added
     */
    public void addItem(Item i) {
        if (i != null) {
            Vector pos = i.getPos();
            items.add(i);
            entityGrid[pos.getY()][pos.getX()] = i;
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
        for (int i = 0; i < enemies.size(); i++) {
            if ((player.getPos().getX() == enemies.get(i).getPos().getX())
                    && (player.getPos().getY() == enemies.get(i).getPos().getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes item from the entityGrid at position x,y
     *
     * @param x The horizontal position of the item in the grid.
     * @param y The vertical position of the item in the grid.
     */
    private void removeItem(int y, int x) {
        entityGrid[y][x] = null;
        renderEntities();
    }

    /**
     * Iterates through each enemy and moves them to their next position.
     *
     * @param map the map will be passed through to each enemy to assist their
     * next move calculation.
     */
    public void moveEnemies(MapController map) {
        for (int i = 0; i < enemies.size(); i++) {
            // Get the enemy to move
            Enemy moveEnemy = enemies.get(i);
            // Get this enemies original position
            int x;
            int y;
            x = moveEnemy.getPos().getX();
            y = moveEnemy.getPos().getY();
            entityGrid[y][x] = null;
            moveEnemy.algorithm(map, this);
            x = moveEnemy.getPos().getX();
            y = moveEnemy.getPos().getY();
            entityGrid[y][x] = moveEnemy;
            renderEntities();
        }
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
        return new Player(new Vector(x, y));
    }

    /**
     * Creates a new straight line enemy from a scanner and position
     *
     * @param line the scanner of the rest of the line including specific
     * details
     * @param pos the position the enemy is created at
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
     * @param line the scanner of the rest of the line including specific
     * details
     * @param pos the position the enemy is created at
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
    public static Item makeItem(int x, int y, char c) {
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

    /**
     * Renders the entities respective assets on a GridPane at their locations
     * based on the entityGrid
     *
     * @return
     */
    public GridPane renderEntities() {
        // Clear the entity GridPane for fresh render
        entityGridPane.getChildren().clear();
        for (int y = 0; y < entityGrid.length; y++) {
            for (int x = 0; x < entityGrid[y].length; x++) {
                if (entityGrid[y][x] != null) {
                    entityGridPane.add(entityGrid[y][x].render(), x, y);
                } else {
                    // Create and add a blank pane
                    // - used to create correct spacing in the rendered GridPane
                    Pane blankSpace = new Pane();
                    blankSpace.setMinSize(200, 200);
                    entityGridPane.add(blankSpace, x, y);
                }
            }
        }

        return entityGridPane;
    }

}

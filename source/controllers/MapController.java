package controllers;

//Local imports
import cells.*;
import entities.Entity;
import entities.Item;
import utils.*;

//Java imports
import java.util.ArrayList;
import java.util.Scanner;

//JavaFX imports
import javafx.scene.layout.GridPane;

/**
 * MapController.java
 *
 * @version 1.0.0
 * @author Olly Rea, Scott Barr
 */

/**
 * MapController is a class to manage the map and all methods related to it. It
 * consists of a 2D array of cells and contains the methods required to alter
 * and manipulate them
 */
public class MapController {

    // 2D array of cells that make up the game map
    private final Cell[][] map;

    private GridPane mapGrid = new GridPane();
    AssetBuilder assetUtil;

    public final int width;
    public final int height;

    /**
     * MapController constructor; Instantiates a new MapController
     *
     * @param cellArray The 2D array of cells that make up the MapController
     * @param width The width of the 2d array
     * @param height the height of the 2d array
     */
    public MapController(Cell[][] cellArray, int width, int height) {
        map = cellArray;
        this.width = width;
        this.height = height;
        assetUtil = new AssetBuilder(map);
    }

    /**
     * Returns cell at location in cellArray
     *
     * @param x The x value of desired cell
     * @param y The y value of the desired cell
     * @return the Cell at map[y][x]
     */
    public Cell getCell(int x, int y) {
        return map[y][x];
    }

    /**
     * Returns cell at location of a vector
     *
     * @param pos
     * @return
     */
    public Cell getCell(Vector pos) {
        return map[pos.getY()][pos.getX()];
    }

    /**
     * Returns the next cell in a certain direction from the cell at a certain
     * position.
     *
     * @param pos position vector of the cell
     * @param dir direction of the next cell
     * @return
     */
    public Cell getNextCell(Vector pos, Direction dir) {
        return map[pos.getY() + dir.Y][pos.getX() + dir.X];
    }

    /**
     * Changes a door at a given location to a empty cell
     *
     * @param x The x value of desired door
     * @param y The y value of the desired door
     */
    public void openDoor(int x, int y) {
        map[y][x] = new Ground(x, y);
        Ground deadBoulder = (Ground) map[y][x];
        deadBoulder.addDebris();
        mapGrid.add(map[y][x].render(), x, y);
        renderMap();
    }

    /**
     * Exports the current map state as a string array to allow for game saving
     *
     * @return String[]
     */
    public String[] exportMap(EntityController ec) {

        // Create the export String ArrayList
        ArrayList<String> mapExport = new ArrayList<>();
        mapExport.add(width + " " + height);

        for (int y = 0; y < height; y++) {
            String row = "";
            for (int x = 0; x < width; x++) {
                Cell cell = map[y][x];
                if (cell instanceof Ground) {
                    Entity e = ec.getEntity(x, y);
                    if (e instanceof Item) {
                        row += ((Item) e).getChar();
                    } else {
                        row += cell.getChar();
                    }
                } else {
                    row += cell.getChar();
                }
            }
            mapExport.add(row);
        }

        // return the String array of the mapExport ArrayList
        return mapExport.toArray(new String[mapExport.size()]);
    }

    public String[] exportSpecific() {
        ArrayList<String> mapSpecifics = new ArrayList<>();
        ArrayList<Teleporter> teleporters = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = map[y][x];
                if (cell instanceof Teleporter) {
                    Teleporter te1 = (Teleporter) cell;
                    if (!teleporters.contains(te1.getLinked())) {
                        Teleporter te2 = te1.getLinked();
                        Vector t1 = te1.getPos();
                        Vector t2 = te2.getPos();
                        mapSpecifics.add(String.format("TELEPORTER %d %d %d %d", t1.getX(), t1.getY(), t2.getX(), t2.getY()));
                        teleporters.add(te1);
                    }
                } else if (cell instanceof TokenDoor) {
                    TokenDoor door = (TokenDoor) cell;
                    mapSpecifics.add(String.format("DOOR %d %d %d", x, y, door.getTokens()));
                }
            }
        }

        // return the String array of the mapSpecifics ArrayList
        return mapSpecifics.toArray(new String[mapSpecifics.size()]);
    }

    /**
     * Goes through every cell and sets their image if needed
     */
    public void autotile() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                Cell cell = map[y][x];
                if (cell instanceof Wall) {
                    ((Wall) cell).setImage(assetUtil.getWallType(x, y));
                }
                if (cell instanceof Water) {
                    ((Water) cell).setImage(assetUtil.getWaterType(x, y));
                }
            }
        }
    }

    /**
     * Method to render the map to the screen centred on the player's location
     *
     * @return
     */
    public GridPane renderMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x].getType() == null) {
                    System.err.println("Mapfile error at: (" + x + ", " + y + ")");
                }
                mapGrid.add(map[y][x].render(), x, y);
            }
        }
        return mapGrid;
    }

    /**
     * Method to link two Teleporters together
     * 
     * @param sc 
     */
    public void linkTeleporters(Scanner sc) {
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        Teleporter t1 = (Teleporter) map[y1][x1];
        Teleporter t2 = (Teleporter) map[y2][x2];
        Teleporter.link(t1, t2);
    }

    /**
     * Method to set the door token requirement
     *
     * @param sc
     */
    public void initDoor(Scanner sc) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        int tokens = sc.nextInt();
        TokenDoor door = (TokenDoor) map[y][x];
        door.setTokens(tokens);
    }

    /**
     * Method to create Cells based on the input character from a read map file
     * 
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @param c character in the text file to be translated into a Cell object
     * @return
     */
    public static Cell makeCell(int x, int y, char c) {
        switch (c) {
            case '#':
                return new Wall(x, y);
            case ' ':
                return new Ground(x, y);
            case 'T':
                return new Teleporter(x, y);
            case 'W':
                return new Water(x, y);
            case 'F':
                return new Fire(x, y);
            case '!':
                return new Goal(x, y);
            case 'R':
                return new ColouredDoor(x, y, DoorColour.RED);
            case 'G':
                return new ColouredDoor(x, y, DoorColour.GREEN);
            case 'B':
                return new ColouredDoor(x, y, DoorColour.BLUE);
            case 'Y':
                return new ColouredDoor(x, y, DoorColour.YELLOW);
            case 'D':
                return new TokenDoor(x, y);
            default:
                return new Ground(x, y);
        }
    }
}

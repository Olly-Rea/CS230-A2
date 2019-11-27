package controllers;

import cells.*;
import utils.*;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;

/**
 * MapController.java
 *
 * @version 1.0.0
 * @author Olly Rea, Daniel Clenaghan
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
     * @param width     The width of the 2d array
     * @param height    the height of the 2d array
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
     * @return the Cell at map[x][y]
     */
    public Cell getCell(int x, int y) {
        return map[x][y];
    }

    /**
     * Retruns cell at location of a vector
     * 
     * @param pos
     * @return
     */
    public Cell getCell(Vector pos) {
        return map[pos.getY()][pos.getX()];
    }

    /**
     * Returns the next cell in a certain direction from the cell at a certain position.
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
        map[y][x] = new Cell(CellType.GROUND, x, y);
    }

    /**
     * Exports the current map state as a string array to allow for game saving
     *
     * @return String[]
     */
    public String[] export() {

        // Create the export String ArrayList
        ArrayList<String> mapExport = new ArrayList<>();

        // Loop through the 'map' Cell array, converting each cell to it's
        // string counterpart
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                // Switch case to add respective characters to the output string
                // depending on the cellType
                if (map[x][y].getType() == null) {
                    mapExport.add(" ");
                } else {
                    switch (map[x][y].getType()) {
                    case WALL:
                        mapExport.add("#");
                        break;
                    case GROUND:
                        mapExport.add(" ");
                        break;
                    case FIRE:
                        mapExport.add("F");
                        break;
                    case WATER:
                        mapExport.add("W");
                        break;
                    case TELEPORTER:
                        mapExport.add("T");
                        break;
                    case DOOR:
                        mapExport.add("D");
                        break;
                    case GOAL:
                        mapExport.add("!");
                        break;
                    default:
                        mapExport.add(" ");
                        break;
                    }
                }
            }
            // Add a delimiter for the filehandler to create a newline at
            mapExport.add("|");
        }

        // Create line as one string, each element is a line

        // ADD IN LINES FOR EACH TELEPORTER, DOOR, ETC

        // return the String array of the mapExport ArrayList
        return mapExport.toArray(new String[mapExport.size()]);
    }

    /**
     * Method to render the map to the screen centred on the player's location
     *
     * @param playerLocation The player controller is used to access the player's
     *                       current location
     *
     * @return
     */
    public GridPane renderMap(PlayerController playerLocation) {

        // Vector PlayerPos = playerLocation.getPlayerPos();

        // Loop through the map
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[x].length; x++) {
                // Check that there is a cell at this section of the map array
                if (map[y][x].getType() == null) {
                    System.out.println("Mapfile error at: (" + x + ", " + y + ")");
                }
                // If no error occurs; check cell type and add to the javaFX
                // gridPane accordingly
                if (map[y][x].getType() == CellType.WALL) {
                    // Get the wall asset Image from the AssetBuilder class
                    String newAssetPath = assetUtil.getWallType(x, y);
                    // Add the cell image to the GridPane
                    mapGrid.add(map[y][x].render(newAssetPath), x, y, 1, 1);
                } else {
                    // Add the cell image to the GridPane
                    mapGrid.add(map[y][x].render(), x, y, 1, 1);
                }

            }
        }

        return mapGrid;

        // Have an x and y value for the GridPane on which the map is rendered and
        // move the map instead of the player to maintain centred focus

    }

    public void moveMap(Direction dir) {

    }

    /**
     * getMapHeight is a method to return the height of the 2D map array
     *
     * @return
     */
    public final int getMapHeight() {
        // Return the height of the map array
        return map.length;
    }

    /**
     * getMapWidth is a method to return the max width of the 2D map array
     *
     * @return
     */
    public final int getMapWidth() {

        int maxLength = 0;

        for (int y = 0; y < map.length; y++) {
            int xLength = 0;
            for (int x = 0; x < map[x].length; x++) {
                xLength++;
            }
            if (maxLength < xLength) {
                maxLength = xLength;
            }
        }

        return maxLength;
    }

    public static Cell makeCell(int x, int y, char c) {
        switch (c) {
            case '#' : return new Cell(CellType.WALL, x, y);
            case ' ' : return new Cell(CellType.GROUND, x, y);
            case 'T' : return new Teleporter(x, y);
            case 'W' : return new Cell(CellType.WATER, x, y);
            case 'F' : return new Cell(CellType.FIRE, x, y);
            case 'G' : return new Cell(CellType.GOAL, x, y);
            // case 'R' : return new Door(DoorType.RED, x, y); // TODO: Door implementation change
            // case 'G' : return new Door(DoorType.GREEN, x, y); // TODO: Door implementation change
            // case 'B' : return new Door(DoorType.BLUE, x, y); // TODO: Door implementation change
            // case 'Y' : return new Door(DoorType.YELLOW, x, y); // TODO: Door implementation change
            // case 'D' : return new Door(DoorType.TOKEN, x, y); // TODO: Door implementation change
            default : return new Cell(CellType.GROUND, x, y);
        }
    }

}

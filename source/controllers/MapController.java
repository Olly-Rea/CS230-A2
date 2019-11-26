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
    private GridPane mapImg = new GridPane();

    /**
     * MapController constructor; Instantiates a new MapController
     *
     * @param cellArray The 2D array of cells that make up the MapController
     */
    public MapController(Cell[][] cellArray) {
        map = cellArray;
        AssetBuilder assetUtil = new AssetBuilder(map);
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
     * Changes a door at a given location to a empty cell
     *
     * @param x The x value of desired door
     * @param y The y value of the desired door
     */
    public void openDoor(int x, int y) {
        map[x][y] = new Cell(CellType.GROUND);
    }

    /**
     * Exports the current map state as a string array to allow for game saving
     *
     * @return String[]
     */
    public String[] export() {

        //Create the export String ArrayList
        ArrayList<String> mapExport = new ArrayList<>();

        //Loop through the 'map' Cell array, converting each cell to it's
        //string counterpart
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                //Switch case to add respective characters to the output string
                //depending on the cellType
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
            //Add a delimiter for the filehandler to create a newline at
            mapExport.add("|");
        }
        //return the String array of the mapExport ArrayList
        return mapExport.toArray(new String[mapExport.size()]);
    }

    /**
     * Method to render the map to the screen centred on the player's location
     *
     * @param playerLocation The player controller is used to access the
     * player's current location
     *
     * @return
     */
    public GridPane renderMap(PlayerController playerLocation) {

        //Vector PlayerPos = playerLocation.getPlayerPos();

        //Loop through the map
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[x].length; x++) {
                //Check that there is a cell at this section of the map array
                if (map[y][x].getType() == null) {
                    System.out.println("Mapfile error at: (" + x + ", " + y + ")");
                //If no error occurs; check cell type and add to the javaFX
                //gridPane accordingly
                } else {
                    switch (map[y][x].getType()) {
                        case WALL:
                            mapImg.add(Cell.render(AssetBuilder.getWallType(x, y)), x, y, 1, 1);
                            break;
                        case GROUND:
                            mapImg.add(Cell.render(), x, y, 1, 1);
                            break;
                        case FIRE:
                            mapImg.add(Cell.render(), x, y, 1, 1);
                            break;
                        case WATER:
                            mapImg.add(Cell.render(), x, y, 1, 1);
                            break;
                        case TELEPORTER:
                            mapImg.add(Cell.render(), x, y, 1, 1);
                            break;
                        case DOOR:
                            mapImg.add(Cell.render(), x, y, 1, 1);
                            break;
                        case GOAL:
                            mapImg.add(Cell.render(), x, y, 1, 1);
                            break;
                        default:
                            mapImg.add(Cell.render(), x, y, 1, 1);
                            break;
                    }
                }
            }
        }

        return mapImg;

        /*

        Change to render whole map and then obscure hidden-from-view parts
        Saves having to re-render each map (and wall) each move

        using JavaFX 'TilePane' Canvas canvas = new Canvas(800, 800);

        Have an x and y value for the canvas on which the map is rendered and
        move the map instead of the player to maintain centred focus

         */
        //assetUtil.getWallType(x,y);
    }

    public void moveMap(Direction dir) {

    }

    /**
     * getMapHeight is a method to return the height of the 2D map array
     *
     * @return
     */
    public final int getMapHeight() {
        //Return the height of the map array
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

}

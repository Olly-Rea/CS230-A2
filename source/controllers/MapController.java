package controllers;

//Local imports
import cells.*;
import utils.*;
//Java imports
import java.util.ArrayList;
//JavaFX imports
import javafx.scene.layout.GridPane;

/**
 * MapController.java
 *
 * @version 1.0.0
 * @author Olly Rea
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

    /**
     * MapController constructor; Instantiates a new MapController
     *
     * @param cellArray The 2D array of cells that make up the MapController
     */
    public MapController(Cell[][] cellArray) {
        map = cellArray;
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
     * Changes a door at a given location to a empty cell
     *
     * @param x The x value of desired door
     * @param y The y value of the desired door
     */
    public void openDoor(int x, int y) {
        map[y][x] = new Cell(CellType.GROUND, x, y);
        mapGrid.add(map[y][x].render(), x, y);
    }

    /**
     * Exports the current map state as a string array to allow for game saving
     *
     * @return String[]
     */
    public String[] export() {

        //Create the export String ArrayList
        ArrayList<String> mapExport = new ArrayList<>();
        ArrayList<String> mapSpecifics = new ArrayList<>();

        //Loop through the 'map' Cell array, converting each cell to it's
        //string counterpart
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[x].length; x++) {
                String mapLine = "";
                //Switch-case to add respective characters to the mapLine string
                //depending on the CellType at map[y][x]
                if (map[y][x].getType() == null) {
                    mapLine += " ";
                } else {
                    switch (map[y][x].getType()) {
                        case WALL:
                            mapLine += "#";
                            break;
                        case GROUND:
                            mapLine += " ";
                            break;
                        case FIRE:
                            mapLine += "F";
                            break;
                        case WATER:
                            mapLine += "W";
                            break;
                        case TELEPORTER:
                            mapLine += "T";
                            //Find out the teleporter link
                            mapSpecifics.add("TELEPORTEER " + "" + "" + "" + "");
                            break;
                        case DOOR:
                            //Find out the door type
                            mapLine += "D";
                            //Add the door type and specifics
                            mapSpecifics.add("DOOR " + "" + "" + "");
                            break;
                        case GOAL:
                            mapLine += "!";
                            break;
                        default:
                            mapLine += " ";
                            break;
                    }
                }
                mapExport.add(mapLine);
            }
        }

        //Split the map from the 'map specifics'
        mapExport.add("");

        //Add the 'map specifics' lines to the mapExport
        for (int i = 0; i < mapSpecifics.size(); i++) {
            mapExport.add(mapSpecifics.get(i));
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
                }
                //If no error occurs; check cell type and add to the javaFX
                //gridPane accordingly
                if (map[y][x].getType() == CellType.WALL) {
                    //Get the wall asset Image from the AssetBuilder class
                    String newAssetPath = assetUtil.getWallType(x, y);
                    //Add the cell image to the GridPane
                    mapGrid.add(map[y][x].render(newAssetPath), x, y);
                } else {
                    //Add the cell image to the GridPane
                    mapGrid.add(map[y][x].render(), x, y);
                }
            }
        }

        return mapGrid;

        //Have an x and y value for the GridPane on which the map is rendered and
        //move the map instead of the player to maintain centred focus

    }

    public void moveMap(Direction dir) {

        int x = 0;
        int y = 0;

        switch (dir) {
            case UP:
                x = 0;
                y = 0;
            case DOWN:
                x = 0;
                y = 0;
            case LEFT:
                x = 0;
                y = 0;
            case RIGHT:
                x = 0;
                y = 0;
        }


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

    /*

    There will be two layers;
    - the Player 'layer', and
    - the world layer,

    Entities move within the world layer, and then the world layer is moved
    around the player 'layer', while the player 'layer' stays still (central to
    the screen).

    */

}

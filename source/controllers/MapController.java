package controllers;

import cells.*;

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
    private Cell[][] map;
    private Entity[][] entityMap;

    /**
     * Creates a MapController
     *
     * @param cellArray The 2D array of cells that make up the MapController
     */
    public MapController(Cell[][] cellArray) {
        map = cellArray;
    }

    /**
     * Returns cell at location in cellArray
     *
     * @param x The x value of desired cell
     * @param y The y value of the desired cell
     * @return
     */
    public Cell getCell(int x, int y) {
        return null;
    }

    /**
     * Changes a door at a given location to a empty cell
     *
     * @param x The x value of desired door
     * @param y The y value of the desired door
     */
    public void openDoor(int x, int y) {
        entityMap[x][y] = null;
        render
    }

    /**
     * Exports the current map state as a string array to allow for game saving
     *
     * @return String[]
     */
    public String[] export() {
        return null;
    }

    /**
     * Method to render the map to the screen centred on the player's location
     *
     * @param playerLocation The player controller is used to access the
     * player's current location
     */
    public void render(/*PlayerController playerLocation*/) {

        /**
         *
         * Change to render whole map and then obscure hidden-from-view parts
         * Saves having to re-render each map (and wall) each move
         *
         * Have an x and y value for the canvas on which the map is rendered and
         * move the map instead of the player to maintain centred focus
         *
         */

    }
    public void reRenderEntities() {

    }

    /**
     * Method to check which wall graphic should be assigned to a wall cell -
     * Checks the surrounding cells - 9x9 grid - to determine the graphic
     *
     * @param x The x value of the wall cell
     * @param y The y value of the wall cell
     */
    public String wallCheck(int x, int y) {

        //Check the top row of cells
        //boolean tLCell = map[x - 1][y + 1].getType() == CellType.WALL;
        boolean tCell = map[x][y + 1].getType() == CellType.WALL;
        //boolean tRCell = map[x + 1][y + 1].getType() == CellType.WALL;
        
        //Check the left/right cells
        boolean lCell = map[x - 1][y].getType() == CellType.WALL;
        boolean rCell = map[x + 1][y].getType() == CellType.WALL;

        //Check the bottom row of cells
        //boolean bLCell = map[x - 1][y - 1].getType() == CellType.WALL;
        boolean bCell = map[x][y - 1].getType() == CellType.WALL;
        //boolean bRCell = map[x + 1][y - 1].getType() == CellType.WALL;

        String basePath = "../../assets/cells/";
        String assetPath;

        //base case
        assetPath = basePath + "wall_o";

        //Check for wall_e edge
        if (bCell) {
            assetPath = basePath + "wall_e1";
        }
        if (tCell) {
            assetPath = basePath + "wall_e2";
        }
        if (lCell) {
            assetPath = basePath + "wall_e3";
        }
        if (rCell) {
            assetPath = basePath + "wall_e4";
        }

        //Check for wall_v / wall_h join
        if (bCell && tCell) {
            assetPath = basePath + "wall_v";
        }
        if (rCell && lCell) {
            assetPath = basePath + "wall_h";
        }

        //Check for wall_c corner
        if (tCell && lCell) {
            assetPath = basePath + "wall_c1";
        }
        if (lCell && bCell) {
            assetPath = basePath + "wall_c2";
        }
        if (bCell && rCell) {
            assetPath = basePath + "wall_c3";
        }
        if (rCell && tCell) {
            assetPath = basePath + "wall_c4";
        }

        //Check for wall_t junc
        if (lCell && bCell && rCell) {
            assetPath = basePath + "wall_t1";
        }
        if (tCell && bCell && rCell) {
            assetPath = basePath + "wall_t2";
        }
        if (tCell && lCell && rCell) {
            assetPath = basePath + "wall_t3";
        }
        if (tCell && lCell && bCell) {
            assetPath = basePath + "wall_t4";
        }

        //Check for wall_x junc
        if (tCell && lCell && bCell && rCell) {
            assetPath = basePath + "wall_x";
        }

        //I DON'T LIKE THIS - trying to find a better way

//        //Check for left and right wall cells
//        if (lCell || rCell) {
//            if (lCell && rCell) {
//                assetPath = basePath + "wall_h";
//                //Check for top and bottom wall cells
//                if (tCell || bCell) {
//                    if (tCell && bCell) {
//                        assetPath = basePath + "wall_x";
//                        //Check for topL and topR wall cells
//                        if (tLCell && tRCell) {
//                            assetPath = basePath + "wall_T";
//                            //Check for bottomL and bottomR wall cells
//                            if (bLCell && bRCell) {
//                                assetPath = basePath + "wall_X";
//                            }
//                        } else {
//
//                        }
//                    } else if (tCell) {
//
//                    } else {
//
//                    }
//                } else if (lCell) {
//                    assetPath = basePath + "wall_endL";
//                } else {
//
//                }
//            //Check for top but not bottom cell
//            } else if (tCell && !bCell) {
//
//            } else {
//
//            }
//
//        //Check for top and bottom wall cells
//        } else if (tCell && bCell) {
//            assetPath = basePath + "wall_v";
//
//        } else {
//            assetPath = basePath + "wall_o";
//        }
        return assetPath + ".jpg";
    }

}

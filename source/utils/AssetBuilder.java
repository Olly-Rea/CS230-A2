package utils;

//Local imports
import cells.*;
//Java imports
import java.util.HashMap;

/**
 * AssetBuilder.java
 *
 * AssetBuilder class to allow for connected textures between graphical elements
 *
 * @version 1.0.0
 * @author Olly Rea
 */
public class AssetBuilder {

    //Create hashMaps containing the wall and water graphics filenames, indexed
    //according to the 8-bit tile bitmasking algorithm
    final HashMap<Integer, String> WALL_MAP = new HashMap<>();
    final HashMap<Integer, String> WATER_MAP = new HashMap<>();
    Cell[][] map;

    public AssetBuilder(Cell[][] mapRef) {
        //Pass the AssetBuilder class a reference of the cell map
        map = mapRef;

        //Add ALL the indexes and respective filenames to WALL_MAP
        WALL_MAP.put(0, "wall_1");    WALL_MAP.put(2, "wall_2");    WALL_MAP.put(8, "wall_3");
        WALL_MAP.put(10, "wall_4");   WALL_MAP.put(11, "wall_5");   WALL_MAP.put(16, "wall_6");
        WALL_MAP.put(18, "wall_7");   WALL_MAP.put(22, "wall_8");   WALL_MAP.put(24, "wall_9");
        WALL_MAP.put(26, "wall_10");  WALL_MAP.put(27, "wall_11");  WALL_MAP.put(30, "wall_12");
        WALL_MAP.put(31, "wall_13");  WALL_MAP.put(64, "wall_14");  WALL_MAP.put(66, "wall_15");
        WALL_MAP.put(72, "wall_16");  WALL_MAP.put(74, "wall_17");  WALL_MAP.put(75, "wall_18");
        WALL_MAP.put(80, "wall_19");  WALL_MAP.put(82, "wall_20");  WALL_MAP.put(86, "wall_21");
        WALL_MAP.put(88, "wall_22");  WALL_MAP.put(90, "wall_23");  WALL_MAP.put(91, "wall_24");
        WALL_MAP.put(94, "wall_25");  WALL_MAP.put(95, "wall_26");  WALL_MAP.put(104, "wall_27");
        WALL_MAP.put(106, "wall_28"); WALL_MAP.put(107, "wall_29"); WALL_MAP.put(120, "wall_30");
        WALL_MAP.put(122, "wall_31"); WALL_MAP.put(123, "wall_32"); WALL_MAP.put(126, "wall_33");
        WALL_MAP.put(127, "wall_34"); WALL_MAP.put(208, "wall_35"); WALL_MAP.put(210, "wall_36");
        WALL_MAP.put(214, "wall_37"); WALL_MAP.put(216, "wall_38"); WALL_MAP.put(218, "wall_39");
        WALL_MAP.put(219, "wall_40"); WALL_MAP.put(222, "wall_41"); WALL_MAP.put(223, "wall_42");
        WALL_MAP.put(248, "wall_43"); WALL_MAP.put(250, "wall_44"); WALL_MAP.put(251, "wall_45");
        WALL_MAP.put(254, "wall_46"); WALL_MAP.put(255, "wall_47");

        //Add ALL the indexes and respective filenames to WATER_MAP
        WATER_MAP.put(0, "water_1");    WATER_MAP.put(2, "water_2");    WATER_MAP.put(8, "water_3");
        WATER_MAP.put(10, "water_4");   WATER_MAP.put(11, "water_5");   WATER_MAP.put(16, "water_6");
        WATER_MAP.put(18, "water_7");   WATER_MAP.put(22, "water_8");   WATER_MAP.put(24, "water_9");
        WATER_MAP.put(26, "water_10");  WATER_MAP.put(27, "water_11");  WATER_MAP.put(30, "water_12");
        WATER_MAP.put(31, "water_13");  WATER_MAP.put(64, "water_14");  WATER_MAP.put(66, "water_15");
        WATER_MAP.put(72, "water_16");  WATER_MAP.put(74, "water_17");  WATER_MAP.put(75, "water_18");
        WATER_MAP.put(80, "water_19");  WATER_MAP.put(82, "water_20");  WATER_MAP.put(86, "water_21");
        WATER_MAP.put(88, "water_22");  WATER_MAP.put(90, "water_23");  WATER_MAP.put(91, "water_24");
        WATER_MAP.put(94, "water_25");  WATER_MAP.put(95, "water_26");  WATER_MAP.put(104, "water_27");
        WATER_MAP.put(106, "water_28"); WATER_MAP.put(107, "water_29"); WATER_MAP.put(120, "water_30");
        WATER_MAP.put(122, "water_31"); WATER_MAP.put(123, "water_32"); WATER_MAP.put(126, "water_33");
        WATER_MAP.put(127, "water_34"); WATER_MAP.put(208, "water_35"); WATER_MAP.put(210, "water_36");
        WATER_MAP.put(214, "water_37"); WATER_MAP.put(216, "water_38"); WATER_MAP.put(218, "water_39");
        WATER_MAP.put(219, "water_40"); WATER_MAP.put(222, "water_41"); WATER_MAP.put(223, "water_42");
        WATER_MAP.put(248, "water_43"); WATER_MAP.put(250, "water_44"); WATER_MAP.put(251, "water_45");
        WATER_MAP.put(254, "water_46"); WATER_MAP.put(255, "water_47");
    }

    /**
     * Possible method to aid in implementation of 'lighting' effects
     *
     * @return the path to the correct ground texture
     */
    public String groundCheck() {
        return "";
    }

    /**
     * Possible Method to connect water textures
     *
     * @return the path to the correct water texture
     */
    public String waterCheck() {
        return "";
    }

    /**
     * Method to check which wall graphic should be assigned to a wall cell -
     * Checks the surrounding cells - 9x9 grid - to determine the graphic
     *
     * @param x The x value of the wall cell
     * @param y The y value of the wall cell
     * @return the path to the correct wall texture
     */
    public String getWallType(int x, int y) {

        //Create the base of the path string
        String basePath = "../../assets/visuals/cells/walls/";
        //Array reference value
        int wallRef = 0;

        //Check for edge of map to prevent indexOutOfBounds exception
        if ((x > 1 && x < map[y].length) || (y > 1 && y < map.length)) {

            //Top row
            boolean NorthWest = map[y - 1][x - 1].getType() == CellType.WALL;
            boolean North = map[y - 1][x].getType() == CellType.WALL;
            boolean NorthEast = map[y - 1][x + 1].getType() == CellType.WALL;
            //Middle row
            boolean West = map[y][x - 1].getType() == CellType.WALL;
            boolean East = map[y][x + 1].getType() == CellType.WALL;
            //Bottom row
            boolean SouthWest = map[y + 1][x - 1].getType() == CellType.WALL;
            boolean South = map[y + 1][x].getType() == CellType.WALL;
            boolean SouthEast = map[y + 1][x + 1].getType() == CellType.WALL;

            //Top row
            if (NorthWest && North && West) {
                wallRef += 1;
            }
            if (North) {
                wallRef += 1 << 1;
            }
            if (NorthEast && North && East) {
                wallRef += 1 << 2;
            }

            //Middle Row
            if (West) {
                wallRef += 1 << 3;
            }
            if (East) {
                wallRef += 1 << 4;
            }

            //Bottom Row
            if (SouthWest && South && West) {
                wallRef += 1 << 5;
            }
            if (South) {
                wallRef += 1 << 6;
            }
            if (SouthEast && South && East) {
                wallRef += 1 << 7;
            }

        } else {
            //Set the map edges as map asset 1
            wallRef = 1;
        }

        return basePath + WALL_MAP.get(wallRef) + ".jpg";
    }


    /*

    Will also need to apply bitmasking algorithm in methods to

        - check floor cells for lighting effects
            - Give cells an "isLightSource" boolean value; perform bitmasking
              check based on that

        - check for water tiles
            - Will need identical setup to walls

    Try and make the bitmasking method generic for all use-cases

     */
}

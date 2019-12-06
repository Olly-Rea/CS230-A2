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
     * Method to check which wall graphic should be assigned to a wall cell -
     *
     * @param x The x value of the wall cell
     * @param y The y value of the wall cell
     * @return the path to the correct wall texture
     */
    public String getWallType(int x, int y) {
        // Create the base of the path string
        String basePath = "./assets/visuals/cells/Walls/";
        // Return a string with the wall type from the array reference value
        return basePath + WALL_MAP.get(bitMaskingAlgorithm(x, y, CellType.WALL)) + ".jpg";
    }
    
    /**
     * Method to check which wall graphic should be assigned to a wall cell -
     *
     * @param x The x value of the wall cell
     * @param y The y value of the wall cell
     * @return the path to the correct wall texture
     */    
    public String getWaterType(int x, int y) {
        // Create the base of the path string
        String basePath = "./assets/visuals/cells/Water/";     
        // Return a string with the water type from the array reference value
        return basePath + WATER_MAP.get(bitMaskingAlgorithm(x, y, CellType.WATER)) + ".jpg";
    }

     /**
     * BitMasking algorithm to calculate which graphic should be used for a tile 
     * based on the tiles surrounding it; with checks for map edges
     *
     * @param x The x value of the wall cell
     * @param y The y value of the wall cell
     * @param type The CellType of the input cell
     * 
     * @return the path to the correct wall texture
     */
    private int bitMaskingAlgorithm(int x, int y, CellType type) {
        
        int assetRef = 0;
        
        // Top row booleans
        boolean NorthWest = false;
        boolean North = false;
        boolean NorthEast = false;
        // Middle row booleans
        boolean West = false;
        boolean East = false;
        // Bottom row booleans
        boolean SouthWest = false;
        boolean South = false;
        boolean SouthEast = false;
        
        // Check ALL 4 corners of the map
        
        // Top-Left corner
        if (y == 0 && x == 0) {
            
            //Edge-case top row is always true
            NorthWest = true;
            North = true;
            NorthEast = true;
            //Calc middle row
            West = true;
            East = map[y][x + 1].getType() == type;
            //Calc bottom row
            SouthWest = true;
            South = map[y + 1][x].getType() == type;
            SouthEast = map[y + 1][x + 1].getType() == type;
        
        // Top-Right corner
        } else if (y == 0 && x == map[y].length - 1) {
            
            //Edge-case top row is always true
            NorthWest = true;
            North = true;
            NorthEast = true;
            //Calc middle row
            West = map[y][x - 1].getType() == type;
            East = true;
            //Calc bottom row
            SouthWest = map[y + 1][x - 1].getType() == type;
            South = map[y + 1][x].getType() == type;
            SouthEast = true;

        //Bottom-Left corner
        } else if (y == map.length - 1 && x == 0) {

            //Edge-case top row is always true
            NorthWest = true;
            North = map[y - 1][x].getType() == type;
            NorthEast = map[y - 1][x + 1].getType() == type;
            //Calc middle row
            West = true;
            East = map[y][x + 1].getType() == type;
            //Calc bottom row
            SouthWest = true;
            South = true;
            SouthEast = true;

        //Bottom-Right corner
        } else if (y == map.length - 1 && x == map[y].length - 1) {

            //Edge-case top row is always true
            NorthWest = map[y - 1][x - 1].getType() == type;
            North = map[y - 1][x].getType() == type;
            NorthEast = true;
            //Calc middle row
            West = map[y][x - 1].getType() == type;
            East = true;
            //Calc bottom row
            SouthWest = true;
            South = true;
            SouthEast = true;
            
        // Check for 'y = 0' corner of map
        } else if (y == 0 && (x >= 1 && x < map[y].length - 1)) {

            //Edge-case top row is always true
            NorthWest = true;
            North = true;
            NorthEast = true;
            //Calc middle row
            West = map[y][x - 1].getType() == type;
            East = map[y][x + 1].getType() == type;
            //Calc bottom row
            SouthWest = map[y + 1][x - 1].getType() == type;
            South = map[y + 1][x].getType() == type;
            SouthEast = map[y + 1][x + 1].getType() == type;

        // Check for 'y = max length' edge of map
        } else if (y == map.length - 1 && (x >= 1 && x < map[y].length - 1)) {
            
            //Calc top row
            NorthWest = map[y - 1][x - 1].getType() == type;
            North = map[y - 1][x].getType() == type;
            NorthEast = map[y - 1][x + 1].getType() == type;
            //Calc middle row
            West = map[y][x - 1].getType() == type;
            East = map[y][x + 1].getType() == type;     
            //Edge-case bottom row is always true
            SouthWest = true;
            South = true;
            SouthEast = true;
        
        // Check for 'x = 0' edge of map to prevent indexOutOfBounds exception
        } else if (x == 0 && (y >= 1 && y < map.length - 1)) {

            //Calc top row (Edge-case West is always true)
            NorthWest = true;
            North = map[y - 1][x].getType() == type;
            NorthEast = map[y - 1][x + 1].getType() == type;
            //Calc middle row (Edge-case West is always true)
            West = true;
            East = map[y][x + 1].getType() == type;
            //Calc bottom row (Edge-case West is always true)
            SouthWest = true;
            South = map[y + 1][x].getType() == type;
            SouthEast = map[y + 1][x + 1].getType() == type;
            
        // Check for 'x = max length' edge of map to prevent indexOutOfBounds exception
        } else if (x == map[y].length - 1 && (y >= 1 && y < map.length - 1)) {
            
            //Calc top row (Edge-case East is always true)
            NorthWest = map[y - 1][x - 1].getType() == type;
            North = map[y - 1][x].getType() == type;
            NorthEast = true;
            //Calc middle row (Edge-case East is always true)
            West = map[y][x - 1].getType() == type;
            East = true;
            //Calc bottom row (Edge-case East is always true)
            SouthWest = map[y + 1][x - 1].getType() == type;
            South = map[y + 1][x].getType() == type;
            SouthEast = true;

        // Otherwise, perform all checks to get the correct wall asset
        } else if ((x >= 1 && x < map[y].length - 1) && (y >= 1 && y < map.length - 1)) {

            //Calc top row
            NorthWest = map[y - 1][x - 1].getType() == type;
            North = map[y - 1][x].getType() == type;
            NorthEast = map[y - 1][x + 1].getType() == type;
            //Calc middle row
            West = map[y][x - 1].getType() == type;
            East = map[y][x + 1].getType() == type;
            //Calc bottom row
            SouthWest = map[y + 1][x - 1].getType() == type;
            South = map[y + 1][x].getType() == type;
            SouthEast = map[y + 1][x + 1].getType() == type;

        } else {
            //If something somehow goes wrong, set the wall cell as wall_1
            assetRef = 0;
        }
        
        
        //Top row
        if (NorthWest && North && West) {
            assetRef += 1;
        }
        if (North) {
            assetRef += 1 << 1;
        }
        if (NorthEast && North && East) {
            assetRef += 1 << 2;
        }

        //Middle Row
        if (West) {
            assetRef += 1 << 3;
        }
        if (East) {
            assetRef += 1 << 4;
        }

        //Bottom Row
        if (SouthWest && South && West) {
            assetRef += 1 << 5;
        }
        if (South) {
            assetRef += 1 << 6;
        }
        if (SouthEast && South && East) {
            assetRef += 1 << 7;
        }
        
        return assetRef;        
    }
    

    /*

    Will also need to apply bitmasking algorithm in methods to

        - check floor cells for lighting effects
            - Give cells an "isLightSource" boolean value; perform bitmasking
              check based on that

     */
}

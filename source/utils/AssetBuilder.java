package utils;

import cells.*;
import java.util.HashMap;

/**
 * AssetBuilder.java
 *
 * AssetBuilder class to allow for connecting textures for walls and other 
 * graphical items. 
 * 
 * @version 1.0.0
 * @author Olly Rea
 */
public class AssetBuilder {
    
    /*
        Create a hasMap containing the wall graphic filenames, indexed according 
        to the 8-bit tile bitmasking algorithm
        */
        final HashMap<Integer, String> WALL_MAP = new HashMap<>();
        Cell[][] map;
        
        public AssetBuilder(Cell[][] mapRef){
            //Pass the AssetBuilder class a reference of the cell map
            map = mapRef;
            
            //Add ALL the indexes and respective filenames to WALL_MAP
            WALL_MAP.put(0, "wall_1"); WALL_MAP.put(2, "wall_2"); WALL_MAP.put(8, "wall_3");
            WALL_MAP.put(10, "wall_4"); WALL_MAP.put(11, "wall_5"); WALL_MAP.put(16, "wall_6");
            WALL_MAP.put(18, "wall_7"); WALL_MAP.put(22, "wall_8"); WALL_MAP.put(24, "wall_9");
            WALL_MAP.put(26, "wall_10"); WALL_MAP.put(27, "wall_11"); WALL_MAP.put(30, "wall_12");
            WALL_MAP.put(31, "wall_13"); WALL_MAP.put(64, "wall_14"); WALL_MAP.put(66, "wall_15");
            WALL_MAP.put(72, "wall_16"); WALL_MAP.put(74, "wall_17"); WALL_MAP.put(75, "wall_18");
            WALL_MAP.put(80, "wall_19"); WALL_MAP.put(82, "wall_20"); WALL_MAP.put(86, "wall_21");
            WALL_MAP.put(88, "wall_22"); WALL_MAP.put(90, "wall_23"); WALL_MAP.put(91, "wall_24");
            WALL_MAP.put(94, "wall_25"); WALL_MAP.put(95, "wall_26"); WALL_MAP.put(104, "wall_27");
            WALL_MAP.put(106, "wall_28"); WALL_MAP.put(107, "wall_29"); WALL_MAP.put(120, "wall_30");
            WALL_MAP.put(122, "wall_31"); WALL_MAP.put(123, "wall_32"); WALL_MAP.put(126, "wall_33");
            WALL_MAP.put(127, "wall_34"); WALL_MAP.put(208, "wall_35"); WALL_MAP.put(210, "wall_36");
            WALL_MAP.put(214, "wall_37"); WALL_MAP.put(216, "wall_38"); WALL_MAP.put(218, "wall_39");
            WALL_MAP.put(219, "wall_40"); WALL_MAP.put(222, "wall_41"); WALL_MAP.put(223, "wall_42");
            WALL_MAP.put(248, "wall_43"); WALL_MAP.put(250, "wall_44"); WALL_MAP.put(251, "wall_45");
            WALL_MAP.put(254, "wall_46"); WALL_MAP.put(255, "wall_47");

        }
    
    /**
     * Possible method to aid in implementation of 'lighting' effects
     * @return 
     */
    public String groundCheck() {
        return "";
    }
    
    /**
     * Possible Method to connect water textures
     * @return 
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
    public String getwallType(int x, int y) {

        //Create the base of the path string
        String basePath = "../../assets/cells/";
               
        //Array reference value
        int wallRef = 0;
        //Power value
        int n = 0;

        //Loop through the surrounding cells
        for (int i = 1; i > -1; i--) {
            for (int j = -1; j < 1; j++) {
                //Check to ignore the center tile (the wall)
                if (i == 2 && j == 1) {
                    j++;
                } else {
                    //Add to the bitmask value if focused tile is a wall
                    if (map[x + i][y + j].getType() == CellType.WALL) {
                        wallRef += 1 << n;
                    }
                }
                //Increment the power
                n++;
            }
        }

        //If corner tiles have no surrounding tiles, ignore them
        
        //Also check for edge of map else indexOutOfBounds exception occurs

     
        
        return basePath + WALL_MAP.get(wallRef) + ".jpg";
    }
    
}

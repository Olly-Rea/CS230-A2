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
        HashMap<Integer, String> wallMap = new HashMap<>();
        Cell[][] map;
        
        public AssetBuilder(Cell[][] mapRef){
            //Pass the AssetBuilder class a reference of the cell map
            map = mapRef;
            
            //Add ALL the indexes and respective filenames to wallMap
            wallMap.put(0, "wall_1"); wallMap.put(2, "wall_2"); wallMap.put(8, "wall_3");
            wallMap.put(10, "wall_4"); wallMap.put(11, "wall_5"); wallMap.put(16, "wall_6");
            wallMap.put(18, "wall_7"); wallMap.put(22, "wall_8"); wallMap.put(24, "wall_9");
            wallMap.put(26, "wall_10"); wallMap.put(27, "wall_11"); wallMap.put(30, "wall_12");
            wallMap.put(31, "wall_13"); wallMap.put(64, "wall_14"); wallMap.put(66, "wall_15");
            wallMap.put(72, "wall_16"); wallMap.put(74, "wall_17"); wallMap.put(75, "wall_18");
            wallMap.put(80, "wall_19"); wallMap.put(82, "wall_20"); wallMap.put(86, "wall_21");
            wallMap.put(88, "wall_22"); wallMap.put(90, "wall_23"); wallMap.put(91, "wall_24");
            wallMap.put(94, "wall_25"); wallMap.put(95, "wall_26"); wallMap.put(104, "wall_27");
            wallMap.put(106, "wall_28"); wallMap.put(107, "wall_29"); wallMap.put(120, "wall_30");
            wallMap.put(122, "wall_31"); wallMap.put(123, "wall_32"); wallMap.put(126, "wall_33");
            wallMap.put(127, "wall_34"); wallMap.put(208, "wall_35"); wallMap.put(210, "wall_36");
            wallMap.put(214, "wall_37"); wallMap.put(216, "wall_38"); wallMap.put(218, "wall_39");
            wallMap.put(219, "wall_40"); wallMap.put(222, "wall_41"); wallMap.put(223, "wall_42");
            wallMap.put(248, "wall_43"); wallMap.put(250, "wall_44"); wallMap.put(251, "wall_45");
            wallMap.put(254, "wall_46"); wallMap.put(255, "wall_47");

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

     
        
        return basePath + wallMap.get(wallRef) + ".jpg";
    }
    
}

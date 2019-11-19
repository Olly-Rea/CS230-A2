package controllers;

/**
* MapController.java
* @version 1.0.0
* @author Daniel Clenaghan
*/

/**
*
* MapController is a class to manage the map and all methods related to it.
* It consists of a 2D array of cells and contains the methods required to alter
* and manipulate them
*/

public class MapController {

  // 2D array of cells that make up the game map
  private Cell[][] map;

  /**
  * Creates a MapController
  * @param Cell[][] The 2D array of cells that make up the MapController
  */
  public MapController (Cell[][] cellArray){
  }

  /**
  * Returns cell at location in cellArray
  * @param int x The x value of desired cell
  * @param int y The y value of the desired cell
  */
  public Cell getCell(int x,int y){
    return null;
  }

  /**
  * Changes a door at a given location to a empty cell
  * @param int x The x value of desired door
  * @param int y The y value of the desired door
  */
  public void openDoor(int x,int y){

  }

  /**
  * Exports the current map state as a string array to allow for game saving
  * @return String[]
  */
  public String[] export(){
    return null;
  }

  /**
  * Method to render the map to the screen centered on the player's location
  * @param playerLocation The player controller is used to access the player's
  * current location
  */
  public void render(PlayerController playerLocation){

  }
}

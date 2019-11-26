package entities;

import utils.Vector;

/**
* Player.java
* @version 1.0.0
* @author Daniel Clenaghan
*/


/**
* The player class is responsible for storing and manipulating the
* player inventory and its attributes
*/

public class Player extends Entity {

  private boolean hasFireBoots;
  private boolean hasFlippers;
  private int tokens;
  private int redKeys;
  private int greenKeys;
  private int blueKeys;
  private int yellowKeys;

  /**
  * Creates a Player object
  * @param location Takes a vector location when created
  */
  public Player (Vector pos){
    super(pos);
  }

  /**
  * Adds an item to the players inventory
  * @param Item The item to be added
  */
  public void addItem (Item item){

  }

  /**
  * Method to get the player's location
  * @return The player's vector
  */
  public Vector getPos (){
    return pos;
  }

  /**
  * Method to return the number of tokens in the player's inventory
  * @return The number of tokens
  */
  public int getTokens(){
    return tokens;
  }

  /**
  * Method to find if the player has collected fire boots
  * @return True if the player has picked up fire boots, else false
  */
  public Boolean hasFireBoots(){
    return hasFireBoots;
  }

  /**
  * Method to find if the player has collected flippers
  * @return True if the player has picked up flippers, else false
  */
  public Boolean hasFlippers(){
    return hasFlippers;
  }

  /**
  * Method to check for and to use an item in the player's inventory
  * @param item The item the game would like to use
  * @return True if the item is present, false otherwise
  */
  public Boolean useItem(Item item){
    return null;
  }
}

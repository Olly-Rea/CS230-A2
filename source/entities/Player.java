package entities;

/**
* Player.java
* The player class is responsible for storing and manipulating the
* player inventory and its attributes
* @author Daniel Clenaghan
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
  public Player (Vector location){
    super (location);
    this.hasFireBoots = false;
    this.hasFlippers = false;
    this.tokens = 0;
    this.redKeys = 0;
    this.greenKeys = 0;
    this.blueKeys = 0;
    this.yellowKeys = 0;
  }

  /**
  * Adds an item to the players inventory
  * @param Item The item to be added
  */
  public void addItem (Item item){
    ItemType add = item.getType;
    switch(add) {
      case TOKEN:
        tokens++;
        break;
      case FIREBOOTS:
        hasFireBoots = true;
        break;
      case FLIPPERS:
        hasFlippers = true;
        break;
      case REDKEY:
        redKeys++;
        break;
      case BLUEKEY:
        blueKeys++;
        break;
      case GREENKEY:
        greenKeys++;
        break;
      case YELLOWKEY:
        yellowKeys++;
        break;
    }
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
  * @return true if the player has picked up fire boots, else false
  */
  public Boolean hasFireBoots(){
    return hasFireBoots;
  }

  /**
  * Method to find if the player has collected flippers
  * @return true if the player has picked up flippers, else false
  */
  public Boolean hasFlippers(){
    return hasFlippers;
  }

  /**
  * Check for and use tokens from the player's inventory
  * @param tokensReq Tokens required
  * @return true if the tokens are present in the inventory, false otherwise
  */
  public Boolean useTokens(int tokensReq){
    if (tokensReq <= tokens){
      tokens = tokens - tokensReq;
      return true;
    } else {
      return false;
    }
  }

  /**
  * Method to check for and to use an item in the player's inventory
  * @param item The item the game would like to use
  * @return true if the item is present, false otherwise
  */
  public Boolean useItem(Item item){
    ItemType use = item.getType;
    switch(use) {
      case FIREBOOTS:
        if (hasFireBoots == true){
          return true;
        } else {
          return false;
        }
      case FLIPPERS:
        if (hasFlippers == true){
          return true;
        } else {
          return false;
        }
      case REDKEY:
        if (redKeys > 0){
          redKeys--;
          return true;
        } else {
          return false;
        }
      case BLUEKEY:
        if (blueKeys > 0){
          blueKeys--;
          return true;
        } else {
          return false;
        }
      case GREENKEY:
        if (greenKeys > 0){
          greenKeys--;
          return true;
        } else {
          return false;
        }
      case YELLOWKEY:
        if (yellowKeys > 0){
          yellowKeys--;
          return true;
        } else {
          return false;
        }
    }
  }
}

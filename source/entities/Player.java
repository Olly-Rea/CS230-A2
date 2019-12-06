package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Direction;
import utils.Vector;

/**
 * The player class is responsible for storing and manipulating the player
 * inventory and its attributes
 *
 * @author Daniel Clenaghan, Scott Barr, Olly Rea
 */
public class Player extends Entity {

    private boolean hasFireBoots;
    private boolean hasFlippers;
    private int tokens;
    private int redKeys;
    private int greenKeys;
    private int blueKeys;
    private int yellowKeys;

    private String assetPath = "./assets/visuals/entities/Player/";
    private Image playerAsset;

    /**
     * Creates a Player object
     *
     * @param location Takes a vector location when created
     */
    public Player(Vector location) {
        super(location);
        this.hasFireBoots = false;
        this.hasFlippers = false;
        this.tokens = 0;
        this.redKeys = 0;
        this.greenKeys = 0;
        this.blueKeys = 0;
        this.yellowKeys = 0;

        try {
            playerAsset = new Image(new FileInputStream(assetPath + "Player_Front.png"));
        } catch (FileNotFoundException e) {
            playerAsset = null;
            System.err.println("Player asset path not found!");
        }
    }

    /**
     * Sets the players inventory
     *
     * @param inventory array of integers corresponding to the inventory values
     */
    public void setInventory(int[] inventory) {
        hasFireBoots = inventory[0] == 1 ? true : false;
        hasFlippers = inventory[1] == 1 ? true : false;
        this.tokens = inventory[2];
        this.redKeys = inventory[3];
        this.greenKeys = inventory[4];
        this.blueKeys = inventory[5];
        this.yellowKeys = inventory[6];
    }

    /**
     * Adds an item to the players inventory
     *
     * @param Item
     */
    public void addItem(Item item) {
        ItemType add = item.getType();

        //Display the graphic of the item being added
        updatePlayerAsset(add);

        //Add the item to the player inventory
        switch (add) {
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
     *
     * @return The player's vector
     */
    public Vector getPos() {
        return pos;
    }

    /**
     * Method to change the player's location
     *
     * @param newPos player's new vector
     */
    public void setPos(Vector newPos) {
        this.pos = newPos;
    }

    /**
     * Method to return the number of tokens in the player's inventory
     *
     * @return tokens
     */
    public int getTokens() {
        return tokens;
    }

    /**
     * Method to collect fireshoes
     *
     */
    public void collectFireShoes() {
        hasFireBoots = true;
    }

    /**
     * Method to collect flippers
     *
     */
    public void collectFlippers() {
        hasFlippers = true;
    }

    /**
     * Method to find if the player has collected fire boots
     *
     * @return true if the player has picked up fire boots, else false
     */
    public boolean hasFireBoots() {
        return hasFireBoots;
    }

    /**
     * Method to find if the player has collected flippers
     *
     * @return true if the player has picked up flippers, else false
     */
    public boolean hasFlippers() {
        return hasFlippers;
    }

    /**
     * Check for and use tokens from the player's inventory
     *
     * @param tokensReq Tokens required
     * @return true if the tokens are present in the inventory, false otherwise
     */
    public boolean useTokens(int tokensReq) {
        if (tokensReq <= tokens) {
            tokens = tokens - tokensReq;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check for and to use an item in the player's inventory
     *
     * @param item The item the game would like to use
     * @return true if the item is present, false otherwise
     */
    public boolean useKey(ItemType item) {
        switch (item) {
            case REDKEY:
                if (redKeys > 0) {
                    redKeys--;
                    return true;
                } else {
                    return false;
                }
            case BLUEKEY:
                if (blueKeys > 0) {
                    blueKeys--;
                    return true;
                } else {
                    return false;
                }
            case GREENKEY:
                if (greenKeys > 0) {
                    greenKeys--;
                    return true;
                } else {
                    return false;
                }
            case YELLOWKEY:
                if (yellowKeys > 0) {
                    yellowKeys--;
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }

    }

    /**
     * Generate an array containing the player's inventory {X,Y,fireShoes,
     * flippers, tokens, red, green, blue, yellow}
     *
     * @return int array of player's inventory
     */
    public int[] getInventory() {
        int fireShoes = hasFireBoots ? 1 : 0;
        int flippers = hasFlippers ? 1 : 0;
        int[] playerSave = {fireShoes, flippers, tokens, redKeys, greenKeys, blueKeys,
            yellowKeys};
        return playerSave;
    }

    /**
     * Method to render the player to the screen.
     * @return an ImageView of the starting Player Asset
     */
    public ImageView render() {
        return new ImageView(playerAsset);
    }

    /**
     * Method to update the player asset based on the direction of the move made
     *
     * @param dir the direction the player has moved
     * @return an ImageView of the new player asset
     */
    public ImageView updatePlayerAsset(Direction dir) {
        String currAsset = assetPath;
        switch (dir) {
            case UP:
                currAsset += "Player_Back";
                break;
            case DOWN:
                currAsset += "Player_Front";
                break;
            case LEFT:
                currAsset += "Player_Left";
                break;
            case RIGHT:
                currAsset += "Player_Right";
                break;
            default:
                currAsset += "Player_Front";
                break;
        }
        //get the new image file for the player
        try {
            playerAsset = new Image(new FileInputStream(currAsset + ".png"));
        } catch (FileNotFoundException e) {
            playerAsset = null;
            System.err.println("Player asset path not found!");
        }
        return new ImageView(playerAsset);
    }

    /**
     * Overloaded method to update the player asset based on if an item has been found
     *
     * @param item the item to display
     * @return an ImageView of the new player asset
     */
    public ImageView updatePlayerAsset(ItemType item) {
        String currAsset = assetPath + "PlayerItems/";
        switch (item) {
            case FIREBOOTS:
                currAsset += "Player_Fireboots";
                break;
            case FLIPPERS:
                currAsset += "Player_Flippers";
                break;
            case REDKEY:
                currAsset += "Player_Redkey";
                break;
            case BLUEKEY:
                currAsset += "Player_Bluekey";
                break;
            case GREENKEY:
                currAsset += "Player_Greenkey";
                break;
            case YELLOWKEY:
                currAsset += "Player_Purplekey";
                break;
            default:
                break;
        }

        if(item != ItemType.TOKEN) {
            //get the new image file for the player
            try {
                playerAsset = new Image(new FileInputStream(currAsset + ".png"));
            } catch (FileNotFoundException e) {
                playerAsset = null;
                System.err.println("Player_item asset path not found!");
            }
        }
        return new ImageView(playerAsset);
    }

}

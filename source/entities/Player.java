package entities;

import utils.Vector;

/**
 * The player class is responsible for storing and manipulating the player
 * inventory and its attributes
 *
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
	}

	/**
	 * Creates a player object from a int array
	 * {X,Y,fireShoes,flippers,tokens,red,green,blue,yellow}
	 *
	 * @param inventory
	 */
	public Player(int[] inventory) {
		super(inventory[0], inventory[1]));
		if (inventory[2] == 1) {
			this.hasFireBoots = true;
		} else {
			this.hasFireBoots = false;
		}
		if (inventory[3] == 1) {
			this.hasFlippers = true;
		} else {
			this.hasFlippers = false;
		}
		this.tokens = inventory[4];
		this.redKeys = inventory[5];
		this.greenKeys = inventory[6];
		this.blueKeys = inventory[7];
		this.yellowKeys = inventory[8];
	}

	/**
	 * Adds an item to the players inventory
	 *
	 * @param Item
	 */
	public void addItem(Item item) {
		ItemType add = item.getType();
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
	public boolean useItem(Item item) {
		ItemType use = item.getType();
		boolean itemPresent = false;
		switch (use) {
		case FIREBOOTS:
			if (hasFireBoots == true) {
				itemPresent = true;
			}
		case FLIPPERS:
			if (hasFlippers == true) {
				itemPresent = true;
			}
		case REDKEY:
			if (redKeys > 0) {
				redKeys--;
				itemPresent = true;
			}
		case BLUEKEY:
			if (blueKeys > 0) {
				blueKeys--;
				itemPresent = true;
			}
		case GREENKEY:
			if (greenKeys > 0) {
				greenKeys--;
				itemPresent = true;
			}
		case YELLOWKEY:
			if (yellowKeys > 0) {
				yellowKeys--;
				itemPresent = true;
			}
		}
		return itemPresent;
	}

	/**
	 * Generate an array containing the player's inventory {X,Y,fireShoes, flippers,
	 * tokens, red, green, blue, yellow}
	 *
	 * @return int array of player's inventory
	 */
	public int[] export() {
		int fireShoes = 0;
		int flippers = 0;
		if (hasFireBoots() == true) {
			fireShoes = 1;
		}
		if (hasFlippers() == true) {
			flippers = 1;
		}
		int[] playerSave;
		playerSave = new int[] { pos.getX(), pos.getY(), fireShoes, flippers, tokens, redKeys, greenKeys, blueKeys,
				yellowKeys };
		return playerSave;
	}

	/**
	 * Method to render the player to the screen.
	 */
	public void render() {

	}
}

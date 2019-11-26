package cells;

import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Door.
 * @author advait kumar
 * @version 1.00
 */
public class Door {

	/** The type. */
	private DoorType type;

	/** The tokens. */
	private int tokens = 0;

	/** The colour. */
	private Color colour;

	/**
	 * Instantiates a new door.
	 *
	 * @param type the type
	 */
	public Door(DoorType type) {
		this.type = type;
	}

	public Door(int tokens) {
		this.tokens = tokens;
	}

	/**
	 * Sets the number of tokens.
	 *
	 * @param tokens the new number of tokens
	 */
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}

	/**
	 * Gets the tokens.
	 *
	 * @return the tokens
	 */
	public int getTokens() {
		return tokens;
	}

	/**
	 * Gets the door type.
	 *
	 * @return the door type
	 */
	public DoorType getType() {
		return type;
	}

}

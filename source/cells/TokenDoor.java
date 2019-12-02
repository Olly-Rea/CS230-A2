package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import entities.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TokenDoor extends Door {

    private static final String IMAGE_NAME = "Boulder";
    private static Image image;
	private int tokens;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Boulder image path not found");
        }
    }


	/**
	 * Constructs a token door at x, y
	 * 
	 * @param x Cell x
	 * @param y Cell y
	 */
	public TokenDoor(int x, int y) {
		super(x, y);
	}

	
	/**
	 * Sets the tokenDoor token requirement to tokens
	 * 
	 * @param tokens the token requirement
	 */
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}

	/**
	 * Returns the number of tokens needed to open the door.
	 * 
	 * @return tokens
	 */
	public int getTokens() {
		return tokens;
	}

	public char getChar() {
		return 'D';
	}

	/**
	 * Method to test whether the door is openable by the player.
	 * 
	 * @param p the player object to check for tokens
	 */
	public boolean isOpenable(Player p) {
		if (!(tokens > 0))
			System.err.println("Door at " + getPos().getX() + ", " + getPos().getY() + " has an invalid token limit");
		int pTokens = p.getTokens();
		if (pTokens >= tokens) {
			p.useTokens(tokens);
			return true;
		}
		return false;
	}

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}

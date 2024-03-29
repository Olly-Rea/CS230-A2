package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import entities.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Token door class to store information on the amount of tokens required for a door and check if a given
 * player has the tokens required to open. Will also render the cell into the map.
 * @version 1.0
 * @author Scott Barr
 */
public class TokenDoor extends Door {

    private static final String IMAGE_NAME = "Boulder_Token";
    private static Image image;
    private int tokens;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + "Boulders/" + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Boulder image path not found");
        }
    }

    /**
     * Constructs a token door at x, y
     *
     * @param x The horizontal position of the cell
     * @param y The vertical position of the cell
     */
    public TokenDoor(int x, int y) {
        super(x, y);
    }

    /**
     * Implementation of isLightSource 
     * 
     * @return false
     */
    public boolean isLightSource() {
        return false;
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
        if (!(tokens > 0)) {
            System.err.println("Door at " + getPos().getX() + ", " + getPos().getY() + " has an invalid token limit");
        }
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

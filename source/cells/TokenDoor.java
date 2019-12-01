package cells;

import entities.Player;

public class TokenDoor extends Door {

    private int tokens;

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
}

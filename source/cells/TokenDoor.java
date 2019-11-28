
public class TokenDoor extends Door {
	
	public int tokens = 0;
	
	public TokenDoor(CellType type) {
		
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
}

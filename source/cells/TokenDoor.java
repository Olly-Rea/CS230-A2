package cells;

import entities.Player;

public class TokenDoor extends Door {
	
	private final int tokens;
	
	public TokenDoor(int x, int y, int tokens) {
		super(x, y);
		this.tokens = tokens;
	}
	
	public boolean isOpenable(Player p) {
		int pTokens = p.getTokens();
		if (pTokens >= tokens) {
			p.useTokens(tokens);
			return true;
		} 
		return false;
	}
}

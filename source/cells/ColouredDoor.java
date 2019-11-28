package cells;

import entities.ItemType;
import entities.Player;

public class ColouredDoor extends Door {
	
	private final DoorColour colour;
	
	public ColouredDoor(int x, int y, DoorColour colour) {
		super(x, y);
		this.colour = colour;
	}
	
	public boolean isOpenable(Player p) {
		ItemType key = null;
		switch (colour) {
			case RED : key = ItemType.REDKEY;
				break;
			case BLUE : key = ItemType.BLUEKEY;
				break;
			case GREEN : key = ItemType.GREENKEY;
				break;
			case YELLOW : key = ItemType.YELLOWKEY;
				break;
		}
		return p.useKey(key);
	}
}

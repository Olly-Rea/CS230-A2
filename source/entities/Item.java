package entities;

public class Item extends Entity{

	private  ItemType type;

	/**
	 * Instantiates a new item.
	 * @param type the item type
	 */
	public Item (ItemType type, int x, int y) {
		super(x,y);
		this.type = type;
	}
	
	/**
	 * Gets the type of item in question.
	 *
	 * @return the item type
	 */
	public ItemType getType() {
		return type;
	}

	public void render() {

	}
}

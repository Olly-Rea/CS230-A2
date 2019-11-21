package entities;

public class Item {

	private  ItemType type;

	/**
	 * Instantiates a new item.
	 * @param type the item type
	 */
	public Item (ItemType type) {
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
}

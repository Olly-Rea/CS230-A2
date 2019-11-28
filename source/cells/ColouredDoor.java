import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Door.
 * @author advait kumar
 * @version 1.00
 */
public class ColouredDoor extends Door {
	
	/** The type. */
	private ColouredDoorType  colouredDoorType;
	
	/** The tokens. */
	private int tokens = 0;
	
	/** The colour. */
	private Color colour;
	
	/**
	 * Instantiates a new door.
	 *
	 * @param type the type
	 */
	public ColouredDoor(ColouredDoorType colouredDoorType, String colour) {
		super(colouredDoorType);
		this.colouredDoorType = colouredDoorType;
		
		switch(colour) {
			case "R":
			  colouredDoorType = ColouredDoorType.RED;
				break;
			case "B":
			  colouredDoorType = ColouredDoorType.BLUE;
				break;
			case "G":
				colouredDoorType = ColouredDoorType.GREEN;
				break;
			case "Y":
			  colouredDoorType = ColouredDoorType.YELLOW;
				break;	
		} 
	}

}

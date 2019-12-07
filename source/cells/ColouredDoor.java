package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import entities.ItemType;
import entities.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a door with a colour type attached to it. Contains method to check if
 * it can be opened or not
 *
 * @version 1.0
 * @author TODO
 */
public class ColouredDoor extends Door {

	public final DoorColour colour;
	private Image image;

    /**
     * Create a fire cell at location (x,y)
     * @param x
     * @param y
     */
	public ColouredDoor(int x, int y, DoorColour colour) {
		super(x, y);
		this.colour = colour;

		String imageName;
		switch (colour) {
		case RED:
			imageName = "Boulder_Red";
			break;
		case GREEN:
			imageName = "Boulder_Green";
			break;
		case BLUE:
			imageName = "Boulder_Blue";
			break;
		case YELLOW:
			imageName = "Boulder_Purple";
			break;
		default:
			imageName = "Boulder_Token";
		}

		try {
			image = new Image(new FileInputStream(ASSET_PATH + "Boulders/" + imageName + ".jpg"));
		} catch (FileNotFoundException e) {
			image = null;
			System.err.println(colour + " ColouredDoor image path not found");
		}
	}

        public boolean isLightSource() {
            return false;
        }

	public boolean isOpenable(Player p) {
		ItemType key = null;
		switch (colour) {
		case RED:
			key = ItemType.REDKEY;
			break;
		case BLUE:
			key = ItemType.BLUEKEY;
			break;
		case GREEN:
			key = ItemType.GREENKEY;
			break;
		case YELLOW:
			key = ItemType.YELLOWKEY;
			break;
		}
		return p.useKey(key);
	}

    /**
     * Return the character used in the map file for this cell
     * @return char
     */
	public char getChar() {
		switch (colour) {
			case RED : return 'R';
			case GREEN : return 'G';
			case YELLOW : return 'Y';
			case BLUE : return 'B';
			default : return 'D';
		}
	}

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}

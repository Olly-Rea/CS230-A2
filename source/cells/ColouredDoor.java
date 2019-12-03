package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import entities.ItemType;
import entities.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ColouredDoor extends Door {

	public final DoorColour colour;
	private Image image;

	public ColouredDoor(int x, int y, DoorColour colour) {
		super(x, y);
		this.colour = colour;

		String imageName;
		switch (colour) {
		case RED:
			imageName = "Boulder";
			break;
		case GREEN:
			imageName = "Boulder";
			break;
		case BLUE:
			imageName = "Boulder";
			break;
		case YELLOW:
			imageName = "Boulder";
			break;
		default:
			imageName = "Boulder";
		}

		try {
			image = new Image(new FileInputStream(ASSET_PATH + imageName + ".jpg"));
		} catch (FileNotFoundException e) {
			image = null; 
			System.err.println(colour + " ColouredDoor image path not found");
		}
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

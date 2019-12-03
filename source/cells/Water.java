package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Water extends Cell {

    private static final String IMAGE_NAME = "Water";
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Water image path not found");
        }
    }

    public Water(int x, int y) {
        super(CellType.WATER, x, y);
    }

    public char getChar() {
        return 'W';
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}
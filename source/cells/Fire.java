package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fire extends Cell {

    private static final String IMAGE_NAME = "Fire";
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Water image path not found");
        }
    }

    public Fire(int x, int y) {
        super(CellType.FIRE, x, y);
    }

    public ImageView render() {
        ImageView imageNode = new ImageView(image);
        return imageNode;
    }
}
package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Water extends Cell {

    private Image image;

    public void setImage(String path) {
        // String path = ASSET_PATH + "walls/" + imageName;
        try {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Wall image path not found at '" + path + "'");
        }
    }

    public Water(int x, int y) {
        super(CellType.WATER, x, y);
    }

    public boolean isLightSource() {
        return false;
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
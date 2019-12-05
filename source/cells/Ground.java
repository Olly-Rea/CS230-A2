package cells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ground extends Cell {

    private static final String IMAGE_NAME = "Floor_Dark";
    private Image image;

    public Ground(int x, int y) {
        super(CellType.GROUND, x, y);
        try {
            image = new Image(new FileInputStream(ASSET_PATH + IMAGE_NAME + ".jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Ground image path not found");
        }
    }
    
    public char getChar() {
        return ' ';
    }

    /**
     * Renders the Ground cell to the screen
     * 
     * @return an ImageView of the ground cell asset
     */
    public ImageView render() {
        return new ImageView(image);
    }
    
    /**
     * Method to change the asset of a ground tile to have debris after a 
     * boulder has been broken
     */
    public void addDebris() {
        try {
            image = new Image(new FileInputStream(ASSET_PATH + "Boulders/Boulder_Broken.jpg"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("Boulder_Broken image path not found");
        }    
    }
}
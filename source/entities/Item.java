package entities;

//Java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//JavaFX imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author
 */
public class Item extends Entity {

    private ItemType type;
    private Image assetImg;

    /**
     * Instantiates a new item.
     *
     * @param type the item type
     * @param x
     * @param y
     */
    public Item(ItemType type, int x, int y) {
        super(x, y);
        this.type = type;
        String assetPath = "./assets/visuals/entities/";
        
        //Set the asset image file dependent on item type
        switch (type) {
            case TOKEN:
                assetPath += "token";
                break;
            case FIREBOOTS:
                assetPath += "fireboots"; 
                break;
            case FLIPPERS:
                assetPath += "flippers";
                break;
            case REDKEY:
                assetPath += "Gems/red_key";
                break;
            case BLUEKEY:
                assetPath += "Gems/blue_key";
                break;
            case GREENKEY:
                assetPath += "Gems/green_key";
                break;
            case YELLOWKEY:
                assetPath += "Gems/purple_key";
                break;
        }
        assetPath += ".png";
        
        try {
            assetImg = new Image(new FileInputStream(assetPath));
        } catch (FileNotFoundException e) {
            assetImg = null;
            System.err.println(type + " image path not found");
        }        
    }

    public char getChar() {
        switch (type) {
            case REDKEY:return 'r';
            case BLUEKEY:return 'b';
            case GREENKEY:return 'g';
            case YELLOWKEY:return'y';
            case FLIPPERS:return'w';
            case FIREBOOTS:return'f';
            case TOKEN: return'*';
            default : return ' ';
        }
    }

    /**
     * Gets the type of item in question.
     *
     * @return the item type
     */
    public ItemType getType() {
        return type;
    }

    public ImageView render() {
        return new ImageView(assetImg);
    }
}

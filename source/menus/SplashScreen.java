package menus;

//JavaFX imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to display blank(ish) screens with a message on it
 *
 * @author Olly Rea
 */
public class SplashScreen extends Menu {

    private Label message;
    private final Label continueText = new Label("Press any key to continue");
    private boolean continueShown = true;
    
    //Static image for the Splashscreen
    private static ImageView menuTitle = null;
    //Set the MenuTitle image
    static {
        try {
            menuTitle = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/MenuTitle.png")));
        } catch (FileNotFoundException e) {
            System.err.println("game logo image path wasn't found");
        }
    }

    /**
     * Constructor for the SplashScreen
     * 
     * @param orignialMsg the original message to display on the splashScreen 
     *        on creation - this is originally the MOTD on game startup
     */
    public SplashScreen(String orignialMsg) {
        message = new Label(orignialMsg);
        menuLayout.getChildren().add(menuTitle);
        menuLayout.getChildren().add(message);
        toggleContinue();
        menuLayout.getChildren().add(continueText);
    }

    /**
     * Method to update the message displayed on the SplashScreen
     * 
     * @param message the new message to replace the old one
     */
    public void updateMessages(String message) {
        //Update the message label
        this.message.setText(message);
        // Clear previous messages
        menuLayout.getChildren().clear();
        //Add the game logo, the new message and the continue text
        menuLayout.getChildren().add(menuTitle);
        menuLayout.getChildren().add(this.message);
        toggleContinue();
        menuLayout.getChildren().add(continueText);
    }
    
    /**
     * Method to toggle the visibility of the "continue" text
     */
    public void toggleContinue() {
        if (continueShown) {
            continueShown = !continueShown;
            continueText.setStyle("-fx-font-color: rgba(255,255,255,0)");
        } else {
            continueShown= !continueShown;
            continueText.setStyle("-fx-font-color: rgba(255,255,255,1)");
        }
    }
}

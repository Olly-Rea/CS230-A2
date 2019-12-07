package menus;

//Local imports
import controllers.GameController;

//Java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//JavaFX imports
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;

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
    public SplashScreen(GameController gc, String motd) {
        menuTitle.getTransforms().add(new Scale(gc.SCALE_VAL, gc.SCALE_VAL, 0, 0));
        menuTitle.setStyle("-fx-translate-y: 60px");
        continueText.setStyle("-fx-translate-x: 120px; -fx-font-size: 15pt");
        
        message = new Label(motd);
        message.setStyle("-fx-min-width: 600px; -fx-max-width: 600px; -fx-translate-x: -70px");
        menuLayout.getChildren().add(menuTitle);
        menuLayout.getChildren().add(message);
        menuLayout.getChildren().add(continueText);
        message.toFront();
        continueText.toFront();
        
        scaleMenu();
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
        continueText.setVisible(!continueText.isVisible());
    }
}

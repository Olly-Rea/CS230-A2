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
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;

/**
 * Class to display blank(ish) screens with a message on it
 *
 * @author Olly Rea
 */
public class SplashScreen extends Menu {

    private Label message;
    private GameController gc;
    
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
    //Static image for the Splashscreen
    private static ImageView youDied = null;
    //Set the MenuTitle image
    static {
        try {
            youDied = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/youDiedGraphic.png")));
        } catch (FileNotFoundException e) {
            System.err.println("'you Died' image path wasn't found");
        }
    }
    
    /**
     * Constructor for the SplashScreen
     * 
     * @param motd the MOTD to display on game startup
     */
    public SplashScreen(GameController gc, String motd) {
        this.gc = gc;
        //Add the menu title to the splashScreen
        menuTitle.getTransforms().add(new Scale(gc.SCALE_VAL+0.2, gc.SCALE_VAL+0.2, 0, 0));
        menuTitle.setStyle("-fx-translate-x: -80px; -fx-translate-y: -20px");
        //Add the motd to the splashscreen
        message = new Label(motd);
        message.setStyle("-fx-min-width: 600px; -fx-max-width: 600px; -fx-translate-x: -70px; -fx-font-size:16pt");
        menuLayout.getChildren().add(menuTitle);
        menuLayout.getChildren().add(message);        
        scaleMenu();
    }

    /**
     * Method to update the message displayed on the SplashScreen
     * 
     * @param message the new message to replace the old one
     */
    public void morphScreen() {
        //morph the "game start" splash screen to the "you died" splash screen
        menuLayout.getChildren().clear();
        youDied.getTransforms().add(new Scale(gc.SCALE_VAL, gc.SCALE_VAL, 0, 0));
        youDied.setStyle("-fx-translate-y: -60px");
        menuLayout.getChildren().add(youDied);
    }
}

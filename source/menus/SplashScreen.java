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

    private static ImageView menuTitle = null;
    private static ImageView youDied = null;

    private Label message;
    private GameController gc;

    // Static image for the Splashscreen
    // Set the MenuTitle image
    static {
        try {
            menuTitle = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/MenuTitle.png")));
        } catch (FileNotFoundException e) {
            System.err.println("game logo image path wasn't found");
        }
    }

    // Static image for the Splashscreen
    // Set the MenuTitle image
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
     * @param gc The main GameController
     * @param motd the MOTD to display on game startup
     */
    public SplashScreen(GameController gc, String motd) {
        this.gc = gc;
        // Add the menu title to the splashScreen
        menuTitle.getTransforms().add(new Scale(GameController.SCALE_VAL + 0.2, GameController.SCALE_VAL + 0.2, 0, 0));
        menuTitle.setStyle("-fx-translate-x: -80px; -fx-translate-y: -20px");
        // Add the motd to the splashscreen
        message = new Label(motd);
        message.setStyle("-fx-min-width: 600px; -fx-max-width: 600px; -fx-translate-x: -70px; -fx-font-size:16pt");
        menuLayout.getChildren().add(menuTitle);
        menuLayout.getChildren().add(message);
        scaleMenu();
    }

    /**
     * Method to update the message displayed on the SplashScreen
     */
    public void morphScreen() {
        // morph the "game start" splash screen to the "you died" splash screen
        menuLayout.getChildren().clear();
        menuLayout.setStyle("-fx-background-color: black");
        youDied.getTransforms().add(new Scale(GameController.SCALE_VAL, GameController.SCALE_VAL, 0, 0));
        youDied.setStyle("-fx-translate-x: 100px; -fx-translate-y: 200px");
        menuLayout.getChildren().add(youDied);
    }

    /**
     * Custom toggle method to display the SplashScreen before the game menus
     * 
     * @param sPM the SelectProfileMenu to display after the SplashScreen
     */
    public void toggle(SelectProfileMenu sPM) {
        menuLayout.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(3000), menuLayout);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        ft.setOnFinished((ActionEvent e) -> {
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
            ft.setOnFinished((ActionEvent f) -> {
                menuLayout.setVisible(false);
                morphScreen();
                sPM.toggle();
            });
        });
    }

    /**
     * Custom toggle method to show and then hide the "you died" splashscreen
     */
    public void toggle() {
        menuLayout.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), menuLayout);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        ft.setOnFinished((ActionEvent e) -> {
            gc.restart();
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
            ft.setOnFinished((ActionEvent f) -> {
                menuLayout.setVisible(false);
            });
        });
    }
}

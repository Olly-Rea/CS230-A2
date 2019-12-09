package menus;

//Local imports
import controllers.GameController;
import misc.Profile;

//Java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//JavaFX imports
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;


/**
 * @author Scott Barr, Olly Rea
 */
public class CreateProfileMenu extends Menu {

    /**
     * Constructor for the CreateProfileMenu
     * @param gc the GameController to give the menu access to methods it 
     *        requires for its functionality
     */
    public CreateProfileMenu(GameController gc) {

        super();

        Label putName = new Label("Enter your profile name:");
        TextField input = new TextField();
        
        ImageView createProfileButton = null;

        try {
            createProfileButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/newProfileButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("new profile button path wasn't found");
        }
        Button createProfile = new Button();
        // Add the button graphic and scale the button
        createProfile.setGraphic(createProfileButton);
        createProfile.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        // Add the event handler
        createProfile.setOnAction((ActionEvent e) -> {
            // submit profile
            Profile p = Profile.fromLine(input.getText());
            p.saveProfile();
            gc.setProfile(p);
            this.toggle();
        });

        menuLayout.getChildren().add(putName);
        menuLayout.getChildren().add(input);
        menuLayout.getChildren().add(createProfile);
        
        scaleMenu();
    }
}

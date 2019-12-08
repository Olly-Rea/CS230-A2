package menus;

//Local imports
import controllers.GameController;
import misc.Profile;

//Java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

//JavaFX imports
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;

/**
 * Menu to display the leaderboard after a level is finished
 *
 * @author Olly Rea, Daniel Clenaghan
 */
public class LeaderboardMenu extends Menu {


    private VBox selection = new VBox();
    private Label player;

    public LeaderboardMenu(GameController gc) {
        // set not visible
        super();

        ImageView nextLevelButton = null;
        ImageView returnButton = null;

        try {
            nextLevelButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/nextLevelButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("new profile button path wasn't found");
        }
        try {
            returnButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/returnButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("select profile button path wasn't found");
        }
        
        Button nextLevel = new Button();
        // Add the button graphic and scale the button
        nextLevel.setGraphic(nextLevelButton);
        nextLevel.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        // Add the event handler
        nextLevel.setOnAction((ActionEvent e) -> {
            gc.nextLevel();
            // Close + start next level
        });

        Button back = new Button();
        // Add the button graphic and scale the button
        back.setGraphic(returnButton);
        back.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        // Add the event handler
        back.setOnAction((ActionEvent e) -> {
            gc.toLevelSelect();
        });

        menuLayout.getChildren().add(selection);
        menuLayout.getChildren().add(back);
        menuLayout.getChildren().add(nextLevel);

        scaleMenu();

    }

    public void loadLeaderboard(int currentMapNum, GameController gc) {
        ArrayList<String> lbList = new ArrayList<String>();
        lbList = gc.getLeaderboard();
        selection.getChildren().clear();
        Label first = new Label(lbList.get(0));
        Label second = new Label(lbList.get(1));
        Label third = new Label(lbList.get(2));
        selection.getChildren().add(first);
        selection.getChildren().add(second);
        selection.getChildren().add(third);
        selection.getChildren().add(player);

    }

    /**
     * Displays the player's score under the leader board
     *
     * @param profile
     * @param time
     */
    public void displayPlayer(Profile profile, double time) {
        String playerScore = "Your score: " + profile.getName() + " : " + time;
        Label player = new Label(playerScore);
        this.player = player;
    }
}

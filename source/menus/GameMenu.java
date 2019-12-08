package menus;

//Local imports
import controllers.GameController;

//java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//JavaFX imports
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.scene.transform.Scale;

/**
 * @author Scott Barr, Olly Rea
 */
public class GameMenu extends Menu {

    public GameMenu(GameController gc) {
        super();


        menuLayout.setSpacing(80);
        //add the New Game button to the menu VBox
        ImageView newGameButton = null;
        ImageView loadGameButton = null;
        ImageView saveGameButton = null;
        ImageView exitGameButton = null;

        try {
            newGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/restartLvlButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("new game button path wasn't found");
        }
        try {
            saveGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/saveGameButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("save game button path wasn't found");
        }
        try {
            loadGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/loadGameButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("load game button path wasn't found");
        }
        try {
            exitGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/exitGameButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("exit game button path wasn't found");
        }
        //add current level time to menu
        /*
        int currentTime = GameController.getCurrentTime() - GameController.getStartTime();
        Text time = new Text(10, 50, "Current Time: " + (currentTime)/1000) + " seconds";
        time.setFont(new Font(20));
        menuLayout.getChildren().add(time);
        */

        //Create the newGame button, assign the graphic, and add to VBox
        /*
        Button newGame = new Button();
        newGame.setGraphic(newGameButton);
        newGame.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gc.restart();
                toggle();
            }
        });

        menuLayout.getChildren().add(newGame);
        */

        Button restartLvl = new Button();
        restartLvl.setGraphic(restartLvl);
        restartLvl.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        restartLvl.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gc.restart();
                toggle();
            }
        });
        menuLayout.getChildren().add(restartLvl);
        

        //Create the loadGame button, assign the graphic, and add to VBox
        Button saveGame = new Button();
        saveGame.setGraphic(saveGameButton);
        saveGame.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        saveGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("Enter the filename");
                String result = "";
                while (result.length() == 0) {
                    result = dialog.showAndWait().get();
                }
                gc.saveGame(result);
                toggle();
                //Create load game scene
            }
        });
        menuLayout.getChildren().add(saveGame);

        
        //Create the loadGame button, assign the graphic, and add to VBox
        Button loadGame = new Button();
        loadGame.setGraphic(loadGameButton);
        loadGame.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gc.loadSaves();
                toggle();
                //Create load game scene
            }
        });
        menuLayout.getChildren().add(loadGame);
        

        //Create the exitGame button, assign the graphic, and add to VBox
        Button exitGame = new Button();
        exitGame.setGraphic(exitGameButton);
        exitGame.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        menuLayout.getChildren().add(exitGame);

        scaleMenu();

    }
}

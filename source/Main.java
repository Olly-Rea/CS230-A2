//Local imports
import controllers.GameController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//Java imports
import java.io.IOException;
//JavaFX imports
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws IOException {
        window.setTitle("Game");
        window.setResizable(false);

        double scaleVal = 0.6;
        // 1400 - 20 from the window size due to 'non-resizeable' window change
        final double WINDOW_SIZE = 1380 * scaleVal;

        GameController gc = new GameController();
        
        Group root = Menu(gc, window, scaleVal);
        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE, Color.rgb(30, 16, 0));

        scene.setOnKeyPressed(e -> {
            gc.gameStep(e, root, scaleVal);
        });

        window.setScene(scene);
        window.show();
    }
    
    private Group Menu(GameController gc, Stage window, double scaleVal) {
        //Create the group to export and the VBox to store the menu buttons
        Group root = new Group();
        VBox menuLayout = new VBox(80);
        
        //Create the game menu title
        Label outputLabel = new Label("THIS IS OUR GAME");
        menuLayout.getChildren().add(outputLabel);
               
        
        //BUTTON LAYOUTS BELOW
        
        
        //add the New Game button to the menu VBox
        ImageView newGameButton;
        try {
            newGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/newGameButton.png")));
        } catch (FileNotFoundException e) {
            newGameButton = null;
            System.err.println("loadGameButton.png path not found");
        }
        newGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        //Create the button and assign the graphic
        Button newGame = new Button("New Game");
        newGame.setGraphic(newGameButton);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                gc.render(root, scaleVal);
            }
        });
        menuLayout.getChildren().add(newGame);
        
        
        //add the Load Game button to the menu VBox
        ImageView loadGameButton;
        try {
            loadGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/loadGameButton.png")));
        } catch (FileNotFoundException e) {
            loadGameButton = null;
            System.err.println("loadGameButton.png path not found");
        }
        loadGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        //Create the button and assign the graphic
        Button loadGame = new Button("Load Game");
        loadGame.setGraphic(loadGameButton);
        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String savefilePath = "./savefiles";
                //Create load game scene
            }
        });
        menuLayout.getChildren().add(loadGame);
        
        
        //add the Exit Game button to the menu VBox
        ImageView exitGameButton;
        try {
            exitGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/exitGameButton.png")));
        } catch (FileNotFoundException e) {
            exitGameButton = null;
            System.err.println("exitGameButton.png path not found");
        }
        exitGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        //Create the button and assign the graphic
        Button exitGame = new Button("Exit Game");
        exitGame.setGraphic(exitGameButton);
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                window.close();
            }
        });
        menuLayout.getChildren().add(exitGame);
        
        //Add background graphics to the menu
        Image menuBackImg;
        try {
            menuBackImg = new Image(new FileInputStream("./assets/visuals/menu/MenuBackground.jpg"));
        } catch (FileNotFoundException e) {
            menuBackImg = null;
            System.err.println("MenuBackground.jpg path not found");
        }
        ImageView menuBack = new ImageView(menuBackImg);
        menuBack.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        root.getChildren().add(menuBack);
        
        Image featherEdgeImg;
        try {
            featherEdgeImg = new Image(new FileInputStream("./assets/visuals/Feather_Edge.png"));
        } catch (FileNotFoundException e) {
            featherEdgeImg = null;
            System.err.println("Feather_Edge.png path not found");
        }
        ImageView featherEdge = new ImageView(featherEdgeImg);
        featherEdge.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        root.getChildren().add(featherEdge);
        
        //Add the menu buttons to the menuRoot group
        root.getChildren().add(menuLayout);
        root.getChildren().get(2).setLayoutX(380*scaleVal);
        root.getChildren().get(2).setLayoutY(300*scaleVal);
        
        //Return the root
        return root;
    }
    
    private void LoadGame() {
        
    }
}

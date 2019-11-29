import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

import controllers.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) throws IOException {
        window.setTitle("Game");

        Group root = new Group();
        Scene scene = new Scene(root);

        GameController gc = new GameController();

        EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
            }
        };
        gc.render(root);
        
        scene.setOnKeyPressed(keyEventHandler);

        window.setScene(scene);
        window.show();
    }
}
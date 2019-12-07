//Local imports
import controllers.GameController;

//Java imports
import java.io.IOException;

//JavaFX imports
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main.java
 *
 * @version 1.0.0
 * @author Olly Rea, Scott Barr
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws IOException {
        window.setTitle("Game");
        window.setResizable(false);

        Group root = new Group();
        GameController gc = new GameController(root);

        // 1400 - 20 from the window size due to 'non-resizeable' window change
        final double WINDOW_SIZE = 1380 * GameController.SCALE_VAL;        
        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE, Color.rgb(30, 16, 0));      
        gc.render();

        scene.setOnKeyPressed(e -> {
            gc.gameStep(e);
        });

        window.setScene(scene);
        window.show();
    }
}

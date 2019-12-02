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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import misc.Menu;

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
    
   
    
    private void LoadGame() {
        
    }
}

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

        double scaleVal = 0.6;
        // 1400 - 20 from the window size due to 'non-resizeable' window change
        final double WINDOW_SIZE = 1380 * scaleVal;

        GameController gc = new GameController();
        
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE, Color.rgb(30, 16, 0));      

        gc.render(root, scaleVal);
        Menu menu = new Menu(gc, root, scaleVal);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                menu.toggle();
            } else {
                gc.gameStep(e, root, scaleVal);
            }
        });

        window.setScene(scene);
        window.show();
    }
    
   
    
    private void LoadGame() {
        
    }
}

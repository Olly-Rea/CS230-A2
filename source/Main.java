import java.io.IOException;

import controllers.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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
        //1400 - 20 from the window size due to 'non-resizeable' window change
        final double WINDOW_SIZE = 1380 * scaleVal;
        
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE, Color.rgb(30,16,0));

        GameController gc = new GameController();
        
        gc.render(root, scaleVal);
        
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                //System.out.println("W key was pressed");
                gc.renderMove(root , 0, 200, scaleVal);
            }
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                //System.out.println("A key was pressed");
                gc.renderMove(root , 200, 0, scaleVal);
            }
            if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                //System.out.println("S key was pressed");
                gc.renderMove(root , 0, -200, scaleVal);
            }
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                //System.out.println("D key was pressed");
                gc.renderMove(root , -200, 0, scaleVal);
            }
        });
        
        window.setScene(scene);
        window.show();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import controllers.GameController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Olive
 */
public class GameMenu extends Menu {

    public GameMenu(GameController gc) {
        super();        
        //Create the group to export and the VBox to store the menu buttons
        //Create the game menu title
        Label outputLabel = new Label("THIS IS OUR GAME");
        menuLayout.getChildren().add(outputLabel);
        
        //BUTTON LAYOUTS BELOW
        
        //add the New Game button to the menu VBox
        ImageView newGameButton = null;
        ImageView loadGameButton = null;
        ImageView exitGameButton = null;
        try {
            newGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/newGameButton.png")));
            //newGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
            loadGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/loadGameButton.png")));
            //loadGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
            exitGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/exitGameButton.png")));
            //exitGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));                    
        } catch (FileNotFoundException e) {
            newGameButton = null;
            System.err.println("A button path wasn't found");
        }
        
        //Create the newGame button, assign the graphic, and add to VBox
        Button newGame = new Button("New Game");
        newGame.setGraphic(newGameButton);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gc.restart();
            }
        });
        menuLayout.getChildren().add(newGame);
        
        
        //Create the loadGame button, assign the graphic, and add to VBox
        Button loadGame = new Button("Load Game");
        loadGame.setGraphic(loadGameButton);
        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                gc.loadSaves();
                toggle();
                //Create load game scene
            }
        });
        menuLayout.getChildren().add(loadGame);
        
        
        //Create the exitGame button, assign the graphic, and add to VBox
        Button exitGame = new Button("Exit Game");
        exitGame.setGraphic(exitGameButton);
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        menuLayout.getChildren().add(exitGame);    
    }    
}

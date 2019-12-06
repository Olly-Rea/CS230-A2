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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;

/**
 *
 * @author Olive
 */
public class GameMenu extends Menu {

    public GameMenu(GameController gc) {
        super();        
        menuLayout.getStylesheets().add("File:./assets/styles/button.css");
        
        //Create the group to export and the VBox to store the menu buttons
        //Create the game menu title
        Label outputLabel = new Label("THIS IS OUR GAME");
        menuLayout.getChildren().add(outputLabel);
        double scaleVal = GameController.SCALE_VAL;
        
        //BUTTON LAYOUTS BELOW
        
        //add the New Game button to the menu VBox
        ImageView newGameButton = null;
        ImageView loadGameButton = null;
        ImageView saveGameButton = null;
        ImageView exitGameButton = null;

        try {
            newGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/newGameButton.png")));
            saveGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/saveGameButton.png")));
            saveGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        } catch (FileNotFoundException e) {
            System.err.println("A button path wasn't found");
        }
        try {
            loadGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/loadGameButton.png")));
            loadGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        } catch (FileNotFoundException e) {
            System.err.println("A button path wasn't found");
        }
        try {
            exitGameButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/exitGameButton.png")));
            exitGameButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));                    
        } catch (FileNotFoundException e) {
            System.err.println("A button path wasn't found");
        }
        
        //Create the newGame button, assign the graphic, and add to VBox
        Button newGame = new Button();
        newGame.setGraphic(newGameButton);
        newGame.setStyle("-fx-border-style: none; -fx-background-color:rgba(0,0,0,0)");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                newGame.setStyle("-fx-scale: 0.8");
                gc.restart();
                toggle();
            }
        });
        menuLayout.getChildren().add(newGame);
        
        
        //Create the loadGame button, assign the graphic, and add to VBox
        Button loadGame = new Button();
        loadGame.setGraphic(loadGameButton);
        loadGame.setStyle("-fx-border-style: none; -fx-background-color:rgba(0,0,0,0)");
        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                loadGame.setStyle("-fx-scale: 0.8");
                gc.loadSaves();
                toggle();
                //Create load game scene
            }
        });
        menuLayout.getChildren().add(loadGame);

                //Create the loadGame button, assign the graphic, and add to VBox
        Button saveGame = new Button();
        saveGame.setGraphic(saveGameButton);
        saveGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                saveGame.setStyle("-fx-scale: 0.8");
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
        
        
        //Create the exitGame button, assign the graphic, and add to VBox
        Button exitGame = new Button();
        exitGame.setGraphic(exitGameButton);
        exitGame.setStyle("-fx-border-style: none; -fx-background-color:rgba(0,0,0,0)");
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                exitGame.setStyle("-fx-scale: 0.8");
                Platform.exit();
            }
        });
        menuLayout.getChildren().add(exitGame);    
    }    
}

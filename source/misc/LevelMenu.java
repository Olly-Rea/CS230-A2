/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import controllers.GameController;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import utils.FileHandler;

/**
 *
 * @author Olive
 */
public class LevelMenu extends Menu {

    private static String MAP_DIR = "./levelfiles/";
    private ListView<String> mapChoices;
    private String selected = null;


    public LevelMenu(GameController gc) {
        // set not visible
        super();        

        // Selection box setup
        setupMapChoice();

        // new profile button setup

        // go button
        Button goButton = new Button("Go");
        goButton.setOnAction((ActionEvent e)->{
            gc.loadGame(MAP_DIR + selected + ".txt");
            gc.render();
        });

        menuLayout.getChildren().add(mapChoices);
        menuLayout.getChildren().add(goButton);
    }

    private void setupMapChoice() {
        mapChoices = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(getMaps());
        mapChoices.setItems(FXCollections.observableArrayList(getMaps()));
        mapChoices.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        mapChoices.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends String> ov, String oldVal, String val) -> {
                selected = val;
            }
        );
    }

    

    private String[] getMaps() {
        File[] files = FileHandler.getFiles(MAP_DIR);
        String[] fileNames = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            fileNames[i] = FileHandler.getFileName(files[i]);
        }

        return fileNames;
        // return new ChoiceBox<String>(FXCollections.observableArrayList(fileNames));
    }
}

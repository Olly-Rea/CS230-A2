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
import javafx.scene.layout.VBox;
import utils.FileHandler;

/**
 *
 * @author Olive
 */
public class LevelMenu extends Menu {

    public static final String[] levels = {"test1", "test2", "test3", "test4"};
    private static String MAP_DIR = "./levelfiles/";
    private static String SAVE_DIR = "./savefiles/";

    private VBox selection = new VBox();
    private ListView<String> mapChoices = new ListView<String>();
    private String selected = null;
    private String path = MAP_DIR;

    public LevelMenu(GameController gc) {
        // set not visible
        super();        

        // go button
        Button goButton = new Button("Go");
        goButton.setOnAction((ActionEvent e)->{
            gc.loadGame(path + selected + ".txt");
            gc.render();
        });

        menuLayout.getChildren().add(goButton);
        menuLayout.getChildren().add(selection);
    }

    private ListView<String> setupMapChoice(String[] files) {
        ListView<String> maps = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(files);
        maps.setItems(FXCollections.observableArrayList(files));
        maps.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        maps.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends String> ov, String oldVal, String val) -> {
                selected = val;
            }
        );
        return maps;
    }

    public void loadSaves(Profile p) {
        String[] maps = getSaves(p);
        path = SAVE_DIR + p.getName() + "/";
        mapChoices = setupMapChoice(maps);

        selection.getChildren().clear();
        selection.getChildren().add(mapChoices);
    }

    public void loadLevels(int level) {
        String[] maps = getMaps(level);
        path = MAP_DIR;
        mapChoices = setupMapChoice(maps);

        selection.getChildren().clear();
        selection.getChildren().add(mapChoices);
    }

    private String[] getMaps(int level) {
        String[] files = new String[level];
        for (int i = 0; i < level; i++) {
            files[i] = levels[i];
        }
        return files;
    }

    private String[] getSaves(Profile p) {
        String name = p.getName();
        File[] files = FileHandler.getFiles(SAVE_DIR + name + "/");
        String[] fileNames = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            fileNames[i] = FileHandler.getFileName(files[i]);
        }

        return fileNames;
    }
}

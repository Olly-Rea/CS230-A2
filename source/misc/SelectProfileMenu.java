/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;


import controllers.GameController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import utils.FileHandler;
import java.util.ArrayList;

import misc.Profile;

/**
 *
 * @author Olive
 */
public class SelectProfileMenu extends Menu {

    private CreateProfileMenu createProfileMenu;
    private ListView<Profile> selectionList;
    private Profile selection = null;


    public SelectProfileMenu(GameController gc) {
        // set not visible
        super();

        createProfileMenu = new CreateProfileMenu(this, gc);

        Button newProfile = new Button("Create New Profile");
        newProfile.setOnAction((ActionEvent e) -> {
            createProfileMenu.toggle();
            // newProfile.setVisible(false);
        });

        Button useProfile = new Button("No player selected");
        useProfile.setDisable(true);
        useProfile.setOnAction((ActionEvent e) -> {
            gc.setProfile(selection);
        });

        makeSelection();
        selectionList.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends Profile> ov, Profile oldVal, Profile val) -> {
                selection = val;
                useProfile.setDisable(false);
                useProfile.setText(String.format("Use %s!", val.getName()));
            }
        );

        menuLayout.getChildren().add(selectionList);
        menuLayout.getChildren().add(useProfile);
        menuLayout.getChildren().add(newProfile);
        menuLayout.getChildren().add(createProfileMenu.render());
    }

    private void makeSelection() {
        selectionList = new ListView<>();
        ObservableList<Profile> items = FXCollections.observableArrayList(getProfiles());
        selectionList.setItems(items);
        selectionList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    private Profile[] getProfiles() {
        FileHandler reader = new FileHandler(Profile.PROFILE_PATH);
        ArrayList<Profile> profiles = new ArrayList<>();
        while (reader.hasNext()) {
           Profile p = Profile.fromLine(reader.nextLine());
           profiles.add(p);
        }
        return profiles.toArray(new Profile[profiles.size()]);
    }
}

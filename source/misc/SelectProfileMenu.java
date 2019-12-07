/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import controllers.GameController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import utils.FileHandler;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;

import misc.Profile;

/**
 *
 * @author Olive
 */
public class SelectProfileMenu extends Menu {

    private ListView<Profile> selectionList = new ListView<>();

    private Profile selection = null;

    public SelectProfileMenu(GameController gc) {

        super();

        ImageView newProfileButton = null;
        ImageView selectProfileButton = null;
        ImageView deleteProfileButton = null;

        try {
            newProfileButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/newProfileButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("new profile button path wasn't found");
        }
        try {
            selectProfileButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/selectProfileButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("select profile button path wasn't found");
        }
        try {
            deleteProfileButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/deleteProfileButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("new game button path wasn't found");
        }

        Button useProfile = new Button();
        useProfile.setDisable(true);
        // Add the button graphic and scale the button
        useProfile.setGraphic(selectProfileButton);
        useProfile.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        // Add the event handler
        useProfile.setOnAction((ActionEvent e) -> {
            this.toggle();
            gc.setProfile(selection);
        });

        Button deleteProfile = new Button();
        deleteProfile.setDisable(true);
        // Add the button graphic and scale the button
        deleteProfile.setGraphic(deleteProfileButton);
        deleteProfile.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        // Add the event handler
        deleteProfile.setOnAction((ActionEvent e) -> {
            selection.deleteProfile();
            useProfile.setDisable(true);
            deleteProfile.setDisable(true);
            makeSelection();
        });

        makeSelection();
        selectionList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Profile> ov, Profile oldVal, Profile val) -> {
                    if (val != null) {
                        selection = val;
                        useProfile.setDisable(false);
                        deleteProfile.setDisable(false);
                    } else {
                        selection = null;
                    }
                }
        );

        Button newProfile = new Button();
        // Add the button graphic and scale the button
        newProfile.setGraphic(newProfileButton);
        newProfile.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        // Add the event handler
        newProfile.setOnAction((ActionEvent e) -> {
            gc.createProfile();
            this.render();
        });

        menuLayout.getChildren().add(selectionList);
        menuLayout.getChildren().add(useProfile);
        menuLayout.getChildren().add(deleteProfile);
        menuLayout.getChildren().add(newProfile);

        scaleMenu();

    }

    private void makeSelection() {
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

    @Override
    public void scaleMenu() {
        double menuWidth = 1380 - (menuLayout.getWidth());
        double menuHeight = 1380 - (menuLayout.getHeight());

        menuLayout.setPadding(new Insets((menuHeight / 7.5) * scaleVal,
                (menuWidth / 4.5) * scaleVal,
                (menuHeight / 4.5) * scaleVal,
                (menuWidth / 4.5) * scaleVal));
    }

}

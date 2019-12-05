/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;


import controllers.GameController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import misc.Profile;

/**
 *
 * @author Olive
 */
public class CreateProfileMenu extends Menu {

    public CreateProfileMenu(SelectProfileMenu parent, GameController gc) {
        // set not visible
        super();

        Label putName = new Label("Enter your profile name : ");
        TextField input = new TextField();
        Button submit = new Button("Create Player");
        submit.setOnAction((ActionEvent e) -> {
            // submit profile
            Profile p = Profile.fromLine(input.getText());
            p.saveProfile();
            gc.setProfile(p);
        });

        HBox newProfile = new HBox(30);

        newProfile.getChildren().add(putName);
        newProfile.getChildren().add(input);
        newProfile.getChildren().add(submit);
        
        menuLayout.getChildren().add(newProfile);
    }


}

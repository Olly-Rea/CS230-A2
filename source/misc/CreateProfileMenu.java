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

import misc.Profile;

/**
 *
 * @author Olive
 */
public class CreateProfileMenu extends Menu {

    public CreateProfileMenu(GameController gc) {
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
            this.toggle();
        });

        menuLayout.getChildren().add(putName);
        menuLayout.getChildren().add(input);
        menuLayout.getChildren().add(submit);
        
        scaleMenu();
    }
}

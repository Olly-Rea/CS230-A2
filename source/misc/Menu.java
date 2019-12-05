/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

//Local imports
import controllers.GameController;
//Java io imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//JavaFX imports
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
 * @author Olly Rea
 */
public abstract class Menu {
    
    protected VBox menuLayout = new VBox(60);

    public Menu() {
        menuLayout.setVisible(false);
    }

    public VBox render() {
        return menuLayout;
    }

    public void toggle() {
        menuLayout.setVisible(!menuLayout.isVisible());
    }

    public boolean isVisible() {
        return menuLayout.isVisible();
    }
}

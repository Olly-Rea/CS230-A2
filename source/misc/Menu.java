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

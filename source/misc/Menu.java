/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

//Local imports
import controllers.GameController;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

/**
 *
 * @author Olly Rea
 */
public abstract class Menu {
    
    double scaleVal = GameController.SCALE_VAL;
    protected VBox menuLayout = new VBox(50);

    public Menu() {
        menuLayout.setVisible(false);
        menuLayout.getStylesheets().add("File:./assets/styles/menu.css");
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
    
    public void scaleMenu() {
        double menuWidth = 1380 - menuLayout.getWidth();
        double menuHeight = 1380 - menuLayout.getHeight();

        menuLayout.setPadding(new Insets((menuHeight/4.5)*scaleVal, 
                        (menuWidth/4.5)*scaleVal, 
                        (menuHeight/4.5)*scaleVal, 
                        (menuWidth/4.5)*scaleVal));
    }
}

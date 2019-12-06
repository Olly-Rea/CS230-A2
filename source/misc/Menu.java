/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

//Local imports
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

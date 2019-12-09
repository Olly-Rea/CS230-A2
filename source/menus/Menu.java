package menus;

//Local imports
import controllers.GameController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
//JavaFX imports
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * @author Olly Rea, Scott Barr
 */
public abstract class Menu {
    
    double scaleVal = GameController.SCALE_VAL;
    protected VBox menuLayout = new VBox(50);

    /**
     * Constructor for the menu superclass
     */
    public Menu() {
        menuLayout.setVisible(false);
        menuLayout.getStylesheets().add("File:./assets/styles/menu.css");
    }

    /**
     * Render method for each menu, allowing it to be displayed to the screen
     * 
     * @return the VBox containing the menu child nodes
     */
    public VBox render() {
        return menuLayout;
    }

    /**
     * Method to toggle the visibility of a menu - Fade transition for cool points
     */
    public void toggle() {
        boolean in = menuLayout.isVisible() == false;
        menuLayout.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), menuLayout);
        
        ft.setFromValue(in ? 0 : 1);
        ft.setToValue(in ? 1: 0);

        ft.play();

        ft.setOnFinished((ActionEvent e) -> {
            menuLayout.setVisible(in);
        });
    }

    /**
     * Method to get a boolean if a menu is visible or not
     * 
     * @return the boolean VBox isVisible() value
     */
    public boolean isVisible() {
        return menuLayout.isVisible();
    }
    
    /**
     * Method to scale the menus to fit in the scene window
     */
    public void scaleMenu() {
        double menuWidth = 1380 - menuLayout.getWidth();
        double menuHeight = 1380 - menuLayout.getHeight();

        menuLayout.setPadding(new Insets((menuHeight/4.5)*scaleVal, 
                        (menuWidth/4.5)*scaleVal, 
                        (menuHeight/4.5)*scaleVal, 
                        (menuWidth/4.5)*scaleVal));
    }
}

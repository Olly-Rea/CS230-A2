package menus;

//Local imports
import controllers.GameController;
import utils.FileHandler;
import misc.Profile;

//Java imports
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//JavaFX imports
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

/**
 * @author Scott Barr, Olly Rea
 */
public class LevelMenu extends Menu {

    public static final String[] levels = {"Level_1", "Level_2", "Level_3", "Level_4", "Level_5"};
    private static String MAP_DIR = "./levelfiles/";
    private static String SAVE_DIR = "./savefiles/";

    private ListView<String> mapChoices = new ListView<String>();
    private Button goButton;
    private Button backButton;
    private VBox selection = new VBox();
    private String selected = null;
    private String path = MAP_DIR;

    public LevelMenu(GameController gc) {
        // set not visible
        super();

        selection.getStyleClass().add("selection");

        // Select level button
        ImageView selectLevelButton = null;
        try {
            selectLevelButton = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/selectLevelButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("select level button path wasn't found");
        }

        // Go button
        goButton = new Button();
        // Add the button graphic and scale the button
        goButton.setGraphic(selectLevelButton);
        goButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        // Add the event handler
        goButton.setOnAction((ActionEvent e) -> {
            if (selected == null) {
                this.toggle();
                return;
            };
            gc.loadGame(path + selected + ".txt");        
        });
        goButton.setDisable(true);

        menuLayout.getChildren().add(selection);
        menuLayout.getChildren().add(goButton);

        this.scaleMenu();
    }

    /**
     * Method to add the maps to the level select ListView
     *
     * @param files the String array of filenames
     * @return the ListView of currently available maps
     */
    private ListView<String> setupMapChoice(String[] files) {
        ListView<String> maps = new ListView<>();
        maps.setItems(FXCollections.observableArrayList(files));
        maps.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        maps.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String oldVal, String val) -> {
                    selected = val;
                    goButton.setDisable(false);
                }
        );
        return maps;
    }

    /**
     * Method to load the save files of a player
     *
     * @param p the profile to check for save files in
     */
    public void loadSaves(Profile p) {
        String[] maps = getSaves(p);
        selected = null;
        path = SAVE_DIR + p.getName() + "/";
        mapChoices = setupMapChoice(maps);

        selection.getChildren().clear();
        selection.getChildren().add(mapChoices);
    }

    /**
     * Method to load the number of levels the player has currently unlocked
     *
     * @param level the current map level the player has achieved
     */
    public void loadLevels(int level) {
        String[] maps = getMaps(level);
        selected = null;
        path = MAP_DIR;
        mapChoices = setupMapChoice(maps);

        selection.getChildren().clear();
        selection.getChildren().add(mapChoices);
    }

    private String[] getMaps(int level) {
        String[] files = new String[level];
        for (int i = 0; i < level && i < levels.length; i++) {
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

    public void addBackBtn(LeaderboardMenu lbM) {
        ImageView backButtonImg = null;
        try {
            backButtonImg = new ImageView(new Image(new FileInputStream("./assets/visuals/menu/Buttons/backButton.png")));
        } catch (FileNotFoundException e) {
            System.err.println("back button path wasn't found");
        }

        // Back button
        backButton = new Button();
        // Add the button graphic and scale the button
        backButton.setGraphic(backButtonImg);
        backButton.getTransforms().add(new Scale(scaleVal, scaleVal, 0, 0));
        // Add the event handler
        backButton.setOnAction((ActionEvent e) -> {
            this.toggle();
            lbM.toggle();
        });

        // Add the back button to the menuLayout
        menuLayout.getChildren().add(backButton);
        menuLayout.setStyle("-fx-transform-y: -120px;");
    }
}

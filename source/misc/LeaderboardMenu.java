package misc;

import java.io.File;
import controllers.GameController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import utils.FileHandler;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Label;

import misc.Profile;

import controllers.GameController;
import utils.FileHandler;

public class LeaderboardMenu extends Menu {

	private Leaderboard leaderboard;
	private ArrayList<String> lbList;
	private String levelName;
    private VBox selection = new VBox();
    private Label first;
	private Label second;
	private Label third;
	private Label player;

	public LeaderboardMenu(GameController gc) {
		// set not visible
		super();

		
		ArrayList<String> lbList = new ArrayList<String>(); 
		lbList = gc.getLeaderboard();
		
		Label first = new Label("NULL");
		Label second = new Label("NULL");
		Label third = new Label("NULL");
		selection.getChildren().add(first);
		selection.getChildren().add(second);
		selection.getChildren().add(third);
		
		Label player = new Label();

		Button nextLevel = new Button("Proceed to next level");
		nextLevel.setOnAction((ActionEvent e) -> {
			// Close + start next level
		});

		Button back = new Button("Return to level screen");
		back.setOnAction((ActionEvent e) -> {
			// Return to map select
		});
		
		menuLayout.getChildren().add(selection);
		menuLayout.getChildren().add(back);
		menuLayout.getChildren().add(nextLevel);
		
	}
	
	public void loadLeaderboard(int currentMapNum, GameController gc) {
		ArrayList<String> lbList = new ArrayList<String>(); 
		lbList = gc.getLeaderboard();
        selection.getChildren().clear();
		Label first = new Label(lbList.get(0));
		Label second = new Label(lbList.get(1));
		Label third = new Label(lbList.get(2));
		selection.getChildren().add(first);
		selection.getChildren().add(second);
		selection.getChildren().add(third);
		selection.getChildren().add(player);
		
	}
	
	public void displayPlayer(Profile profile, int time) {
		String playerScore = profile.getName() + " + " + time;
		Label player = new Label(playerScore);
		this.player = player;
	}
}

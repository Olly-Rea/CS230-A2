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
	private int levelNum;

	public LeaderboardMenu(GameController gc) {
		// set not visible
		super();

		// get level?
		ArrayList<String> lbList = new ArrayList<String>(); 
		lbList = gc.getLeaderboard();
//		lbList.add("Dave_1");
//		lbList.add("Dave_2");
//		lbList.add("Dave_3");
		
		Label first = new Label(lbList.get(0));
		Label second = new Label(lbList.get(1));
		Label third = new Label(lbList.get(2));
		//Label player = new Label(lbList.get(0));

		Button nextLevel = new Button("Proceed to next level");
		nextLevel.setOnAction((ActionEvent e) -> {
			// Close + start next level
		});

		Button back = new Button("Return to level screen");
		back.setOnAction((ActionEvent e) -> {
			// Return to map select
		});
		
		menuLayout.getChildren().add(first);
		menuLayout.getChildren().add(second);
		menuLayout.getChildren().add(third);
		menuLayout.getChildren().add(back);
		menuLayout.getChildren().add(nextLevel);

	}
}

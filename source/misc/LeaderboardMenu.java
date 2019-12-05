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

import misc.Profile;

import controllers.GameController;
import utils.FileHandler;

public class LeaderboardMenu extends Menu {
	
	private Leaderboard Leaderboard;
	private int levelNum;

	public LeaderboardMenu(GameController gc) {
		 // set not visible
		super();
		
		// How do I get level?
		//gc.showLeaderboard(level);
		
		Button nextLevel = new Button("Proceed to next level");
		
		
		Button back = new Button("Return to level screen");
		
		
	}

}

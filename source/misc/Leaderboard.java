package misc;

/**
 * Loads profile list and shows the top times of the level.
 *
 * @author Alexandros
 */
import java.io.File;

import utils.FileHandler;

public class Leaderboard {

	public static final String LEADERBOARD_DIR = "./leaderboards/";
	private int[] topScores;
	private String[] topNames;
	private String path;

	public Leaderboard(String path) {
		// Populate leaderboard with info from file
		this.path = path;
		FileHandler reader = new FileHandler(path);
		int[] topScores = new int[3];
		String[] topNames = new String[3];
		String[] leaderboard = new String[3];
		for (int i = 0; i < 3; i++) {
			if (!(reader.hasNext())) {
				topNames[i] = "-----";
				topScores[i] = 9999;
			} else {
				String line = reader.nextLine();
				String[] nameScore = line.split(" : ");
				String name = nameScore[0];
				String scoreString = nameScore[1];
				int score = Integer.parseInt(scoreString);
				topNames[i] = name;
				topScores[i] = score;
			}
		}
		this.topScores = topScores;
		this.topNames = topNames;
		String[] writeToFile = new String[3];
		for (int i = 0; i < 3; i++) {
			writeToFile[i] = topNames[i] + " : " + topScores[i];
		}
		reader.writeFile(path, writeToFile, false);
	}

	public void addTime(Profile profile, int time) {
		if (time < topScores[0]) {
			topScores[2] = topScores[1];
			topScores[1] = topScores[0];
			topScores[0] = time;
			topNames[2] = topNames[1];
			topNames[1] = topNames[0];
			topNames[0] = profile.getName();
		} else if (time < topScores[1]) {
			topScores[2] = topScores[1];
			topScores[1] = time;
			topNames[2] = topNames[1];
			topNames[1] = profile.getName();
		} else if (time < topScores[2]) {
			topScores[2] = time;
			topNames[2] = profile.getName();
		}
		FileHandler writer = new FileHandler(path);
		String[] newBoard = new String[3];
		for(int i = 0; i < 3;i++) {
			newBoard[i] = topNames[i] + " : " + topScores[i];
		}
		writer.writeFile(path, newBoard, false);
	}

	public String[] displayBoard() {
		String[] lb = new String[3];
		for (int i = 0; i < 3; i++) {
			if (topScores[i] == 9999) {
				lb[i] = topNames[i] + " : " + "_";
			} else {
				lb[i] = topNames[i] + " : " + topScores[i];
			}
		}
		return lb;
	}
}

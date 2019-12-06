package misc;

import java.io.File;
import java.util.ArrayList;

import utils.FileHandler;

/**
 * Loads profile list and shows the top times of the level.
 *
 * @author Alexandros, Daniel Clenaghan
 * @version 1.0
 */
public class Leaderboard {

	public static final String LEADERBOARD_DIR = "./leaderboards/";
	private int[] topScores;
	private String[] topNames;
	private String path;
	private int maxValue = 2147483647;

	/**
	 * Create and populate a leaderboard with info from a file
	 * @param path
	 */
	public Leaderboard(String path) {
		this.path = path;
		FileHandler reader = new FileHandler(path);
		int[] topScores = new int[3];
		String[] topNames = new String[3];
		for (int i = 0; i < 3; i++) {
			if (!(reader.hasNext())) {
				topNames[i] = "-----";
				topScores[i] = maxValue;
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

	/**
	 * Check if a time is lower than those already in the leaderboard
	 * @param profile
	 * @param time
	 */
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

	/**
	 * Output the leaderboard as a string array
	 * @return array
	 */
	public ArrayList<String> displayBoard() {
		ArrayList<String> lb = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			if (topScores[i] == maxValue) {
				lb.add(topNames[i] + " : " + "_");
			} else {
				lb.add(topNames[i] + " : " + topScores[i]);
			}
		}
		return lb;
	}
}

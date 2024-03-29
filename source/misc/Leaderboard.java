package misc;

import java.util.ArrayList;

import utils.FileHandler;

/**
 * Loads profile list and shows the top times of the level.
 *
 * @author Alexandros Melenikos, Daniel Clenaghan
 * @version 1.0
 */
public class Leaderboard {

	public static final String LEADERBOARD_DIR = "./leaderboards/";

	private double[] topScores;
	private String[] topNames;
	private String path;
	private int maxValue = 2147483647;

	/**
	 * Create and populate a leaderboard with info from a file
	 * 
	 * @param path The path to the leaderboard file
	 */
	public Leaderboard(String path) {
		this.path = path;
		FileHandler reader = new FileHandler(path);
		double[] topScores = new double[3];
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
				double score = Double.parseDouble(scoreString);
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
		FileHandler.writeFile(path, writeToFile, false);
	}

	/**
	 * Check if a time is lower than those already in the leaderboard
	 * 
	 * @param profile The profile being added to the leaderboard
	 * @param time The time the profile achieved
	 */
	public void addTime(Profile profile, double time) {
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
		String[] newBoard = new String[3];
		for (int i = 0; i < 3; i++) {
			newBoard[i] = topNames[i] + " : " + topScores[i];
		}
		FileHandler.writeFile(path, newBoard, false);
	}

	/**
	 * Output the leaderboard as a string array
	 * 
	 * @return Outputs the ArrayList of the strings from the leaderboard file
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

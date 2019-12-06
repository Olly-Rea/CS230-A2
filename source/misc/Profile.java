package misc;

import java.io.File;
import java.util.Scanner;

import utils.FileHandler;


/**
 * Contains a name and the highest level completed by profile.
 *
 * @author Alexandros, Daniel Clenaghan, Scott Barr
 * @version 1.0
 */
public class Profile {

    public static final String PROFILE_PATH = "./savefiles/profiles.txt";
    private static final String SAVE_DIR = "./savefiles/";

    private String name;
    private int highest;

    /**
     * Instantiate a profile
     * @param name
     * @param highestLevel
     */
    public Profile(String name, int highestLevel) {
    	this.name = name;
    	this.highest = highestLevel;
	}

	//TODO:
	public static Profile fromLine(String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(":");
		String name = sc.next();
		return sc.hasNext() ? new Profile(name, sc.nextInt()) : new Profile(name, 1);
	}

    /**
     * @return name
     */
    public String getName() {
    	return name;
    }

    /**
     * @return highest
     */
    public int getLevel() {
    	return highest;
    }

    /**
     * Return the profile information as a string
     */
	@Override
	public String toString() {
		return String.format("%s - Highest Level : %d", name, highest);
	}

	/**
	 * Saves the profile to a text file
	 */
    public void saveProfile() {
		String[] profile = {name+":"+highest};
		new File(SAVE_DIR + name + "/").mkdir();
		FileHandler.writeFile(PROFILE_PATH, profile, true);
	}

	/**
	 * TODO:
	 */
	public void deleteProfile() {
		FileHandler fh = new FileHandler(PROFILE_PATH);
		String[] profileLines = fh.readLines();
		Profile[] profiles = new Profile[profileLines.length];

		for (int i = 0; i < profiles.length; i++) {
			Profile check = fromLine(profileLines[i]);
			profiles[i] = this.equals(check) ? null : check;
		}

		FileHandler.clearFile(PROFILE_PATH);
		for (Profile p : profiles) {
			if (p != null) {
				p.saveProfile();
			}
		}
	}

	//TODO:
	@Override
	public String toString() {
		return String.format("%s - Highest Level : %d", name, highest);
	}


	//TODO:
	@Override
	public boolean equals(Object other) {
		if (other instanceof Profile) {
			return ((Profile)other).getName().equals(name) ? true : false;
		} else return false;
	}
}

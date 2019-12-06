package misc;

/**
 * Contains a name and the highest level completed by profile.
 *
	//TODO:
 * @author Alexandros
 */
import java.io.File;
import java.util.Scanner;

import utils.FileHandler;

public class Profile {

	public static final String PROFILE_PATH = "./savefiles/profiles.txt";
    private static final String SAVE_DIR = "./savefiles/";

    private String name;
    private int highest;
    
	//TODO:
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

	//TODO:
    public String getName() {
    	return name;
    }
    
	//TODO:
    public int getLevel() {
    	return highest;
    }
	


	//TODO:
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

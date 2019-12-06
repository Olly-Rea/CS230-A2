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
		FileHandler.writeFile(PROFILE_PATH, profile, true);
	}
	
    /**
     * Creates a Profile from a line of text
     * @param line
     * @return
     */
	public static Profile fromLine(String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(":");
		String name = sc.next();
		return sc.hasNext() ? new Profile(name, sc.nextInt()) : new Profile(name, 0);
	}
}

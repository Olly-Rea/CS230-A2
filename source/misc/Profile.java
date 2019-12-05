package misc;

/**
 * Contains a name and the highest level completed by profile.
 *
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
    
    public Profile(String name, int highestLevel) {
    	this.name = name;
    	this.highest = highestLevel;
    }

    public String getName() {
    	return name;
    }
    
    public int getLevel() {
    	return highest;
    }
	
	@Override
	public String toString() {
		return String.format("%s - Highest Level : %d", name, highest);
	}

    public void saveProfile() {
		String[] profile = {name+":"+highest};
        new File(SAVE_DIR + name + "/").mkdir();
		FileHandler.writeFile(PROFILE_PATH, profile, true);
	}
	
	public static Profile fromLine(String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(":");
		String name = sc.next();
		return sc.hasNext() ? new Profile(name, sc.nextInt()) : new Profile(name, 1);
	}
}

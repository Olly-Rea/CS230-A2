package misc;

/**
 * Contains a name and the highest level completed by profile.
 *
 * @author Alexandros
 */
import java.io.File;
import utils.FileHandler;

public class Profile {

    private String name;
    private int highest;
    
    public Profile(String name, int highestLevel) {
    	this.name = name;
    	this.highest = highestLevel;
    }

    public Profile(String name, int highestLevel,String path) {
    	this.name = name;
    	this.highest = highestLevel;
    	saveProfile(path);
    }
    
    public String getName() {
    	return name;
    }
    
    public int getLevel() {
    	return highest;
    }
    

    private void saveProfile(String path) {
    	// Gets the total number of Profiles stored
    	FileHandler counter = new FileHandler(path);
		int arraySize = 0;
		while (counter.hasNext()) {
			arraySize++;
			counter.nextLine();
		}
		
		FileHandler writer = new FileHandler(path);
		String[] profileList = new String[arraySize + 1];
		String profileInfo = name;
		for (int i = 0; i < profileList.length; i++) {
			profileList[i] = writer.nextLine();
			if (profileList[i] == "") {
				profileList[i] = profileInfo;
			}
			
		}
    	profileList[(profileList.length -1)] = profileInfo;
    	writer.saveFile(path, profileList);
    	
    }
}

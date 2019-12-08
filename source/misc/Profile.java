package misc;

import java.io.File;
import java.util.Scanner;

import utils.FileHandler;

/**
 * Contains a name and the highest level completed by profile.
 *
 * @author Alexandros Melenikos, Daniel Clenaghan, Scott Barr
 * @version 1.0
 */
public class Profile {

    public static final String PROFILE_PATH = "./savefiles/profiles.txt";
    private static final String SAVE_DIR = "./savefiles/";

    private String name;
    private int highest;

    /**
     * Instantiate a profile
     *
     * @param name         The name given to the profile
     * @param highestLevel The highest level achieved of the profile
     */
    public Profile(String name, int highestLevel) {
        this.name = name;
        this.highest = highestLevel;
    }

    /**
     * Creates a new profile from a given line using a scanner.
     * 
     * @param line The string of text being used to create a profile object
     * @return The new Profile that has been created.
     */
    public static Profile fromLine(String line) {
        Scanner sc = new Scanner(line);
        sc.useDelimiter(":");
        String name = sc.next();
        return sc.hasNext() ? new Profile(name, sc.nextInt()) : new Profile(name, 1);
    }

    /**
     * Returns the name of the profile.
     * 
     * @return The name of the profile.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the highest level achieved by the profile.
     * 
     * @return The highest level achieved by the profile.
     */
    public int getLevel() {
        return highest;
    }

    /**
     * Converts the profile into a string showing the name and the highest level.
     * 
     * @return A string of the Profile to be displayed
     */
    @Override
    public String toString() {
        return String.format("%s - Highest Level : %d", name, highest);
    }

    /**
     * Saves the profile to a text file
     */
    public void saveProfile() {
        String[] profile = { name + ":" + highest };
        new File(SAVE_DIR + name + "/").mkdir();
        FileHandler.writeFile(PROFILE_PATH, profile, true);
    }

    /**
     * Method to delete the profile from the profiles.txt at PROFILE_PATH but keep
     * the other profiles.
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

    /**
     * Increments the highest level by 1 if the level is higher than the current
     * highets level achieved by the profile.
     * 
     * @param level The level to check whether it is higher than the current highest.
     */
    public void incLevel(int level) {
        highest = level >= highest ? level + 1 : level;
    }

    /**
     * Method to check if two profiles are equal by checking if their name is the same.
     * 
     * @param other The other object being used to check if the profiles are the same.
     * @return true if the two profiles have the same name.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Profile) {
            return ((Profile) other).getName().equals(name) ? true : false;
        } else {
            return false;
        }
    }
}

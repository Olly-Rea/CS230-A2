package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

/**
 * Takes a text file and reads it. If it's null it throws an exception and
 * terminates the program. Also, it reads every single line and stops when it
 * finds a null one.
 *
 * @author
 */
public class FileHandler {

    private BufferedReader reader;

    /**
     * Reads a file and if it's unable to open it, it throws an exception,
     * prints a message and exits the program.
     *
     * @param path The local path of that file.
     */
    public FileHandler(String path) {
        try {
            FileReader fReader = new FileReader(path);
            reader = new BufferedReader(fReader);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + path);
            System.exit(0);
        }
    }

    /**
     * Reads every line of the file. If it finds an empty line it returns empty
     * "".
     *
     * @return Returns the line of the file or an empty String.
     */
    public String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Method to save a file to a certain path and specific lines to write.
     * 
     * @param path Path to the file being created/written to
     * @param lines the lines of text to be written i nthe file
     * @return True if the file was saved correctly, false otherwise
     */
    public static boolean saveFile(String path, String[] lines) {
        BufferedWriter writer = null;
        File file = new File(path);
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < lines.length; i++) {
                writer.write(lines[i] + (i!=lines.length-1?"\n":""));
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}

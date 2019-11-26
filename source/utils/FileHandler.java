package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

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

}

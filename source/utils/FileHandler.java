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
     * Checks if the reader is at the end of file.
     *
     * @return false if reader has reached the end of the file.
     */
    public boolean hasNext() {
        try {
            reader.mark(1);
            int test = reader.read();
            reader.reset();
            return test > 0 ? true : false;
        } catch (IOException e) {
            return false; // TODO: Handle
        }
    }

    /**
     * Method to save a file to a certain path and specific lines to write.
     *
     * @param path Path to the file being created/written to
     * @param lines the lines of text to be written i nthe file
     * @return True if the file was saved correctly, false otherwise
     */
    public static void writeFile(String path, String[] lines, boolean append, boolean endOfFile) {
        BufferedWriter writer = null;
        File file = new File(path);
        try {
            writer = new BufferedWriter(new FileWriter(file, append));
            for (int i = 0; i < lines.length; i++) {
                // writer.write(lines[i]);
                writer.write(lines[i] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

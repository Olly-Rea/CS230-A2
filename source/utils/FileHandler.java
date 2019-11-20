package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class FileHandler {
	private BufferedReader reader;

	public FileHandler(String path) {

		try {
			FileReader fReader = new FileReader(path);
			reader = new BufferedReader(fReader);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + path);
			System.exit(0);
		}
	}

	public String nextLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			return "";
		}
	}

}

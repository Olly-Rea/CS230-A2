package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferReader;
import java.util.Scanner;

public class FileHandler {
  private BufferReader reader;

  public FileHandler(String path) {

    try {
      FileReader fReader = new FileReader(path);
      reader = new BufferReader(fReader);
    }
    catch (FileNotFoundException e) {
      System.out.println ("Cannot open " + filename);
      System.exit (0);
    }

  }

}

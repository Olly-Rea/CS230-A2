package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Handles http requests.
 *
 * @author
 */
public class RequestHandler {

    /**
     * Sends a get request to the url with specific query parameters.
     *
     * @param uri used to create a new url
     * @param params
     */
    public static String get(String uri) {
        try {
            URL url = new URL(uri);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader res = new BufferedReader(new InputStreamReader(con.getInputStream()));
            return res.readLine();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }

        return null;
    }
    /**
    * Deciphers the puzzle taken from the Url
    * @param puzzle the puzzle to be deciphered
    */
    public static String decipher(String puzzle) {
        char[] chars = puzzle.toCharArray();
        int val = 1;
        for (int i = 0; i < chars.length; i++) {
            int ord = (int) chars[i];
            ord = ord + val;
            ord = ord > 90 ? 65 : ord;
            ord = ord < 65 ? 90 : ord;
            chars[i] = (char) ord;
            val *= -1;
        }
        return new String(chars);
    }
}

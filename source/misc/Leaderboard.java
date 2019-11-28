package misc;

/**
 * Loads profile list and shows the top times of the level.
 *
 * @author Alexandros
 */
import java.io.File;

public class Leaderboard {

    int levelNumber;
    Profile best;

    public void Leaderboard() {

    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int loadTimes(File file) {
        return 0;
    }
}

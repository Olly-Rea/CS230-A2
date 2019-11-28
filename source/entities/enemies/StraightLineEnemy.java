package entities.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cells.Cell;
import cells.CellType;
import controllers.MapController;
import entities.Enemy;
import utils.Direction;
import utils.Vector;
import javafx.scene.image.Image;

public class StraightLineEnemy extends Enemy {

    private static final String ASSET_PATH = "./assets/visuals/entities/wallfollower.png";
    private static Image image;

    static {
        try {
            image = new Image(new FileInputStream(ASSET_PATH));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("StraightLineEnemy image path not found");
        }
    }

    private Direction dir;

    /**
     * Straight line.
     *
     * @param vector the position of th straight line enemy
     * @param dir The direction the enemy is facing initially
     */
    public StraightLineEnemy(Vector pos, Direction dir) {
        super(pos);
        this.dir = dir;
    }

    /**
     * The algorithm used by the enemy to calculate next move, checks whether
     * the next cell in the direction it is facing is a wall, if so it turns
     * around
     *
     * @param map the map
     */
    public void algorithm(MapController map) {
        Cell next = map.getNextCell(pos, dir);
        if (next.getType() == CellType.WALL) {
            dir = dir.cw().cw();
        }

        this.pos.add(dir);
    }

    /**
     * Renders the enemy
     */
    public void render() {

    }
}

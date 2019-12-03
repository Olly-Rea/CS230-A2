package entities.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cells.Cell;
import cells.Ground;
import controllers.EntityController;
import controllers.MapController;
import entities.Enemy;
import entities.Player;
import utils.Direction;
import utils.Vector;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StraightLineEnemy extends Enemy {

    private static final String ASSET_PATH = "./assets/visuals/entities/enemies/straightLine.png";
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
    public StraightLineEnemy(Vector pos, Player player, Direction dir) {
        super(pos, player);
        this.dir = dir;
    }

    /**
     * The algorithm used by the enemy to calculate next move, checks whether
     * the next cell in the direction it is facing is a wall, if so it turns
     * around
     *
     * @param map the map
     */
    public void algorithm(MapController map, EntityController ec) {
        Cell next = map.getNextCell(pos, dir);
        boolean existsEntity = ec.entityPresent(pos, dir);
        if (!(next instanceof Ground) || existsEntity) {
            dir = dir.cw().cw();
        }

        if (!pos.equals(player.getPos())) {
            this.pos.add(dir);
        }
    }
    
    public String export() {
        return String.format("SL %d %d %s", pos.getX(), pos.getY(), dir);
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        return new ImageView(image);
    }
}

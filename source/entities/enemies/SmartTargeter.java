package entities.enemies;

//Local imports
import cells.Cell;
import cells.Ground;
import controllers.EntityController;
import controllers.MapController;
import entities.Enemy;
import entities.Player;
import utils.Direction;
import utils.Vector;

//Java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

//JavaFX imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Subclass of the Enemy class; SmartTargeter SmartTargeter class which always
 * takes the path which leads to the Player.
 *
 * @author Scott Barr
 */
public class SmartTargeter extends Enemy {

    private Image image;

    private Direction dir;

    public SmartTargeter(Vector pos, Player player) {
        super(pos, player);
    }

    /**
     * Creates a distance grid which flood fills from the players position until
     * every possible connecting cell is filled.
     *
     * @param map The map containing the cells
     * @return A 2d Array of integers the same size as the map filled with
     * distances from the Players position.
     */
    private Integer[][] makeDistGrid(MapController map, EntityController ec) {
        // Initialise Variables
        LinkedList<Cell> queue = new LinkedList<>();
        Integer[][] distGrid = new Integer[map.height][map.width];
        Vector playerPos = player.getPos();

        // Set position at player to 0;
        distGrid[playerPos.getY()][playerPos.getX()] = 0;
        queue.add(map.getCell(playerPos.getX(), playerPos.getY()));

        // If queue is not empty then there are still connecting cells left to be filled
        while (!queue.isEmpty()) {
            Cell q = queue.poll(); // q = the next cell in the queue
            for (Direction d : Direction.values()) { // For all directions (UP/RIGHT/DOWN/LEFT)
                int x = q.getPos().getX();
                int y = q.getPos().getY();
                Cell next = map.getNextCell(q.getPos(), d); // get the next cell
                boolean existsEntity = ec.entityPresent(pos, d);
                if (next instanceof Ground && !existsEntity) { // if the cell is of type GROUND
                    int dist = distGrid[y][x] + 1; // distance is incremented by 1
                    // if distGrid at next position is empty then add it to the queue & set to dist
                    Integer val = distGrid[next.getPos().getY()][next.getPos().getX()];
                    if (val == null) {
                        distGrid[next.getPos().getY()][next.getPos().getX()] = dist;
                        queue.add(next);
                    }
                }
            }
        }

        return distGrid;
    }

    public void algorithm(MapController map, EntityController ec) {
        // Generate the distance grid and set minDist to null and dir to null
        Integer[][] distGrid = makeDistGrid(map, ec);
        Integer minDist = null;
        dir = null;

        // If the distGrid at the enemies position is 0 then do not move
        if (!player.getPos().equals(pos) && distGrid[pos.getY()][pos.getX()] != null) {
            for (Direction d : Direction.values()) { // for all Directions (UP, RIGHT, DOWN, LEFT)
                Cell next = map.getNextCell(new Vector(pos.getX(), pos.getY()), d); // get the next cell in that direction
                boolean existsEntity = ec.entityPresent(pos, d);
                if (next instanceof Ground && !existsEntity) { // Confirm it's a ground cell
                    Integer dist = distGrid[pos.getY() + d.Y][pos.getX() + d.X]; // check the distance at that cell in
                    // distGrid
                    // If minDist is null and dist isn't null then set minDist to be dist
                    // and set facing direction of the enemy to be the direction d
                    if (minDist == null && dist != null) {
                        minDist = dist;
                        dir = d;
                    } else { // otherwise if dist is smaller than minDist set the new direction as d
                        if (dist < minDist) {
                            minDist = dist;
                            dir = d;
                        }
                    }
                }
            }
        }

        if (dir != null) { // if dir is null then do not move.
            pos.add(dir); // otherwise add the dir to the positon.
        } else {
            ArrayList<Direction> validMoves = new ArrayList<Direction>();
            if (checkValid(Direction.DOWN, map, ec)) {
                validMoves.add(Direction.DOWN);
            }
            if (checkValid(Direction.UP, map, ec)) {
                validMoves.add(Direction.UP);
            }
            if (checkValid(Direction.LEFT, map, ec)) {
                validMoves.add(Direction.LEFT);
            }
            if (checkValid(Direction.RIGHT, map, ec)) {
                validMoves.add(Direction.RIGHT);
            }
            Random random = new Random();
            pos.add(validMoves.get(random.nextInt(validMoves.size())));

        }
    }

    private Boolean checkValid(Direction dir, MapController map, EntityController ec) {
        Cell next = map.getNextCell(pos, dir);
        boolean existsEntity = ec.entityPresent(pos, dir);
        if (!(next instanceof Ground) || existsEntity) {
            return false;
        }
        return true;
    }

    public String export() {
        return String.format("%d %d ST", pos.getX(), pos.getY());
    }

    /**
     * Renders the Enemy to the screen
     */
    public ImageView render() {
        String currAsset = ASSET_PATH + "Smart/";
        if (dir != null) {
            switch (dir) {
                case UP:
                    currAsset += "Hellhound_Up";
                    break;
                case DOWN:
                    currAsset += "Hellhound_Down";
                    break;
                case LEFT:
                    currAsset += "Hellhound_Left";
                    break;
                case RIGHT:
                    currAsset += "Hellhound_Right";
                    break;
            }
        } else {
            currAsset += "Hellhound_Right";
        }

        try {
            image = new Image(new FileInputStream(currAsset + ".png"));
        } catch (FileNotFoundException e) {
            image = null;
            System.err.println("SmartTargeter image path not found");
        }

        return new ImageView(image);
    }
}

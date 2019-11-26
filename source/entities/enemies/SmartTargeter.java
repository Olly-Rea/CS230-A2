package entities.enemies;

import java.util.LinkedList;

import cells.Cell;
import cells.CellType;
import controllers.MapController;
import entities.Enemy;
import entities.Player;
import utils.Direction;
import utils.Vector;
import javafx.scene.image.Image;

/**
 * SmartTargeter class which always takes the path which leads to the Player.
 * 
 * @author Scott Barr
 */
public class SmartTargeter extends Enemy {
    
	private static final Image SPRITE = new Image("...");

    private Direction dir;
    private Player player;

    public SmartTargeter(Vector pos, Player player) {
        super(pos);
        this.player = player;
    }

    /**
     * 
     */
    public void render() {
    }

    /**
     * Creates a distance grid which flood fills from the players position until
     * every possible connecting cell is filled.
     * 
     * @param map The map containing the cells
     * @return A 2d Array of integers the same size as the map filled with distances
     *         from the Players position.
     */
    private Integer[][] makeDistGrid(MapController map) {
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
                Cell next = map.getNextCell(new Vector(x, y), d); // get the next cell

                if (next.getType() == CellType.GROUND) { // if the cell is of type GROUND
                    int dist = distGrid[y][x] + 1; // distance is incremented by 1
                    // if distGrid at next position is empty then add it to the queue & set to dist
                    Integer val = distGrid[next.getPos().getY()][next.getPos().getX()];
                    if (val == null) {
                        val = dist;
                        queue.add(next);
                    }
                }
            }
        }

        return distGrid;
    }

    public void algorithm(MapController map) {
        // Generate the distance grid and set minDist to null and dir to null
        Integer[][] distGrid = makeDistGrid(map);
        Integer minDist = null;
        dir = null;

        // If the distGrid at the enemies position is 0 then do not move
        if (distGrid[pos.getY()][pos.getX()] != 0) {
            for (Direction d : Direction.values()) { // for all Directions (UP, RIGHT, DOWN, LEFT)
                Cell next = map.getNextCell(new Vector(pos.getX(), pos.getY()), d); // get the next cell in that direction
                if (next.getType() == CellType.GROUND) { // Confirm it's a ground cell
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
        }
    }
}
package utils;

/**
 * This is a simple class designed to store the x and y location of an object in
 * the map
 * 
 * @version 1.0
 * @author Daniel Clenaghan
 */
public class Vector {

    // The x and y coordinates
    private int x;
    private int y;

    /**
     * Create a vector with given coordinates
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x The x value of the Vector
     */
    public int getX() {
        return x;
    }

    /**
     * @return y The y value of the Vector
     */
    public int getY() {
        return y;
    }

    /**
     * Change the vector using a given direction
     * 
     * @param d adds a direction to the vector based off the directions values
     */
    public void add(Direction d) {
        this.x += d.X;
        this.y += d.Y;
    }

    /**
     * Check if two vectors are equal
     * 
     * @param other the vector to be checked
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector) {
            Vector v = (Vector) other;
            return (v.x == x && v.y == y);
        }
        return false;
    }
}

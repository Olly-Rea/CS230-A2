package utils;

/**
 * Utilty enum for Directions WallFollower class can rotate direction when
 * required through {@code Direction.cw()} or {@code Direction.acw()}.
 * 
 * @author Scott Barr
 * @version 1.0
 */
public enum Direction {
    UP(0, -1) {
        @Override
        public Direction cw() {
            return RIGHT;
        }

        public Direction acw() {
            return LEFT;
        }

    },
    RIGHT(1, 0) {
        @Override
        public Direction cw() {
            return DOWN;
        }

        public Direction acw() {
            return UP;
        }
    },
    DOWN(0, 1) {
        @Override
        public Direction cw() {
            return LEFT;
        }

        public Direction acw() {
            return RIGHT;
        }
    },
    LEFT(-1, 0) {
        @Override
        public Direction cw() {
            return UP;
        }

        public Direction acw() {
            return DOWN;
        }
    };

    public final int X, Y;

    private Direction(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public abstract Direction cw();
    public abstract Direction acw();
}

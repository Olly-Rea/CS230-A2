package utils;

/**
 * Utilty enum for Directions WallFollower class can rotate direction when
 * required through {@code Direction.cw()} or {@code Direction.acw()}.
 */
public enum Direction {
    UP {
        @Override
        public Direction cw() {
            return RIGHT;
        }

        public Direction acw() {
            return LEFT;
        }

    },

    RIGHT {
        @Override
        public Direction cw() {
            return DOWN;
        }

        public Direction acw() {
            return UP;
        }
    },

    DOWN {
        @Override
        public Direction cw() {
            return LEFT;
        }

        public Direction acw() {
            return RIGHT;
        }
    },

    LEFT {
        @Override
        public Direction cw() {
            return UP;
        }

        public Direction acw() {
            return DOWN;
        }
    };

    public abstract Direction cw();
    public abstract Direction acw();
}
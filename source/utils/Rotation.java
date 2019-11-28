package utils;

/**
 * Needed to find whether the wall follower's rotation is clockwise or anti
 * clockwise
 *
 * @author Scott
 */
public enum Rotation {
    CW {
        @Override
        public Rotation reverse() {
            return ACW;
        }
    },
    ACW {
        @Override
        public Rotation reverse() {
            return CW;
        }
    };

    public abstract Rotation reverse();
}

package utils;

/**
 * Needed to find whether the wall follower's rotation is clockwise or anti
 * clockwise
 *
 * @author Scott Barr
 * @version 1.0
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

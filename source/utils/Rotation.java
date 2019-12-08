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

    /**
     * Reverses the rotation from CW to ACW or ACW to CW.
     * 
     * @return the opposite Rotation.
     */
    public abstract Rotation reverse();
}

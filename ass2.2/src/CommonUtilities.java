// 329233472 yasmin haddad

import java.util.Random;
import java.awt.Color;


/**
 * common utilities for the project.
 */
public class CommonUtilities {
    private static final double COMPARISON_THRESHOLD = 0.0000001;
    /**
     * checks if 2 doubles are equal by comparing them with a threshold.
     * @param a first double
     * @param b second double
     * @return true if the doubles are equal, false otherwise
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < COMPARISON_THRESHOLD;
    }

    /**
     * Generates a random color.
     * @return a random Color object.
     */
    public static Color generateRandomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));
    }
}

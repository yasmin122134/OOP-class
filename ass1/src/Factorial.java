// 329233472 yasmin haddad

/**
 * provides methods to calculate the factorial of a number.
 * It calculates the factorial using an iterative and recursive approaches.
 * @author yasmin haddad
 * @version 1.0
 * @since 2020-03-03
 */
public class Factorial {

    /**
     * calculates n factorial iteratively.
     * @param n long number to calculate factorial of.
     * @return long factorial of n (n!). */
    public static long factorialIter(long n) {
        long result = 1;
        // multiply all numbers from n to 1 iteratively
        for (long i = n; i > 1; i--) {
            result *= i;
        }
        return result;
    }

    /**
     * calculates n factorial recursively.
     * @param n long number to calculate factorial of.
     * @return long factorial of n (n!). */
    public static long factorialRecursive(long n) {
        // base case: 0! and 1! are both 1
        if (n == 0 || n == 1) {
            return 1;
        }
        // multiply n by the factorial of n - 1
        return n * factorialRecursive(n - 1);
    }

    /**
     * main method.
     *
     * <p>takes in a long number from the command line.
     *     it prints out its factorial in two ways:
     *     iteratively and recursively.
     *     if no number is given, 0 is used.
     * @param args String array of command line arguments.
     */
    public static void main(String[] args) {
        // convert the first argument to a long
        long n = 0;
        if (args.length > 0) {
            n = Long.parseLong(args[0]);
        }
        System.out.println("recursive: " + factorialRecursive(n));
        System.out.println("iterative: " + factorialIter(n));
    }
}

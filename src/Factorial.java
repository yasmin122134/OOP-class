// 329233472 yasmin haddad

/**
 * 
 */
public class Factorial {
    public static long factorialIter(long n) {
        long result = 1;
        for (long i = n; i > 1; i--) {
            result *= i;
        }
        return result;
    }

    public static long factorialRecursive(long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    public static void main(String[] args) {
        long n = args.length > 0 ? Long.parseLong(args[0]) : 0;

        System.out.println("recursive: " + factorialRecursive(n));
        System.out.println("iterative: " + factorialIter(n));
    }
}

// 329233472 yasmin haddad

/**
 * provides methods to describe an array of ints.
 * it finds the minimum, maximum, and average of the numbers in the array.
 * @author yasmin haddad
 * @version 1.0
 * @since 2020-03-03
 */
public class DescribeNumbers {

    /**
     * converts an array of strings to an array of ints.
     * @param numbers String array of numbers.
     * @return int array of numbers.
     */

    public static int[] stringsToInts(String[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            // convert each string to an int
            result[i] = Integer.parseInt(numbers[i]);
        }
        return result;

    }
    /**
     * finds the minimum number in an array of ints.
     * @param numbers int array of numbers.
     * @return int minimum number in the array.
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int number : numbers) {
            // Update the minimum if a smaller number is found
            if (min > number) {
                min = number;
            }
        }
        return min;
    }
    /**
     * finds the maximum number in an array of ints.
     * @param numbers int array of numbers.
     * @return int maximum number in the array.
     */
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int number : numbers) {
            // Update the maximum if a larger number is found
            if (max < number) {
                max = number;
            }
        }
        return max;
    }
    /**
     * finds the average of an array of ints.
     * @param numbers int array of numbers.
     * @return float average of the numbers in the array.
     */
    public static float avg(int[] numbers) {
        float sum = 0;
        // add up all the numbers
        for (int number : numbers) {
            sum += number;
        }
        // calculate and return the average
        return sum / numbers.length;
    }

    /**
     * main method.
     *
     * <p>takes in a list of numbers from the command line as an array of Strings.
     *     it prints out the minimum, maximum, and average of the numbers.
     * @param args String array of command line arguments.
     */
    public static void main(String[] args) {
        // convert the arguments to an array of ints
        int[] numbers = stringsToInts(args);

        // print out the results
        System.out.println("min: " + min(numbers));
        System.out.println("max: " + max(numbers));
        System.out.println("avg: " + avg(numbers));
    }
}

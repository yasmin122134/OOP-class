// 329233472 yasmin haddad

/**
 * provides methods to sort an array of ints in ascending or descending order.
 * @author yasmin haddad
 * @version 1.0
 * @since 2020-03-03
 */
public class Sort {
    /**
     * sorts an array of ints in ascending order.
     * @param numbers int array of numbers.
     */
    public static void sortAsc(int[] numbers) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    // Swap elements if they are in the wrong order
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    /**
     * sorts an array of ints in descending order.
     * @param numbers int array of numbers.
     */
    public static void sortDesc(int[] numbers) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] < numbers[i + 1]) {
                    // Swap elements if they are in the wrong order
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    /**
     * main method.
     *
     * <p>takes in an array of Strings from the command line.
     *    the first argument is either "asc" or "desc".
     *    the rest of the arguments are numbers.
     *    it prints out the numbers in ascending or descending order
     *    based on the first argument.
     * @param args String array of command line arguments.
     */
    public static void main(String[] args) {
        // get the sorting order from the first argument
        String ascOrDesc = args[0];

        // create an array of ints to hold the numbers
        int[] numbers = new int[args.length - 1];

        // convert the rest of the arguments to ints
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(args[i + 1]);
        }

        // sort the numbers in the specified order
        if (ascOrDesc.equals("asc")) {
            sortAsc(numbers);
        } else if (ascOrDesc.equals("desc")) {
            sortDesc(numbers);
        }

        // print the sorted numbers
        for (int number : numbers) {
            System.out.print(number + " ");
        }

    }
}

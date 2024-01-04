public class Sort {
    public static void sortAsc(int[] numbers) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    public static void sortDesc(int[] numbers) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] < numbers[i + 1]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        String ascOrDesc = args[0];
        int[] numbers = new int[args.length - 1];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(args[i + 1]);
        }

        if (ascOrDesc.equals("asc")) {
            sortAsc(numbers);
        } else if (ascOrDesc.equals("desc")) {
            sortDesc(numbers);
        }

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }

    }
}

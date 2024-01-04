public class DescribeNumbers {
    public static int[] stringsToInts(String[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = Integer.parseInt(numbers[i]);
        }
        return result;

    }
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        return min;
    }
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
        }
        return max;
    }
    public static float avg(int[] numbers) {
        float sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];

        }
        return sum / numbers.length;
    }

    public static void main(String[] args) {
        int[] numbers = stringsToInts(args);
        System.out.println("min: " + min(numbers));
        System.out.println("max: " + max(numbers));
        System.out.println("avg: " + avg(numbers));
    }
}

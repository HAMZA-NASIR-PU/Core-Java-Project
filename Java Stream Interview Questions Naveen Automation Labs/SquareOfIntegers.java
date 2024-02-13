//Get square of each number in a stream

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;

class SquareOfIntegers {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 10, 20, 30, 15);
        Stream<Integer> stream = nums.stream();
        IntStream intStream = stream.mapToInt(number -> number * number);
        intStream.forEach((n) -> {
            System.out.println(n + " ");
        });

        // Remove those values which are less than 100.
        // Then take the average of remaining numbers;

        double avg = nums.stream().map(num -> num * num).filter(num -> num > 100).mapToInt(num -> num).average()
                .getAsDouble();

        System.out.println("Average = " + avg);
    }
}
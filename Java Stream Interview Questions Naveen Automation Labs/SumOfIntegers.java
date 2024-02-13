//Get sum of all integers in a stream

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.Optional;

class SumOfIntegers {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        // Stream<Integer> stream = nums.stream();
        // int sum = stream.mapToInt(number -> number).sum();
        // System.out.println("Sum = " + sum);
        
        Optional<Integer> opSum = nums.stream().reduce((a, b) -> a + b);
        System.out.println("Sum = " + opSum.orElse(0));
    }
}
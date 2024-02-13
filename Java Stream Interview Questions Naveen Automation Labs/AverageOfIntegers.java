//Get average of all integers in a stream

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.OptionalDouble;

class AverageOfIntegers {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = nums.stream();
        OptionalDouble opAvg = stream.mapToInt(number -> number).average();
        double average = opAvg.getAsDouble();
        System.out.println("Average = " + average);
        
    }
}
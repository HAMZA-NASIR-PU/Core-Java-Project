//Find odd and even  numbers from a list

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class EvenAndOddIntegers {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 10, 20, 30, 15);
        List<Integer> even = nums.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());

        System.out.println("Even numbers list = " + even);
    }
}
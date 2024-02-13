//Print numbers with prefix 2

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class NumbersStartWithPrefix2 {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(21, 10, 20, 30, 15);

        // Integer.toString is an instance method but Integer.parseInt is a static
        // method.
        List<Integer> result = nums.stream().map(num -> Integer.toString(num)).filter(str -> str.startsWith("2"))
                .map(str -> Integer.parseInt(str)).collect(Collectors.toList());

        System.out.println("Result = " + result);
    }
}
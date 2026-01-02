import java.util.*;
import java.util.stream.*;

public class Main {
  public static void main(String[] args) {
    
    List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    System.out.println("Square of even numbers: ");
    nums.stream().filter(num -> num % 2 == 0).map(num -> num * num).forEach(System.out::println);
    
    System.out.println("-----");
    
    System.out.println("Sum of all numbers: ");
    int sum = nums.stream().reduce(0, (a, b) -> a + b);
    System.out.println("Sum: " + sum);
    
    System.out.println("-----");
    
    System.out.println("Average of numbers: ");
    OptionalDouble avg = nums.stream().mapToInt(n -> n).average();
    System.out.println("Average: " + (avg.isPresent() ? avg.getAsDouble() : "No Value"));
    
    System.out.println("-----");
    
    int max1 = nums.stream().reduce(Integer.MIN_VALUE, (a,b) -> a > b ? a : b);
    System.out.println("Max (via reduce): " + max1);
    
    System.out.println("-----");
    
    int max2 = nums.stream().max(Integer::compareTo).orElseThrow();
    System.out.println("Max (via max): " + max2);
    
    System.out.println("-----");
    
    List<String> formatted = nums.stream().map(n -> "Number: " + n).toList();
    formatted.forEach(System.out::println);
    
  }
}


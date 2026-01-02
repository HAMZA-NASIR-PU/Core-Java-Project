import java.util.*;
import java.util.stream.*;

public class Main {
  public static void main(String[] args) {
    
    List<String> fruits = List.of("apple", "banana", "grape", "apple", "kiwi");
    
    System.out.println("Collect to Set: ");
    Set<String> set1 = fruits.stream().collect(Collectors.toSet());
    set1.forEach(System.out::println);
    
    System.out.println("-----");
    
    System.out.println("Collect to Map<fruit, length>: ");
    Map<String, Integer> map1 = fruits.stream().collect(
                                                        Collectors.toMap(fruit -> fruit, fruit -> fruit.length(), (existing, replacement) -> existing));
    map1.forEach((k, v) -> System.out.println(k + " -> " + v));
    
    System.out.println("-----");
    
    System.out.println("Iterate over Map using for loop: ");
    for (Map.Entry<String, Integer> entry : map1.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }
    
    System.out.println("-----");

    System.out.println("Iterate over Map keys: ");

    for(String key : map1.keySet()) {
      System.out.println("Key = " + key);
    }
    
    System.out.println("-----");
    
    System.out.println("Iterate over Map values: ");
    for (Integer value : map1.values()) {
      System.out.println("Value = " + value);
      
    }
    
    System.out.println("-----");
    
    System.out.println("Join fruit names: ");
    String joined = fruits.stream().collect(Collectors.joining(", "));
    System.out.println(joined);
    
    System.out.println("-----");
    
    System.out.println("Fruit occurrences: ");
    Map<String, Long> occurrences = fruits.stream().collect(Collectors.groupingBy(fruit -> fruit, Collectors.counting()));
    occurrences.forEach((k, v) -> System.out.println(k + " - > " + v));
    
  }
}


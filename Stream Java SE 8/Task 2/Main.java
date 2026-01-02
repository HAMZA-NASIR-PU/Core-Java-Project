import java.util.*;
import java.util.stream.*;

public class Main {
  
  public static void main(String[] args) {
    
    List<String> l1 = List.of("Hamza", "Ali", "Ahmad", "Hassan", "Asad");
    
    System.out.println("Names starting with A:");
    l1.stream().filter(name -> name.startsWith("A")).forEach(System.out::println);
    
    System.out.println("-----");
    
    System.out.println("Uppercase names:");
    l1.stream().map(name -> name.toUpperCase()).forEach(System.out::println);
    
    System.out.println("-----");
    
    long count = l1.stream().filter(name -> name.length() > 4).count();
    System.out.println("Count of names with length greater than 4: " + count);
    
    System.out.println("-----");
    
    System.out.println("Sorted names:");
    l1.stream().sorted().forEach(System.out::println);
    
    System.out.println("-----");
    
    System.out.println("First 3 sorted names: ");
    l1.stream().sorted().limit(3).forEach(System.out::println);
    
    System.out.println("-----");
    
    System.out.println("Skip 2 and print the rest");
    l1.stream().skip(2).forEach(System.out::println);
  }
  
}


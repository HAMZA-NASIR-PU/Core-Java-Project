import java.util.*;
import java.util.stream.*;

public class Main {
  
  public static void main(String[] args) {
    
    List<Integer> l1 = List.of(1, 2, 3, 4, 5);
    Stream<Integer> s1 = l1.stream();
    l1.forEach(System.out::println);
    
    System.out.println("-----");
    
    String[] array = { "Java", "C++", "Python" };
    Stream<String> s2 = Arrays.stream(array);
    s2.forEach(System.out::println);
    
    System.out.println("-----");
    
    Stream<String> s3 = Stream.of("Java", "C++", "C#");
    s3.forEach(System.out::println);
    
    System.out.println("-----");
    
    Stream<Double> s4 = Stream.generate(Math::random).limit(5);
    s4.forEach(System.out::println);
    
    System.out.println("-----");
    
    Stream<Integer> s5 = Stream.iterate(0, n -> n + 2).limit(5);
    s5.forEach(System.out::println);
    
    System.out.println("-----");
    
    IntStream s6 = IntStream.range(1, 5);
    s6.forEach(System.out::println);
    
    System.out.println("-----");
    
    IntStream s7 = IntStream.rangeClosed(1, 5);
    s7.forEach(System.out::println);
    
    System.out.println("-----");
    
    String word = "HELLO";
    word.chars().mapToObj(c -> (char) c).forEach(System.out::println);
    
    System.out.println("-----");
    
    
    Stream<Object> s8 = Stream.empty();
    System.out.println("Empty Stream Count: " + s8.count());
    
  }
  
}




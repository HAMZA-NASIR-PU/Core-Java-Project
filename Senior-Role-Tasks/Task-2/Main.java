//
//  Find Duplicates
//
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
      
      List<Integer> numbers = List.of(
        10, 20, 30, 20, 40, 10, 50, 20, 30
      );

      Map<Integer, Long> mp = numbers.stream().collect(
        Collectors.groupingBy(Function.identity(), Collectors.counting())
      );

      for (Map.Entry<Integer, Long> entry : mp.entrySet()) {
        System.out.println(entry.getKey() + " --> " + entry.getValue());
      }

    }
}
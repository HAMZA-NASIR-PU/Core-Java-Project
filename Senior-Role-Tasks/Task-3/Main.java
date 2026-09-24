//
//  First non-repeated element.
//
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
      
      List<String> values = List.of(
        "java", "spring", "java", "aws",
        "docker", "spring", "redis"
      );

      Map<String, Long> mp = values.stream().collect(
        Collectors.groupingBy(
          Function.identity(),
          LinkedHashMap::new,
          Collectors.counting()
        )
      );

      mp.forEach((key, value) -> System.out.println(key + " --> " + value));

      System.out.println("\n\n");

      for (Map.Entry<String, Long> entry : mp.entrySet()) {
        if (entry.getValue() == 1) {
          System.out.println(entry.getKey());
          break;
        }
      }

    }
}
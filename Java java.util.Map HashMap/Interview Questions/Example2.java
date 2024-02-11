// Example 2: Using compute to update values based on existing mappings

import java.util.*;

public class Example2 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 25);

        // compute: Updates the value associated with the specified key using the given
        // remapping function
        map.compute("John", (key, value) -> value + 1);

        System.out.println("John's age: " + map.get("John")); // Output: John's age: 26
    }
}

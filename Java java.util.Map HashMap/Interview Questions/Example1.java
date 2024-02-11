// Example 1: Using putIfAbsent to avoid overwriting existing values

import java.util.Map;
import java.util.HashMap;
public class Example1 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        
        // putIfAbsent: Adds a key-value pair only if the key is not present
        map.putIfAbsent("John", 25);
        map.putIfAbsent("John", 30); // This won't overwrite the existing value
        
        System.out.println("John's age: " + map.get("John")); // Output: John's age: 25
    }
}

import java.util.Map;
import java.util.HashMap;

public class BasicExample {
    public static void main(String[] args) {
        // Create a Map
        Map<String, Integer> map = new HashMap<>();

        // Add key-value pairs to the map
        map.put("John", 25);
        map.put("Alice", 30);
        map.put("Bob", 20);

        // Accessing values by key
        int johnsAge = map.get("John");
        System.out.println("John's age: " + johnsAge);

        // Check if a key exists
        if (map.containsKey("Alice")) {
            System.out.println("Alice is in the map");
        }

        // Check if a value exists
        if (map.containsValue(20)) {
            System.out.println("Someone is 20 years old");
        }

        // Iterating over keys
        System.out.println("Keys:");
        for (String name : map.keySet()) {
            System.out.println(name);
        }

        // Iterating over values
        System.out.println("Values:");
        for (int age : map.values()) {
            System.out.println(age);
        }

        // Iterating over key-value pairs
        System.out.println("Entries:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Remove a key-value pair
        map.remove("Bob");

        // Size of the map
        System.out.println("Size of the map: " + map.size());

        // Clear the map
        map.clear();

        // Check if the map is empty
        if (map.isEmpty()) {
            System.out.println("The map is empty");
        }
    }
}

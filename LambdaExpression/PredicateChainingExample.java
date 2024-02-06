// Filtering a Collection using Predicate Chaining:

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class PredicateChainingExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        // Define predicates for filtering
        Predicate<String> startsWithA = name -> name.startsWith("A");
        Predicate<String> endsWithE = name -> name.endsWith("e");

        // Chain procedures using logical AND
        Predicate<String> startsWithAAndEndsWithE = startsWithA.and(endsWithE);

        // Filter the names using the chained predicate
        List<String> filteredNames = names.stream().filter(startsWithAAndEndsWithE).collect(Collectors.toList());

        System.out.println("Names starting with 'A' and ending with 'e' : " + filteredNames);
    }
}
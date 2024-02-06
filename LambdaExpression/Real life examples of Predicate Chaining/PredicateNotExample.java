import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateNotExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie", "David", "Eve");

        // Predicate.not() is a static method that was introduced in Java 11.
        Predicate<String> doesNotStartWithA = Predicate.not(name -> name.startsWith("A"));

        // Filtering based on negated predicate
        List<String> filteredNames = names.stream()
                .filter(doesNotStartWithA)
                .toList();

        System.out.println("Names not starting with 'A': " + filteredNames);
    }
}
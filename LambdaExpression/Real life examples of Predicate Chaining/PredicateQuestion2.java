
// Sure! Here's a Java interview question for you:

// Question:
// Write a Java program to filter a list of strings based on the following criteria:

// The string length should be greater than 5.
// The string should start with the letter 'A'.
// The string should contain the letter 'e'.
// Please provide the code using Java 8 Stream API and Predicate interface.

// Feel free to take your time to write the code, and I'll be here to provide guidance or feedback if needed.



import java.util.function.Predicate;
import java.util.List;
import java.util.Arrays;

public class PredicateQuestion2 {
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
        // Define all filters
        Predicate<String> isLengthMoreThan5 = str -> str.length() > 5;
        Predicate<String> isStartWithA = str -> str.startsWith("A");
        Predicate<String> isContainE = str -> str.contains("e");

        // Combined filter
        Predicate filter = isLengthMoreThan5.and(isStartWithA).and(isContainE);

        List<String> names = Arrays.asList("John", "Peter", "Charlie", "Eve", "Alexee");

        names.stream().filter(filter).toList().forEach(name -> System.out.println(name));

    }
}
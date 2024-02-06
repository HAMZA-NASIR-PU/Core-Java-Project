import java.util.function.Predicate;

public class PredicateValidationExample {
    public static void main(String[] args) {
        Predicate<String> startsWithA = name -> name.startsWith("A");
        Predicate<String> endsWithE = name -> name.endsWith("e");

        // Chaining Predicate for complex conditions
        Predicate<String> startsWithAAndEndsWithE = startsWithA.and(endsWithE);

        System.out.println(startsWithAAndEndsWithE.test("Alice"));
        System.out.println(startsWithAAndEndsWithE.test("Bob"));
    }
}
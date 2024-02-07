import java.util.function.Predicate;

public class PredicateQuestion1 {
    public static void main(String[] args) {
        // Predicate to check if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Test the predicate with various numbers
        System.out.println("Is 5 even? " + isEven.test(5));
        System.out.println("Is 10 even? " + isEven.test(10));

        // Define a Predicate to check if a string has more than 5 characters
        Predicate<String> hasMoreThan5Characters = str -> str.length() > 5;

        // Test the predicate with various strings
        System.out.println("Does 'Java' has more than 5 characters? " + hasMoreThan5Characters.test("Java"));
        System.out.println("Does 'Predicate' has more than 5 characters? " + hasMoreThan5Characters.test("Predicate"));
    }
}
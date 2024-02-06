// Validation Logic with Predicate Chaining:

import java.util.function.Predicate;

public class UserValidation {
    // Predicates for user validation
    private static Predicate<String> isNotNullOrEmpty = str -> str != null && !str.isEmpty();
    private static Predicate<String> hasValidEmailFormat = email -> email
            .matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    public static boolean isValidUser(String username, String email) {
        // Chain predicates to define validation rules
        Predicate<String> isValidUsername = isNotNullOrEmpty;
        Predicate<String> isValidEmail = isNotNullOrEmpty.and(hasValidEmailFormat);

        // Check if both username and email are valid
        return isValidUsername.test(username) && isValidEmail.test(email);
    }

    public static void main(String[] args) {
        String username = "user123";
        String email = "user123@example.com";

        if (isValidUser(username, email)) {
            System.out.println("User is valid.");
        } else {
            System.out.println("User is invalid.");
        }
    }
}

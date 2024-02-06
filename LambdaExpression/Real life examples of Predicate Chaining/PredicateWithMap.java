import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateWithMap {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie", "David", "Eve");

        Predicate<String> isLongName = name -> name.length() > 4;

        List<Integer> lengthOfLongNames = names.stream().filter(isLongName).map(str -> str.length()).toList();

        System.out.println("Lengths of long names" + lengthOfLongNames);
    }
}
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> nums = List.of(1, 2, 3, 4, 5);

        Map<Integer, Integer> mp = nums.stream()
            .collect(
                Collectors.toMap(
                    Function.identity(),
                    num -> num * 10
                )
            );

        mp.forEach(
            (key, value) -> System.out.println(key + " --> " + value)
        );

        System.out.println("\n\n");

        List<String> words = Arrays.asList(
            "Spring Boot",
            "Angular",
            "Linux"
        );

        Map<String, Integer> mp2 = words.stream()
            .collect(
                Collectors.toMap(
                    word -> word,
                    word -> word.length()
                )
            );

        for (Map.Entry<String, Integer> entry : mp2.entrySet()) {
            System.out.println(
                entry.getKey() + " --> " + entry.getValue()
            );
        }

        System.out.println("\n\n");

        Map<String, Integer> mp3 = words.stream()
            .collect(
                Collectors.toMap(
                    word -> word,
                    word -> word.length()
                )
            )
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (a, b) -> a,
                    LinkedHashMap::new
                )
            );

        mp3.forEach(
            (key, value) -> System.out.println(key + " --> " + value)
        );

        System.out.println("\n\n");

        mp3 = mp3.entrySet()
            .stream()
            .sorted(
                Comparator
                    .comparingInt(
                        (Map.Entry<String, Integer> e) -> e.getValue()
                    )
                    .reversed()
            )
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (a, b) -> a,
                    LinkedHashMap::new
                )
            );

        for (Map.Entry<String, Integer> entry : mp3.entrySet()) {
            System.out.println(
                entry.getKey() + " --> " + entry.getValue()
            );
        }
    }
}
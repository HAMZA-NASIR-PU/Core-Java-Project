import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    private String name;
    private List<String> hobbies;

    public Person(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = hobbies;
    }

    public List<String> getHobbies() {
        return hobbies;
    }
}

public class StreamFlatMapExample2 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", Arrays.asList("Reading", "Painting")),
                new Person("Bob", Arrays.asList("Cooking", "Gardening")),
                new Person("Charlie", Arrays.asList("Hiking", "Photography"))
        );

        List<String> allHobbies = people.stream()
                                        .flatMap(person -> person.getHobbies().stream())
                                        .collect(Collectors.toList());

        System.out.println("All hobbies: " + allHobbies);
    }
}

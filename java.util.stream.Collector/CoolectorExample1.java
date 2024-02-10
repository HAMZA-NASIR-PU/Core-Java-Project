import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

public class CollectorLearning {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("apple", "banana", "orange");

        // System.out.println(stream.collect(Collectors.toList()));

        Collector<String, List<String>, List<String>> collector = Collector.of(
                ArrayList::new,
                List::add,
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                },
                Collector.Characteristics.IDENTITY_FINISH);

        List<String> collected = stream.collect(collector);

        System.out.println(collected);
    }
}
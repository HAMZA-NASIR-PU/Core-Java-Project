import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.function.Predicate;
import java.util.function.Function;

class ExampleCode {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(5, 4, 7, 8, 9);

        // public interface Stream<T> extends BaseStream<T, Stream<T>>{}
        Stream<Integer> intStream = nums.stream();

        // Once uyou use the sream, you can't again consume it. ==>
        // java.lang.IllegalStateException: stream has already been operated upon or
        // closed
        // Good for your Data Source
        // intStream.forEach(n -> System.out.println(n));
        // intStream.forEach(n -> System.out.println(n));

        // System.out.println(intStream.count());

        // Stream<Integer> newIntStream = intStream.sorted();
        // newIntStream.forEach(n -> System.out.println(n));

        // for(int n:nums) System.out.println(n);

        // Stream<Integer> doubleIntStream = intStream.map(n -> n*2);
        // doubleIntStream.forEach(n -> System.out.println(n));

        // Builder pattern
        // Total 3 streams are created, but the final stream reside in memeory
        // nums.stream().sorted().filter(n -> n % 2 != 0).map(n -> n * 2).forEach(n ->
        // System.out.println(n));

        Predicate<Integer> isNumberOdd = n -> n % 2 != 0;
        Function<Integer, Integer> multiplyBy2 = n -> n * 2;

        nums.stream().filter(isNumberOdd).map(multiplyBy2).forEach(n -> System.out.println(n));
    }
}
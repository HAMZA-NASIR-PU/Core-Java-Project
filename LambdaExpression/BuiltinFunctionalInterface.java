import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Date;

class BuiltinFunctionalInterface {
    public static void main(String[] args) {

        Predicate<String> p1 = s -> (s != null && s.length() > 0);
        System.out.println("Predicate result = " + p1.test(null));

        Function<String, Integer> f = s -> s.length();
        System.out.println("Function result = " + f.apply("ABCD"));

        Function<String, Boolean> f2 = s -> (s != null && s.length() > 0);
        System.out.println("Function2 result = " + f2.apply("ABC"));

        Consumer<String> c1 = str -> System.out.println("Consumer1 = " + str);
        c1.accept("Consumer string");

        Supplier<Date> s1 = () -> new Date();
        System.out.println("Supplier1 result = " + s1.get());

        UnaryOperator<Integer> u1 = x -> x * x * x;
        System.out.println("UnaryOperator result = " + u1.apply(5));

        /************* PREDICATE CHAINING ******/

        Predicate<String> notNull = (s) -> (s != null && s.length() > 0);
        Predicate<String> minimumLength = (s) -> (s.length() >= 10);
        Predicate<String> maximumLength = (s) -> (s.length() <= 20);

        System.out.println("Is HELLO WORLD a valid string? = "
                + notNull.and(minimumLength).and(maximumLength).test("HELLO WORLD"));
    }

}

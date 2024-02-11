import java.util.List;
import java.util.Arrays;

public class DoubleStreamAndOptionalDouble {

    public static void main(String[] args) {
        List<Double> list = Arrays.asList(2.59, 3.98, 2.22);
        double averageValue = list.stream().mapToDouble(x -> x).average().orElse(0.0);
        System.out.println("Average value = " + averageValue);
    }
}
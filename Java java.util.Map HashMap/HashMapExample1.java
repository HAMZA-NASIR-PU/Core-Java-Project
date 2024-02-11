import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

public class HashMapExample1 {
    public static void main(String[] args) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        // System.out.println(frequency.get(22)); // return null
        List<Integer> nums = Arrays.asList(2, 3, -1, 2, 8, 5, 5);

        nums.stream().forEach((num) -> {
            if (frequency.containsKey(num))
                frequency.put(num, frequency.get(num) + 1);
            else
                frequency.put(num, 1);
        });

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            System.out.println(entry.getKey() + " ==> " + entry.getValue());
        }

    }
}
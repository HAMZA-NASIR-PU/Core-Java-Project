//Find Nth Highest Salary using Java Stream API
// Myntra interview question

import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

class FindNthHighestSalary {
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("Nawaz", 300);
        map1.put("Imran", 500);
        map1.put("Asim", 700);
        map1.put("Hameed", 1200); // 2nd highest salary
        map1.put("Raza", 1300);

        // This code will not work in case of duplicate keys
        // System.out.println(getNthHighestSalary(3, map1));

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("Nawaz", 300);
        map2.put("Imran", 500);
        map2.put("Asim", 700); // 2nd highest salary
        map2.put("Jahangir", 700); // 2nd highest salary
        map2.put("Hameed", 1300);
        map2.put("Raza", 1300);
        map2.put("Abdullah", 1300);

        System.out.println(getDynamicNthHighestSalary(2, map2));
    }

    public static Map.Entry<Integer, List<String>> getDynamicNthHighestSalary(int n, Map<String, Integer> map) {
        Map.Entry<Integer, List<String>> result = map.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList())
                .get(1);

        return result;
    }

    public static Map.Entry<String, Integer> getNthHighestSalary(int n, Map<String, Integer> map) {
        // In ascending order
        // Map.Entry<String, Integer> result =
        // map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList()).get(1);

        // In descending order
        Map.Entry<String, Integer> result = map
                .entrySet()
                .stream()
                .sorted(
                        Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList()).get(n - 1);
        return result;
    }
}
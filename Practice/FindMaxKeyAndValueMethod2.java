import java.util.Map; //interface
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Set; //interface
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

class FindMaxKeyAndValueMethod2 {
    
    
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 1, 5, 10, 19, -2);
        Map<Integer, Integer> map = new HashMap<>();
        int i = 1;
        for(Integer num : list) {
            map.put(i, num);
            i++;
        }
        System.out.println(map);
        System.out.println("**********************************");
        System.out.println(findMaxValueKey(map));
    }
    
    public static Integer findMaxValueKey(Map<Integer, Integer> map)
    {
        if(map.isEmpty()) return null;
        
        Stream<Map.Entry<Integer, Integer>> stream = map.entrySet().stream();
        
        Optional<Map.Entry<Integer, Integer>> maxEntry = stream.max(Map.Entry.comparingByValue());
        
        // Optional<Map.Entry<Integer, Integer>> maxEntry = map.entrySet().stream().max(Map.Entry.comparingByValue());
        
        Integer maxKey = maxEntry.map(Map.Entry::getKey).orElse(null);
        
        return maxKey;
    }
}
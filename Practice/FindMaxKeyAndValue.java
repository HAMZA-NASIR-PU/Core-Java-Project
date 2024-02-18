import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

class FindMaxKeyAndValue {
    
    
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
        
        Integer maxKey = null;
        int maxValue = Integer.MIN_VALUE;
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            
            if(value > maxValue) {
                maxValue = value;
                maxKey = key;
            }
        }
        
        return maxKey;
    }
}
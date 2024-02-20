import java.util.Map;
import java.util.HashMap;

class WordFrequencyCounter {
    public static void main(String[] args) {
        String text = "Lorem ipsum dolor sit amet consectetur adipiscing elit " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua " +
                "Lorem ipsum dolor sit amet consectetur adipiscing elit " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
        
        String[] words = text.split("\\s+");
        
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        
        for(String word:words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }
    }
}
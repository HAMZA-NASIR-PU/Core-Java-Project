import java.util.*;

public class Main {
    public static void main(String[] args) {
      
      List<String> logs = List.of(
        "INFO", "ERROR", "INFO", "WARN",
        "ERROR", "INFO", "DEBUG", "WARN"
      );
      
      Map<String, Integer> mp = new HashMap<>();

      for (int i = 0; i < logs.size(); i++) {

        String s = logs.get(i);

        if (mp.containsKey(s)) {
          mp.put(s, mp.get(s) + 1);
        } else {
          mp.put(s, 1);
        }

      }

      for (Map.Entry<String, Integer> entry : mp.entrySet()) {
        System.out.println(entry.getKey() + " --> " + entry.getValue());
      }

    }
}
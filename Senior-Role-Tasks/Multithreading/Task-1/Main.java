//
// Outut of nums.size() is non-predictable. But if use Collections.synchronizedList(new ArrayList<>()),
// then output will always be 20000.
//
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
      
      List<Integer> nums = Collections.synchronizedList(new ArrayList<>());


      Thread t1 = new Thread(() -> {
        for (int i = 1; i <= 10000; i++) {
          nums.add(i);
        }
      });

      Thread t2 = new Thread(() -> {
        for (int i = 10001; i <= 20000; i++) {
          nums.add(i);
        }
      });

      t1.start();
      t2.start();

      t1.join();
      t2.join();

      System.out.println(nums.size());

    }
}
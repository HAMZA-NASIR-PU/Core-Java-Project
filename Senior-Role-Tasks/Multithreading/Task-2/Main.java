//
// Print even and odd using two threads in Java.
//

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static int counter = 1;
  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    
    int limit = 20;

    Runnable printOdd = () -> {
      synchronized(Main.class) {
        while (counter <= limit) {
          while (counter % 2 == 0) {
            try {
              Main.class.wait();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          }

          if (counter <= limit) {
            System.out.println(Thread.currentThread().getName() + ": " + counter);
            counter++;
            Main.class.notifyAll();
          }

        }
      }
    };

    Runnable printEven = () -> {
        synchronized(Main.class) {
        while (counter <= limit) {
          while (counter % 2 != 0) {
            try {
              Main.class.wait();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          }

          if (counter <= limit) {
            System.out.println(Thread.currentThread().getName() + ": " + counter);
            counter++;
            Main.class.notifyAll();
          }

        }
      }
    };

    executor.submit(printOdd);
    executor.submit(printEven);

    executor.shutdown();
    
  }
}


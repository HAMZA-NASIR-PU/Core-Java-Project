//
//  PING AND PONG USING SEMAPHORE.
//
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
      
      ExecutorService executor = Executors.newFixedThreadPool(2);

      Semaphore pingSemaphore = new Semaphore(1);
      Semaphore pongSemaphore = new Semaphore(0);

      Runnable printPing = () -> {
        
        try {
          while (true) {
            pingSemaphore.acquire();
            System.out.println("PING");
            Thread.sleep(2000);
            pongSemaphore.release();
          }
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

      };

      Runnable printPong = () -> {

        try {
          while (true) {
            pongSemaphore.acquire();
            System.out.println("PONG");
            Thread.sleep(2000);
            pingSemaphore.release();
          }
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

      };

      executor.submit(printPing);
      executor.submit(printPong);

      executor.shutdown();
    }
}
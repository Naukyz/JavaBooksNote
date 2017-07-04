package ThinkInJava.c21.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    public static void main(String[] args) {

        // ExecutorService executor = Executors.newFixedThreadPool(1);
        // 参数为1 就是newSingleThreadExecutor
        // ExecutorService executor = Executors.newSingleThreadExecutor();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }
}

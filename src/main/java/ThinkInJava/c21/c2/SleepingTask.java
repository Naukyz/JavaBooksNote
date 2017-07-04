package ThinkInJava.c21.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                if (countDown == 0) {
                    System.out.println(status() + "  ");
                } else {
                    System.out.print(status() + "  ");
                }
                Thread.sleep(500);
                // TimeUnit.MICROSECONDS.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new SleepingTask());
        }
        executor.shutdown();

    }

}

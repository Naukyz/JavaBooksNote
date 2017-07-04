package ThinkInJava.c21.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/4
 */
public class SimplePriorities implements Runnable {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        executor.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        executor.shutdown();
    }

    private int countDown = 5;
    private volatile double d;  // No optimization
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + " : " + countDown + "  " + d;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
                System.out.println(this);
                if (--countDown == 0) {
                    return;
                }
            }
        }
    }
}

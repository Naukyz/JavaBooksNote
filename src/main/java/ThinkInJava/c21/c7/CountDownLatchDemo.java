package ThinkInJava.c21.c7;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/16
 */
public class CountDownLatchDemo {
    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            executor.execute(new WaitingTask(latch));
        }
        for (int i = 0; i < SIZE; i++) {
            executor.execute(new TaskPortion(latch));
        }
        System.out.println("Launched all tasks");
        executor.shutdown();


    }
}

class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random();
    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    public void dowork() throws InterruptedException {
        // rand.nextInt() 是线程安全的
        int a = rand.nextInt(2000);
        TimeUnit.MILLISECONDS.sleep(a);
        System.out.println("randInt -> " + a);
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d ", id);
    }

    @Override
    public void run() {
        try {
            dowork();
            latch.countDown();
        } catch (InterruptedException e) {
        }
    }
}

class WaitingTask implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String toString() {
        return String.format("Waiting Task %1$-3d ", id);
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");

        }
    }
}
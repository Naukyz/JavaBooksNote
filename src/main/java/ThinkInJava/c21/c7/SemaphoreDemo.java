package ThinkInJava.c21.c7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/17
 */

public class SemaphoreDemo {
    final static int SIZE = 5;

    public static void main(String[] args) throws Exception {
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService executor = Executors.newCachedThreadPool();
//        for (int i = 0; i < SIZE; i++) {
//            executor.execute(new CheckoutTask<>(pool));
//        }
//        System.out.println("All CheckoutTask created");

        List<Fat> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            System.out.println(i + " : main() thread checked out ");
            f.operation();
            list.add(f);
        }
//        Future<?> blocked = executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    pool.checkOut();
//                } catch (InterruptedException e) {
//                    System.out.println("checkOut() Interrupted");
//                }
//            }
//        });
//        TimeUnit.SECONDS.sleep(2);
//        blocked.cancel(true);
        System.out.println("Checking in objects in " + list);
        for (Fat f : list) {
            pool.checkIn(f);
        }
        for (Fat f : list) {
            pool.checkIn(f);
        }
        executor.shutdown();

    }
}


class CheckoutTask<T> implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public String toString() {
        return "CheckoutTask{" +
                "id=" + id +
                '}';
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + " check out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " check in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {

        }
    }
}

package ThinkInJava.c21.c2;

import java.util.concurrent.ExecutorService;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/4
 */
public class DaemonFromFactory implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        // ExecutorService executor = Executors.newCachedThreadPool(new DaemonThreadFactory());
        ExecutorService executor = new DaemonThreadPoolExecutor();
        for (int i = 0; i < 10; i++) {
            executor.execute(new DaemonFromFactory());
        }
        System.out.println("All Daemons started");
        Thread.sleep(11000);

    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

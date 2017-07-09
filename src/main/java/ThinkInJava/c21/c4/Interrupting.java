package ThinkInJava.c21.c4;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/9
 */
class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read():");
        } catch (Exception e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while (true) {
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        new Thread() {
            @Override
            public void run() {
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}

public class Interrupting {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = executor.submit(r);
        Thread.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
//        test(new IOBlocked(System.in));
//        test(new SynchronizedBlocked());
        Thread.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}

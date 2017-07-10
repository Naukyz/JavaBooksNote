package ThinkInJava.c21.c4;

import java.io.IOException;
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
        Thread.currentThread().setName("SleepBlocked");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "  InterruptedException ");
        }
        System.out.println(Thread.currentThread().getName() + "  Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("IOBlocked");

        try {
            System.out.println(Thread.currentThread().getName() + "  Waiting for read():");
            in.read();
        } catch (IOException e) {
            // isInterrupted() 是否是中断状态
            // interrupted() 重置中断状态
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "  Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + "  Exiting IOBlocked.run()");
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
        Thread.currentThread().setName("SynchronizedBlocked");
        System.out.println(Thread.currentThread().getName() + "  Trying to call f()");
        // 构造方法里面有一个线程获得了锁 所以下面的 f() 阻塞
        f();
        System.out.println(Thread.currentThread().getName() + "  Exiting SynchronizedBlocked.run()");
    }
}

public class Interrupting {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = executor.submit(r);
        Thread.sleep(50);

        System.out.println(Thread.currentThread().getName() + "  Interrupting " + r.getClass().getName());
        f.cancel(true);
        System.out.println(Thread.currentThread().getName() + "  Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
//        test(new SleepBlocked());
//        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        Thread.sleep(3);
        System.out.println(Thread.currentThread().getName() + "  Aborting with System.exit(0)");
        System.exit(0);
    }
}

package ThinkInJava.c21.c3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);

    public int getValue() {
        return i.get();
    }

    private void evenIncrement() {
        i.addAndGet(2);
//        i.addAndGet(1);
//        i.addAndGet(1);

    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        // 因为 不会 停止 所以 设置 5秒钟之后 自动停止
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("Aborting");
                        System.exit(0);
                    }
                }, 5000
        );
        ExecutorService executor = Executors.newCachedThreadPool();
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        executor.execute(atomicIntegerTest);
        while (true) {
            int val = atomicIntegerTest.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}

package ThinkInJava.c21.c9;

import ThinkInJava.util.Utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/24
 */
public class MyTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        int N = 25;
        long countNumber = 100_000L;
        Counter.test(new Counter1(), N, countNumber);
        Counter.test(new Counter2(), N, countNumber);
        Counter.test(new Counter3(), N, countNumber);
        Counter.test(new Counter4(), N, countNumber);
        Counter.shutDown();
    }
}

abstract class Counter {
    private static class ThreadTest implements Runnable {
        private Counter counter;
        private CyclicBarrier cyclicBarrier;
        private long counterNumber;

        ThreadTest(Counter counter, CyclicBarrier cyclicBarrier, long counterNumber) {
            this.counter = counter;
            this.cyclicBarrier = cyclicBarrier;
            this.counterNumber = counterNumber;
        }

        @Override
        public void run() {
            for (int i = 0; i < counterNumber; i++) {
                counter.increment();
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    protected long value = 0;
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public abstract void increment();

    public abstract String toString();

    public static void shutDown() {
        executor.shutdown();
    }

    public static void test(Counter counter, int N, long counterNumber) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N + 1);
        long time = System.nanoTime();
        for (int i = 0; i < N; i++) {
            executor.execute(new ThreadTest(counter, cyclicBarrier, counterNumber));
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        Utils.printf("%1$-20s : %2$15d", counter, System.nanoTime() - time);
    }
}

class Counter1 extends Counter {
    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public String toString() {
        return "synchronized method";
    }
}

class Counter2 extends Counter {
    @Override
    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    @Override
    public String toString() {
        return "synchronized block";
    }
}

class Counter3 extends Counter {
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public String toString() {
        return "ReentrantLock";
    }

    @Override
    public void increment() {
        try {
            lock.lock();
            value++;
        } finally {
            lock.unlock();
        }
    }
}

class Counter4 extends Counter {
    private AtomicLong value = new AtomicLong(0);

    @Override
    public String toString() {
        return "AtomicLong";
    }

    @Override
    public void increment() {
        value.incrementAndGet();
    }
}

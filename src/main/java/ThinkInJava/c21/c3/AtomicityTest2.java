package ThinkInJava.c21.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicityTest2 implements Runnable {

    private int i = 0;
    private ReentrantLock lock = new ReentrantLock();

    public int getValue() {
        lock.lock();
        try {
            return i;

        } finally {
            lock.unlock();
        }
    }

    private void evenIncrement() {
        lock.lock();
        try {
            i++;
            i++;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        AtomicityTest2 atomicityTest2 = new AtomicityTest2();
        executor.execute(atomicityTest2);
        while (true) {
            int val = atomicityTest2.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);

            }
        }
    }

}
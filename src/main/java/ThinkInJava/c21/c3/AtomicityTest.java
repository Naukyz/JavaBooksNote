package ThinkInJava.c21.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/7
 */

public class AtomicityTest implements Runnable {
    private int i = 0;

//    public int  getValue() {
//        return i;
//    }

    public synchronized int  getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        AtomicityTest atomicityTest = new AtomicityTest();
        executor.execute(atomicityTest);
        while (true) {
            int val = atomicityTest.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}

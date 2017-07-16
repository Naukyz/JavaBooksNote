package ThinkInJava.c21.c4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/16
 */
public class FixedDiningPhilosopher {
    public static void main(String[] args) throws Exception {
        int ponder = 0;
        int size = 5;
        ExecutorService executor = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {
                executor.execute(new Philosopher(chopsticks[i], chopsticks[i + 1], i, ponder));
            } else {
                executor.execute(new Philosopher(chopsticks[0], chopsticks[i], i, ponder));
            }
        }
    }
}

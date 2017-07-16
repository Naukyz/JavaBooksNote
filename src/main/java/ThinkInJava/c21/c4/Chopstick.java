package ThinkInJava.c21.c4;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/16
 */
public class Chopstick {

    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}


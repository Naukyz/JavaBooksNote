package ThinkInJava.c21.c6;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/16
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random();

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                pause();
                System.out.println(this + " " + "thinking");
                right.take();
                System.out.println(this + " " + "grabbing right");
                left.take();
                System.out.println(this + " " + "grabbing left");
                pause();
                System.out.println(this + " " + "eating");
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}

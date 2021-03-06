package ThinkInJava.c21.c4;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/11
 */
public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Blocked3());
        thread.start();
        Thread.sleep(12);
        thread.interrupt();
    }
}

class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup() {
        System.out.println("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {
                    System.out.println("Sleeping");
                    Thread.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        System.out.println("Calculating");
                        for (int i = 0; i < 2500000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("Finished time-consuming operation");

                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while() test");
        } catch (Exception e) {
            System.out.println("Exiting via InterruptedException");
        }
    }
}
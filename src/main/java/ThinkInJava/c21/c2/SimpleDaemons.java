package ThinkInJava.c21.c2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/4
 */
public class SimpleDaemons implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started ");
        Thread.sleep(10000);

    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

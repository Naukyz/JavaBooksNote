package ThinkInJava.c21.c2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/5
 */

public class SelfManaged implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SelfManaged();
        }
    }

    private int countDown = 5;
    private static int threadCount = 0;

    public SelfManaged() {
        Thread t = new Thread(this, String.valueOf(++threadCount));
        t.start();
    }

    @Override
    public String toString() {
        return "#" + Thread.currentThread().getName() + "(" + countDown + ")";
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }

        }
    }
}

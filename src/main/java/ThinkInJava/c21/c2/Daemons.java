package ThinkInJava.c21.c2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/4
 */

public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        // 在 后台线程 里面开启的线程 也是后台线程
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon() + ".");
        Thread.sleep(1000);
    }

}

class DaemonSpawn implements Runnable {

    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}

class Daemon implements Runnable {

    private Thread[] t = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("DaemonSpawn " + i + " started, ");
        }
        for (int i = 0; i < t.length; i++) {
            System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ".");
        }
        while (true) {
            Thread.yield();
        }
    }
}


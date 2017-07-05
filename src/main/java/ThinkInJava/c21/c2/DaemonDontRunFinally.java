package ThinkInJava.c21.c2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/4
 */

public class DaemonDontRunFinally {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ADaemon());
        thread.setDaemon(true);
        // thread.setDaemon(true);
        thread.start();
        Thread.sleep(50);
    }
}

class ADaemon implements Runnable {

    @Override
    public void run() {
        // 后台进程在不执行 finally 的情况下 就会终止 run
        // 后台线程 会突然关闭 在main结束时
        try {
            System.out.println("Starting ADaemon");
            Thread.sleep(100);
            System.out.println("ADaemonIng");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("This should always run?");
        }
    }
}
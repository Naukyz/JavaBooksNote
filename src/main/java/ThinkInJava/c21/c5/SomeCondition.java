package ThinkInJava.c21.c5;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/15
 */
public class SomeCondition {
    static SharedMonitor sharedMonitor = new SharedMonitor();
    static boolean someCondition = false;

    public static void main(String[] args) throws InterruptedException {


        // T1
        synchronized (sharedMonitor) {
            // <setup someCondition for T2>
            sharedMonitor.notify();
        }

        // T2
        while (someCondition) {
            // Point 1
            synchronized (sharedMonitor) {
                sharedMonitor.wait();
            }
        }

        // T2 判断是 true 进入 point1 同时执行 T1 的 notify 然后 T2 wait T2 会永远错过notify 死锁

        // T2 改进
        synchronized (sharedMonitor) {
            while (someCondition) {
                sharedMonitor.wait();
            }
        }
    }


}

class SharedMonitor {

}
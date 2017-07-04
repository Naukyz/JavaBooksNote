package ThinkInJava.c21.c2;

import java.util.concurrent.ThreadFactory;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/4
 */
public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}

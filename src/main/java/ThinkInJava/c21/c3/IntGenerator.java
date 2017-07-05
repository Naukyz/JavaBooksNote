package ThinkInJava.c21.c3;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/5
 */

public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}

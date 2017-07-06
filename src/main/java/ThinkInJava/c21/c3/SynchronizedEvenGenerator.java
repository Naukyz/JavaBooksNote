package ThinkInJava.c21.c3;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/6
 */
public class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args){
        EvenChecker.test(new SynchronizedEvenGenerator());
    }

}

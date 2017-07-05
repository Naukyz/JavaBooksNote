package ThinkInJava.c21.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/5
 */
public class NaiveExceptionHandling {
    public static void main(String[] args) {
        try {
            ExecutorService executor = Executors.newCachedThreadPool();
            executor.execute(new ExceptionThread());
        } catch (RuntimeException e) {
            System.out.println("Exception has been handled");
        }
    }
}

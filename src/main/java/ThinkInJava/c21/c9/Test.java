package ThinkInJava.c21.c9;

import java.util.Random;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/24
 */
public class Test {
    public static void main(String[] args) {
        Random random = new Random(0);
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(100));

        }
    }

}

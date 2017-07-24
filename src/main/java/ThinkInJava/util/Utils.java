package ThinkInJava.util;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/20
 */
public class Utils {
    public static void printnb(Object o) {
        System.out.print(o);
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void printf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}

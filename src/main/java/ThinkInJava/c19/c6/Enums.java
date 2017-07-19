package ThinkInJava.c19.c6;

import java.util.Random;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/19
 */

public class Enums {
    private static Random rand = new Random();

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
package ThinkInJava.c14.c2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/8/22
 */
public class Test {
    public static void main(String[] args) {

        Class<Integer> ii = int.class;

//        ii = double.class;

//        Class<Number> iii = int.class;

        Class<? extends Number> iii = int.class;

        Class<?> i = int.class;

        iii = double.class;

        i = double.class;


    }
}

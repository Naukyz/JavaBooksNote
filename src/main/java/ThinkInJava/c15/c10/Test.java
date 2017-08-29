package ThinkInJava.c15.c10;

import java.util.List;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/8/28
 */
public class Test {

    static List l1;
    static List<Object> l2;
    static List<?> l3;
    static List<? extends Object> l4;

    static void a(List l1) {
        Test.l1 = l1;
        Test.l2 = l1;
        Test.l3 = l1;
        Test.l4 = l1;

    }

    static void a2(List<Object> l2) {
        Test.l1 = l2;
        Test.l2 = l2;
        Test.l3 = l2;
        Test.l4 = l2;
    }

    static void a3(List<?> l3) {
        Test.l1 = l3;
        // Test.l2 = l3;
        Test.l3 = l3;
        Test.l4 = l3;
    }

    static void a4(List<? extends Object> l4) {
        Test.l1 = l4;
        // Test.l2 = l4;
        Test.l3 = l4;
        Test.l4 = l4;
    }

    public static void main(String[] args) {

    }

}

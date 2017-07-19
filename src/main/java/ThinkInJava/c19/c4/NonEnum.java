package ThinkInJava.c19.c4;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/19
 */
public class NonEnum {
    public static void main(String[] args) {
        Class<Integer> intClass = Integer.class;
        try {
            for (Object en : intClass.getEnumConstants())
                System.out.println(en);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

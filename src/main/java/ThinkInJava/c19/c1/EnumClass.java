package ThinkInJava.c19.c1;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/19
 */
enum Shrubbery {
    GROUND, CRAWLING, HANGING
}

public class EnumClass {
    public static void main(String[] args) {

        for (Shrubbery s : Shrubbery.values()) {
            System.out.println(s + " ordinal: " + s.ordinal());
            System.out.println(s.compareTo(Shrubbery.CRAWLING)); // 调用者减参数
            System.out.println(s.equals(Shrubbery.CRAWLING));
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("----");
        }
    }
}

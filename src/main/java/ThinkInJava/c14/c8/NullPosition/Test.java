package ThinkInJava.c14.c8.NullPosition;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class Test {
    public static void main(String[] args) {
        Staff staff = new Staff("CEO", "CTO", "Project Leader");
        staff.fillPosition("CEO", new Person("A", "B", "C"));
        System.out.println(staff);
        System.out.println(staff.positionAvailable("CEO"));
        System.out.println(staff.positionAvailable("CTO"));
        System.out.println(staff.positionAvailable("AAA"));

    }
}

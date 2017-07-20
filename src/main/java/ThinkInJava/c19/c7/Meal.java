package ThinkInJava.c19.c7;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/20
 */
public class Meal {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
}

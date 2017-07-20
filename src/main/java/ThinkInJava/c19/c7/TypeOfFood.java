package ThinkInJava.c19.c7;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/20
 */
public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCourse.LASAGNE;
        food = Food.Dessert.GELATO;
        food = Food.Coffee.CAPPUCCINO;

    }
}
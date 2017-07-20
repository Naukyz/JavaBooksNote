package ThinkInJava.c19.c7;

import ThinkInJava.c19.c6.Enums;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/20
 */

public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);
    private Food[] values;

    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }
}

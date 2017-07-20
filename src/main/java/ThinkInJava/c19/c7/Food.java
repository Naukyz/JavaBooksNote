package ThinkInJava.c19.c7;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/20
 */

public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS
    }

    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO
    }

    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL
    }

    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA
    }
}

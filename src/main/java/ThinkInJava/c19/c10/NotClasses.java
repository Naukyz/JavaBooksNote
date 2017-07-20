package ThinkInJava.c19.c10;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/20
 */

import static ThinkInJava.util.Utils.*;

enum LikeClasses {
    WINKEN {
        void behavior() {
            print("Behavior1");
        }
    },
    BLINKEN {
        void behavior() {
            print("Behavior2");
        }
    },
    NOD {
        void behavior() {
            print("Behavior3");
        }
    };

    abstract void behavior();
}

public class NotClasses {
    // void f1(LikeClasses.WINKEN instance) {} // Nope
}

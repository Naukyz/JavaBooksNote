package ThinkInJava.c19.c10;

import static ThinkInJava.util.Utils.*;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/20
 */

public enum OverrideConstantSpecific {
    NUT, BOLT,
    WASHER {
        void f() {
            print("Overridden method");
        }
    };

    void f() {
        print("default behavior");
    }

    public static void main(String[] args) {
        for (OverrideConstantSpecific ocs : values()) {
            printnb(ocs + ": ");
            ocs.f();
        }
    }
}

package ThinkInJava.c19.c5;

import java.util.Random;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/19
 */
enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

    private Random rand = new Random();

    public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }

    public static CartoonCharacter mValueOf(int a) {
        for (CartoonCharacter cartoonCharacter : values()) {
            if (cartoonCharacter.ordinal() == a) {
                return cartoonCharacter;
            }
        }
        return null;
    }
}

public class EnumImplementation {

    public static <T> void printNext(Generator<T> rg) {

        System.out.print(rg.next() + ", ");
    }

    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++)
            printNext(cc);
        System.out.println();
        System.out.println(CartoonCharacter.mValueOf(1));
    }
}
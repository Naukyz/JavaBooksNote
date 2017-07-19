package ThinkInJava.c19.c4;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/19
 */
enum Search {
    HITHER, YON
}

public class UpcastEnum {
    public static void main(String[] args) {
        Search[] vals = Search.values();
        Enum e = Search.HITHER;
        for (Enum en : e.getClass().getEnumConstants())
            System.out.println(en);
    }
}
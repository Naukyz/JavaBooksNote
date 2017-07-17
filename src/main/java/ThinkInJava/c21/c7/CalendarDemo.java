package ThinkInJava.c21.c7;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/17
 */

public class CalendarDemo {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss SS E");
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS E");

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        System.out.println(simpleDateFormat2.format(calendar.getTime()));
        System.out.println(simpleDateFormat.format(calendar.getTime()));

        System.out.println();


        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        System.out.println(week);


    }

}

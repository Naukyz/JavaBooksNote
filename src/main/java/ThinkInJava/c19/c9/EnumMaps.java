package ThinkInJava.c19.c9;

import ThinkInJava.c19.c8.AlarmPoints;

import java.util.EnumMap;
import java.util.Map;

import static ThinkInJava.c19.c8.AlarmPoints.*;
import static ThinkInJava.util.Utils.*;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/20
 */

interface Command {
    void action();
}

public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
        em.put(KITCHEN, new Command() {
            public void action() {
                print("Kitchen fire!");
            }
        });
        em.put(BATHROOM, new Command() {
            public void action() {
                print("Bathroom alert!");
            }
        });
        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            printnb(e.getKey() + ": ");
            e.getValue().action();
        }
        try {
            // If there's no value for a particular key:
            print(em.get(UTILITY));
            em.get(UTILITY).action();
        } catch (Exception e) {
            print(e);
        }
    }
}

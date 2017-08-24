package ThinkInJava.c14.c8.NullRobotProxy;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/28
 */
public class SnowRobot implements Robot {
    private String name;

    public SnowRobot(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String model() {
        return "SnowRobot";
    }

    @Override
    public List<Operation> operations() {
        return Arrays.asList(
                new Operation() {
                    @Override
                    public String description() {
                        return "can shovel snow";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " shovel snow");
                    }
                }, new Operation() {
                    @Override
                    public String description() {
                        return "can chip ice";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " chip ice");
                    }
                }
        );
    }
}

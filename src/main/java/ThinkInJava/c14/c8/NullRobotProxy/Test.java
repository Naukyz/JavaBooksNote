package ThinkInJava.c14.c8.NullRobotProxy;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/28
 */
public class Test {
    public static void main(String[] args) {
        Robot[] robots = {
                new SnowRobot("My Snow Robot"),
                NullRobot.newNullRobot(SnowRobot.class)
        };

        for (Robot robot : robots) {
            System.out.println("name : " + robot.name());
            System.out.println("model : " + robot.model());
            for (Operation operation : robot.operations()) {
                System.out.println(operation.description());
                operation.command();
            }
            System.out.println("---");
        }
    }
}

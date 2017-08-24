package ThinkInJava.c14.c7.ProxyDemo;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class InterfaceImpl implements Interface {

    private int a;

    @Override
    public void doSomeThing() {
        System.out.println("doSomeThing");
    }

    @Override
    public void doSomethingElse(String args) {
        System.out.println("doSomethingElse " + args);
    }

    @Override
    public void setA(int a) {
        this.a = a;
    }

    @Override
    public int getA() {
        return a;
    }
}

package ThinkInJava.c14.c7.ProxyDemo;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class SimpleProxyDemo {
    public static void main(String[] args) {
        Interface i = new InterfaceImpl();
        i.doSomeThing();
        i.doSomethingElse("a");

        System.out.println("---");

        Interface i2 = new SimpleProxyHandler(i);
        i2.doSomeThing();
        i2.doSomethingElse("a");

    }
}

class SimpleProxyHandler implements Interface {

    private Interface proxied;

    public SimpleProxyHandler(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomeThing() {
        System.out.println("proxy doSomeThing");
        proxied.doSomeThing();
    }

    @Override
    public void doSomethingElse(String args) {
        System.out.println("proxy doSomethingElse " + args);
        proxied.doSomethingElse(args);
    }

    @Override
    public void setA(int a) {

    }

    @Override
    public int getA() {
        return 0;
    }
}
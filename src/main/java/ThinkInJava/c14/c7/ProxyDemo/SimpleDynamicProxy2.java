package ThinkInJava.c14.c7.ProxyDemo;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class SimpleDynamicProxy2 {
    public static void main(String[] args) {
        Interface i = new InterfaceImpl();
        i.doSomeThing();
        i.doSomethingElse("a");
        System.out.println("---");

        Interface i2 = ProxyUtil2.newProxy(
                Interface.class,
                new SimpleDynamicProxyHandler2(i)
        );
        i2.doSomeThing();
        i2.doSomethingElse("a");

        i2.setA(1);
        System.out.println(i.getA());
    }
}

class SimpleDynamicProxyHandler2 implements InvocationHandler {

    private Interface proxied;

    public SimpleDynamicProxyHandler2(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---" + proxy.getClass());
        System.out.println("---" + method.getName());
        System.out.println("---" + Arrays.toString(args));
        return method.invoke(proxied, args);
    }
}

class ProxyUtil2 {
    @SuppressWarnings("unchecked")
    public static <T> T newProxy(Class<T> returnClass, InvocationHandler invocationHandler) {
        return (T) Proxy.newProxyInstance(returnClass.getClassLoader(),
                new Class[]{returnClass}, invocationHandler);
    }
}

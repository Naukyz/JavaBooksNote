package ThinkInJava.c14.c7;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class SimpleDynamicProxy {
    public static void main(String[] args) {
        Interface i = new InterfaceImpl();
        i.doSomeThing();
        i.doSomethingElse("a");
        System.out.println("---");
//        Interface i2 = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
//                new Class[]{Interface.class}, new SimpleDynamicProxyHandler(i));
        Interface i2 = ProxyUtil.newProxy(Interface.class, SimpleDynamicProxyHandler.class, i);
        i2.doSomeThing();
        i2.doSomethingElse("a");

        i2.setA(1);
        System.out.println(i.getA());
    }
}

class SimpleDynamicProxyHandler implements InvocationHandler {
    // 把 Object 改成了 Interface
    private Interface proxied;

    public SimpleDynamicProxyHandler(Interface proxied) {
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

class ProxyUtil {
    @SuppressWarnings("unchecked")
    public static <T> T newProxy(Class<T> returnClass, Class<? extends InvocationHandler> handlerClass, T t) {
        try {
            Constructor cons = handlerClass.getDeclaredConstructor(returnClass);
            return (T) Proxy.newProxyInstance(returnClass.getClassLoader(),
                    new Class[]{returnClass}, (InvocationHandler) cons.newInstance(t));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
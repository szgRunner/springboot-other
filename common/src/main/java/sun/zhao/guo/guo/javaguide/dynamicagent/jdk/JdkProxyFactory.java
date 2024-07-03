package sun.zhao.guo.guo.javaguide.dynamicagent.jdk;

import java.lang.reflect.Proxy;

/**
 * 获取代理对象的工厂类
 */

public class JdkProxyFactory {

    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new JdkInvocationHandler(target));
    }

}

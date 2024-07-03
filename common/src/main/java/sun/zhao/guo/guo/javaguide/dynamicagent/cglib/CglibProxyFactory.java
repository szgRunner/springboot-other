package sun.zhao.guo.guo.javaguide.dynamicagent.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 获取代理类
 */
public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz){

        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();

        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
//        enhancer.setInterfaces(clazz.getInterfaces());

        // 设置被代理类
        enhancer.setSuperclass(clazz);

        // 设置方法拦截器
        enhancer.setCallback(new CglibMethodInterceptor());


//        return Enhancer.create(clazz, new CglibMethodInterceptor());

        // 场景代理类
        return enhancer.create();

    }

}

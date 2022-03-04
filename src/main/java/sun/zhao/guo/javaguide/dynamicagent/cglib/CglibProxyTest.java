package sun.zhao.guo.javaguide.dynamicagent.cglib;

public class CglibProxyTest {

    public static void main(String[] args) {

        CglibSmsService smsService = (CglibSmsService) CglibProxyFactory.getProxy(CglibSmsService.class);
        smsService.send("hello, cglib");

    }

}

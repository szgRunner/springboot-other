package sun.zhao.guo.javaguide.dynamicagent.jdk;

public class SmsServiceTest {

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        String msg = smsService.sendMsg("hello, sunzhaoguo");
        System.out.println("msg = " + msg);
    }

}

package sun.zhao.guo.guo.javaguide.dynamicagent.jdk;

public class SmsServiceImpl implements SmsService {

    @Override
    public String sendMsg(String message) {
        System.out.println("send message: " + message);
        return message;
    }
}

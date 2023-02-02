package sun.zhao.guo.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class RabbitService {

    @RabbitListener(queues = "fanout_queue_email")
    public void psubConsumerEmail(Message message){
        System.out.println("邮件业务接收到message： " + new String(message.getBody()));
    }

    @RabbitListener(queues = "fanout_queue_sms")
    public void psubConsumerSms(Message message){
        System.out.println("短信业务接收到message： " + new String(message.getBody()));
    }

}

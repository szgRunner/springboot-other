package sun.zhao.guo.rabbit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.zhao.guo.model.User;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AmqpRabbit {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void amqptest(){

        // 定义 fanout 类型交换器
        amqpAdmin.declareExchange(ExchangeBuilder.fanoutExchange("fanout_exchange").build());

        // 定义两个默认持久化队列，分别处理 email 和 sms
        amqpAdmin.declareQueue(new Queue("fanout_queue_email"));
        amqpAdmin.declareQueue(new Queue("fanout_queue_sms"));

        // 将队列与交换器进行绑定
        amqpAdmin.declareBinding(new Binding("fanout_queue_email", Binding.DestinationType.QUEUE,
                "fanout_exchange", "", null));
         amqpAdmin.declareBinding(new Binding("fanout_queue_sms", Binding.DestinationType.QUEUE,
                "fanout_exchange", "", null));

    }


    // rabbit 发送

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage(){
        rabbitTemplate.convertAndSend("fanout_exchange", "", new User("1", "sunzhaoguo", new Date()));
    }
}

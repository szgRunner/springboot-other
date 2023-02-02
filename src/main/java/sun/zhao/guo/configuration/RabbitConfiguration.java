package sun.zhao.guo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitConfiguration {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /*
     * 基于配置类的方式
     */

    /*
     * 定义fanout 类型的交换器
     */
    @Bean
    public Exchange buildExchange(){
        return ExchangeBuilder.fanoutExchange("fanout_exchange").build();
    }

    /**
     * 定义 email 和 sms 队列
     */
    @Bean
    public Queue fanout_queue_email(){
        return new Queue("fanout_queue_email");
    }

    @Bean
    public Queue fanout_queue_sms(){
        return new Queue("fanout_queue_sms");
    }

    // 将两个不同名称的消息队列与交换器绑定
    @Bean
    public Binding bindingEmail(){
        return BindingBuilder.bind(fanout_queue_email()).to(buildExchange()).with("").noargs();
    }

    @Bean
    public Binding bindingSms(){
        return BindingBuilder.bind(fanout_queue_sms()).to(buildExchange()).with("").noargs();
    }
}

package sun.zhao.guo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("fanout_queue_email")
    public Queue fanout_queue_email(){
        return new Queue("fanout_queue_email");
    }

    @Bean
    @Qualifier("fanout_queue_sms")
    public Queue fanout_queue_sms(){
        return new Queue("fanout_queue_sms");
    }

    // 将两个不同名称的消息队列与交换器绑定
    @Bean
    public Binding bindingEmail(@Qualifier("fanout_queue_email") Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding bindingSms(@Qualifier("fanout_queue_sms") Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}

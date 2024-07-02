package sun.zhao.guo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import sun.zhao.guo.listener.RedisKeyEventListener;

/**
 * @ClassName: RedisListenerConfiguration
 * @Description:
 * @Author liupei
 * @Emaile 3268727800@qq.com
 * @Version V 1.0.0
 */
@Configuration
public class RedisListenerConfiguration {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    @Bean
    public RedisKeyEventListener redisKeyEventListener(RedisConnectionFactory connectionFactory){
        RedisKeyEventListener redisKeyEventListener = new RedisKeyEventListener(container(connectionFactory));
        return redisKeyEventListener;
    }
}

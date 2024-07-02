package sun.zhao.guo.listener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.util.StringUtils;
import sun.zhao.guo.constant.GlobalRedis;

import javax.annotation.Nullable;
import java.util.Properties;

/**
 * @ClassName: RedisKeyEventListener
 * @Description: redis更新或保存数据监听
 * @Author liupei
 * @Emaile 3268727800@qq.com
 * @Version V 1.0.0
 */
public class RedisKeyEventListener implements MessageListener {

    private String keyspaceNotificationsConfigParamete = "Ex";
    private static final Topic TOPIC_KEYNAMESPACE_NAME = new PatternTopic("__keyspace@*__:"+ GlobalRedis.PREFIX_KEY_IMAGES +":*");
//    private static final Topic KEY_TOPIC = new PatternTopic("__keyevent@*__:set");

    @Nullable
    private ApplicationEventPublisher publisher;


    /**
     * Creates new {@link KeyspaceEventMessageListener}.
     *
     * @param listenerContainer must not be {@literal null}.
     */
    public RedisKeyEventListener(RedisMessageListenerContainer listenerContainer) {
        init(listenerContainer);
    }

//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        super.onMessage(message, pattern);
//    }

//    @Override
//    protected void doRegister(RedisMessageListenerContainer container) {
//        super.doRegister(container);
//        container.addMessageListener(this,KEY_TOPIC);
//    }

//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.publisher = applicationEventPublisher;
//    }

//    @Override
//    protected void doHandleMessage(Message message) {
//        this.publishEvent(new RedisKeyExpiredEvent(message.getBody()));
//
//    }

//    protected void publishEvent(RedisKeyExpiredEvent event){
//        if(this.publisher != null){
//            this.publisher.publishEvent(event);
//        }
//    }

    public void init(RedisMessageListenerContainer listenerContainer) {
        if (StringUtils.hasText(keyspaceNotificationsConfigParamete)) {

            RedisConnection connection = listenerContainer.getConnectionFactory().getConnection();

            try {

                Properties config = connection.getConfig("notify-keyspace-events");

                if (!StringUtils.hasText(config.getProperty("notify-keyspace-events"))) {
                    connection.setConfig("notify-keyspace-events", keyspaceNotificationsConfigParamete);
                }

            } finally {
                connection.close();
            }

            //
            listenerContainer.addMessageListener(this, TOPIC_KEYNAMESPACE_NAME);
        }
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

    }
}

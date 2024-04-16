package sun.zhao.guo.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @ClassName: RedisKeyListener
 * @Description: 监听 redis key 过期事件
 */
//@Component
public class RedisKeyListener extends RedisKeyEventListener{

    /**
     *
     * @param listenerContainer must not be {@literal null}.
     */
    public RedisKeyListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        super.onMessage(message, pattern);

        String key = message.toString();
        System.out.println("自定义KEY过期时间："+key);
        //监听redis 数据变化。触发保存。
        if("set".equals(key)){
            // todo 业务超时处理
        }

    }
}

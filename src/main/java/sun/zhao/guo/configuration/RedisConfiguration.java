package sun.zhao.guo.configuration;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.data.redis.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.configuration
 * @Author: SzgStart-sunzg
 * @Date: 2024/01/25/18:25
 * @Description:
 */

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 老 jackson
        //https://alibaba.github.io/fastjson2/spring_support_cn.html
        /*
            JacksonRedisSerializer<Object> jacksonRedisSerializer = new JacksonRedisSerializer<>(Object.class);
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                    ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
            jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
       */

        // 序列化配置 用 fastjson2
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setJSONB(true);
        fastJsonConfig.setReaderFeatures(
                JSONReader.Feature.SupportAutoType,
                JSONReader.Feature.SupportArrayToBean,
                JSONReader.Feature.SupportClassForName
        );
        fastJsonConfig.setWriterFeatures(
                JSONWriter.Feature.WriteClassName,
                JSONWriter.Feature.WriteEnumUsingToString,
                /**
                 * 为null的属性默认自动排除
                 * 在fastjson中，缺省是不输出空值的。无论Map中的null和对象属性中的null，序列化的时候都会被忽略不输出，这样会减少产生文本的大小。
                 * 但是有时候接口联调需要，非要输出的话，用：SerializerFeature.WriteMapNullValue
                 */
                JSONWriter.Feature.WriteMapNullValue,
                JSONWriter.Feature.WriteNullListAsEmpty,
//                JSONWriter.Feature.WriteNullStringAsEmpty,
//                JSONWriter.Feature.WriteNullNumberAsZero,
                JSONWriter.Feature.WriteNullBooleanAsFalse
        );
        fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);

        //设置默认序列化器
        template.setDefaultSerializer(fastJsonRedisSerializer);

        //key-value
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJsonRedisSerializer);

        //key-hash
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(fastJsonRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }
}

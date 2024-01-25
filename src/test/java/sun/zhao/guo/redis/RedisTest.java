package sun.zhao.guo.redis;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sun.zhao.guo.model.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.redis
 * @Author: SzgStart-sunzg
 * @Date: 2024/01/25/18:40
 * @Description:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("test:redis",
                User.builder().id(UUID.fastUUID().toString(true)).userName("sunzhaoguo").build()
        );

//        User user = (User) redisTemplate.opsForValue().get("test:redis");
        val user = redisTemplate.opsForValue().get("test:redis");

        System.out.println("User = " + JSON.toJSONString(user));
    }

}

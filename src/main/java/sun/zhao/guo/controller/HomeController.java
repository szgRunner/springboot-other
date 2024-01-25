package sun.zhao.guo.controller;

import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.zhao.guo.model.User;

import javax.annotation.Resource;

@Tag(name = "测试入口")
@RestController
public class HomeController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Operation(summary = "hello")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello( @Validated @ParameterObject User user){
        return "hello, SpringBoot";
    }


    /**
     * 在请求头中的参数，例如 GET 请求，如果是一个对象，
     * 则需要使用 @ParameterObject 注解，如果不添加，则无法在 Swagger 接口文档界面进行正常请求。
     * @param user
     * @return
     */
    @Operation(summary = "测试redis")
    @RequestMapping(value = "/redis", method = RequestMethod.POST)
    public User testRedis(@RequestBody @Validated User user){
        String uuid = UUID.fastUUID().toString(true);
        User redisUser = (User) redisTemplate.opsForValue().get("test:redis");
        return redisUser;
    }

}

package sun.zhao.guo.controller;

import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.zhao.guo.constant.Config;
import sun.zhao.guo.exception.HttpResponseTemp;
import sun.zhao.guo.exception.ResultStat;
import sun.zhao.guo.model.TestInfo;
import sun.zhao.guo.model.User;
import sun.zhao.guo.service.db.TestInfoService;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "测试入口")
@RestController
@RequestMapping(Config.API_VERSION + "/home")
@CrossOrigin(origins = "http://localhost:9080")
public class HomeController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private TestInfoService infoService;

    /**
     * 在请求头中的参数，例如 GET 请求，如果是一个对象，
     * 则需要使用 @ParameterObject 注解，如果不添加，则无法在 Swagger 接口文档界面进行正常请求。
     * @param user
     * @return
     */
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
    @CrossOrigin(origins = "http://localhost:9080")
    @Operation(summary = "测试redis")
    @RequestMapping(value = "/redis", method = RequestMethod.POST)
    public User testRedis(@RequestBody @Validated User user){
        String uuid = UUID.fastUUID().toString(true);
        User redisUser = (User) redisTemplate.opsForValue().get("test:redis");
        return redisUser;
    }

    @Operation(summary = "show users")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public HttpResponseTemp<List<TestInfo>> hello(){
        List<TestInfo> testInfos = infoService.list();
        return ResultStat.OK.wrap(testInfos);
    }

}

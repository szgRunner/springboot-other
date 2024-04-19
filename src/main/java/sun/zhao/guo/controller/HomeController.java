package sun.zhao.guo.controller;

import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.zhao.guo.constant.Config;
import sun.zhao.guo.exception.HttpResponseTemp;
import sun.zhao.guo.exception.ResultStat;
import sun.zhao.guo.model.TestInfo;
import sun.zhao.guo.model.User;
import sun.zhao.guo.service.db.TestInfoService;

import java.util.List;

@Tag(name = "测试入口")
@RestController
@RequestMapping(Config.API_VERSION + "/home")
@CrossOrigin(origins = "http://localhost:9080")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {

    private final RedisTemplate<String, Object> redisTemplate;

//    @Resource
//    private TestInfoService infoService;
    private final TestInfoService infoService;

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
    @ResponseBody
    public User testRedis(@RequestBody @Validated User user){
        String uuid = UUID.fastUUID().toString(true);
        User redisUser = (User) redisTemplate.opsForValue().get("test:redis");
        return new User();
    }

    @Operation(summary = "show tests")
    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public HttpResponseTemp<List<TestInfo>> hello(){
        List<TestInfo> testInfos = infoService.list();
        return ResultStat.OK.wrap(testInfos);
    }

    /**
     * 根据version 查询所有testInfo 信息
     * @return HttpResponseTemp
     */
    @Operation(summary = "show tests by version")
    @RequestMapping(value = "/tests/version", method = RequestMethod.GET)
    public HttpResponseTemp<List<TestInfo>> hello(@RequestParam(defaultValue = "1") Integer version){
        List<TestInfo> testInfos = infoService.listByVersion(version);
        return ResultStat.OK.wrap(testInfos);
    }

    @Operation(summary = "show single test")
    @RequestMapping(value = "/tests/id",  method = RequestMethod.GET)
    public HttpResponseTemp<TestInfo> helloUser(@RequestParam(defaultValue = "2599aadab1b788cdaa95b4d24be96715") String testId){
        TestInfo testInfo = infoService.queryOne(testId);
        return ResultStat.OK.wrap(testInfo);
    }

}

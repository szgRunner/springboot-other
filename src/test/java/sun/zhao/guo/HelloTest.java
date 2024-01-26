package sun.zhao.guo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 解决集成 spring-websocket 时 test 无法启动的问题
 * 1、@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * 2、注释掉websocket 相关注入
 * 3、根据条件引入不同的配置，防止冲突
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HelloTest {

    @Test
    public void test(){
        System.out.println("true = " + true);
    }

    @Test
    public void test2(){

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Instant.now());
            }
        }, 10000L, 3000L);

    }

}

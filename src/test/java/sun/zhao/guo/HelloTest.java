package sun.zhao.guo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootTest
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

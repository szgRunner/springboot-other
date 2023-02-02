package sun.zhao.guo.db;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.zhao.guo.model.TestInfo;
import sun.zhao.guo.service.db.TestInfoService;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.db
 * @Author: SzgStart-sunzg
 * @Date: 2023/02/02/下午3:22
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DbTest {

    @Autowired
    private TestInfoService testInfoService;

    @Order(0)
    @Test
    public void test(){
        TestInfo testInfo = new TestInfo();
        testInfo.setTestName("sunzg");
//        testInfo.setTestTime(new Date());
        testInfo.setVersion(1L);
        testInfoService.save(testInfo);
    }

    @Order(1)
    @Test
    public void test1(){
        TestInfo testInfo = testInfoService.getById("cfe16c19a1209d73a30b839646b3fe78");
        testInfo.setTestName("sunzhaoguo");
//        testInfo.setTestTime(new Date());
        testInfoService.updateById(testInfo);
    }

}

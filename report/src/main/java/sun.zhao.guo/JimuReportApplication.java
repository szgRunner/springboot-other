package sun.zhao.guo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: sun.zhao.guo
 * @Author: SzgStart-sunzg
 * @Date: 2024/07/02/14:53
 * @Description:
 */

@SpringBootApplication(scanBasePackages = {"org.jeecg.modules.jmreport","sun.zhao.guo"})
public class JimuReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(JimuReportApplication.class, args);
    }
}

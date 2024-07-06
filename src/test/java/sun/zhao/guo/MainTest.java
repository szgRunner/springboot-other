package sun.zhao.guo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/22/下午3:17
 * @Description:
 */
public class MainTest {

    public static void main(String[] args) {

        // 日期格式化
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();
        String format = LocalDate.now().format(dateTimeFormatter);
        System.out.println("format = " + format);


        String path = Objects.requireNonNull(MainTest.class.getResource("/")).getPath();

        System.out.println("path = " + path);

    }

}

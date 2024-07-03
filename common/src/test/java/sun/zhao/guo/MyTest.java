package sun.zhao.guo;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: sun.zhao.guo
 * @Author: SzgStart-sunzg
 * @Date: 2024/07/03/20:45
 * @Description:
 */
public class MyTest {

    @Test
    public void test() {
        Assertions.assertEquals("sun", "zhao");
    }

    @Test
    public void testTime() throws ParseException {
        // Date类型转String类型
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("Date类型转String类型:" + date);
// 输出结果： Date类型转String类型:2023-06-06 15:16:13
// String类型转Date类型
        Date date1 = DateUtils.parseDate("2021-05-01 01:01:01", "yyyy-MM-dd HH:mm:ss");
        System.out.println("String类型转Date类型:" + date1);
// 输出结果：String类型转Date类型:Sat May 01 01:01:01 CST 2021
        System.out.println("计算一年后的日期:" + DateUtils.addYears(new Date(), 1));
//输出结果：计算一年后的日期:Thu Jun 06 15:16:13 CST 2024
        System.out.println("计算一个月后的日期:" + DateUtils.addMonths(new Date(), 1));
//输出结果：输出结果：计算一个月后的日期:Thu Jul 06 15:16:13 CST 2023
        System.out.println("计算一天后的日期:" + DateUtils.addDays(new Date(), 1));
//输出结果：计算一天后的日期:Wed Jun 07 15:16:13 CST 2023
        System.out.println("计算一周后的日期:" + DateUtils.addWeeks(new Date(), 1));
//输出结果：计算一周后的日期:Tue Jun 13 15:16:13 CST 2023
        System.out.println("计算一个小时后的日期:" + DateUtils.addHours(new Date(), 1));
//输出结果：计算一个小时后的日期:Tue Jun 06 16:16:13 CST 2023
        System.out.println("计算一秒后的日期:" + DateUtils.addSeconds(new Date(), 1));
//输出结果：计算一秒后的日期:Tue Jun 06 15:16:14 CST 2023
        System.out.println("计算一分钟后的日期:" + DateUtils.addMinutes(new Date(), 1));
//输出结果：计算一分钟后的日期:Tue Jun 06 15:17:13 CST 2023
        System.out.println("计算一毫秒后的日期:" + DateUtils.addMilliseconds(new Date(), 1));
//输出结果：计算一毫秒后的日期:Tue Jun 06 15:16:13 CST 2023

    }

}

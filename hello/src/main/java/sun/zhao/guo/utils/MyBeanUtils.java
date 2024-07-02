package sun.zhao.guo.utils;

import org.springframework.context.ApplicationContext;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.utils
 * @Author: SzgStart-sunzg
 * @Date: 2023/02/02/下午6:18
 * @Description:
 */
public class MyBeanUtils {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (Objects.isNull(getApplicationContext())) {
            MyBeanUtils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}

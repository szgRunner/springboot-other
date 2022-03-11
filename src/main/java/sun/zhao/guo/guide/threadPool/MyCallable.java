package sun.zhao.guo.guide.threadPool;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.threadPool
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/11/下午4:36
 * @Description:
 */
public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        //返回执行当前 Callable 的线程名字
        return Thread.currentThread().getName();

    }
}

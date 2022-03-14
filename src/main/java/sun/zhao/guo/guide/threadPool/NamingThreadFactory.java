package sun.zhao.guo.guide.threadPool;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.threadPool
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/14/下午2:29
 * @Description:
 */

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给线程池里的线程命名通常有下面两种方式：
 *
 * 自己实现 ThreadFactory
 *
 * 设置线程名称，方便我们定位问题。
 */
public class NamingThreadFactory implements ThreadFactory {

    private final AtomicInteger threadNum = new AtomicInteger();
    private final ThreadFactory delegate;
    private final String name;

    public NamingThreadFactory(ThreadFactory delegate, String name) {
        this.delegate = delegate;
        this.name = name;
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = delegate.newThread(r);
        thread.setName(name + "[" + threadNum.incrementAndGet() + "]");
        return thread;
    }
}

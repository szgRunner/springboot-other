package sun.zhao.guo.guide.threadPool;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.threadPool
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/11/下午4:37
 * @Description:
 */
public class CallableDemo {

    private final static int CORE_POOL_SIZE = 5;
    private final static int MAX_POOL_SIZE = 10;
    private final static int QUEUE_CAPACITY = 100;
    private final static long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        ArrayList<Future<String>> futures = new ArrayList<>();

        MyCallable myCallable = new MyCallable();
        for (int i = 0; i < 10; i++) {

            // 提交任务到线程池
            Future<String> future = threadPoolExecutor.submit(myCallable);

            //将返回值 future 添加到 list，我们可以通过 future 获得 执行 Callable 得到的返回值
            futures.add(future);
        }

        for (Future<String> future : futures) {
            System.out.println(TimeZone.getTimeZone("Asia/Shanghai").toZoneId());
            System.out.println(LocalDateTime.now(TimeZone.getTimeZone("Asia/Shanghai").toZoneId()));
            System.out.println(LocalDate.now());
            System.out.println(LocalTime.now());
            System.out.println(Instant.now() + " --> " + future.get());
        }

        threadPoolExecutor.shutdown();
    }

}

package sun.zhao.guo.guide.aqs.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.aqs.semaphore
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/15/下午6:11
 * @Description: AQS 需要一次性拿一个许可的情况
 */
public class SemaphoreDemo {

    // 请求的数量
    private static final int threadNum = 550;

    public static void main(String[] args) {

        // 创建一个固定线程数量的线程池对象（如果线程数量过少，可能执行效率很慢）
        ExecutorService executorService = Executors.newFixedThreadPool(300);

        // 一次只能运行执行的线程数量
        Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadNum; i++) {
            final int threaders = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); // 获取一个许可，所以可运行线程数量为20/1=20
                    test(threaders);
                    semaphore.release(); // 释放一个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        System.out.println("finished");

    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadNum: " + threadNum );
        Thread.sleep(1000);
    }
}

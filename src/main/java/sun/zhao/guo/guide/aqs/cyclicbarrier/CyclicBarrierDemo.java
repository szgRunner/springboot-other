package sun.zhao.guo.guide.aqs.cyclicbarrier;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.aqs.cyclicbarrier
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/16/下午4:06
 * @Description: 新建 CyclicBarrier 时指定一个Runnable
 *
 * CyclicBarrier 还提供了一个更高级别的构造函数 CyclicBarrier(int parties, Runnable barrierAction), 用于在线程到达屏障时，优先执行
 * barrierAction, 方便处理更复杂的业务场景。
 */
public class CyclicBarrierDemo {

    private static final int threadCount = 25;


    // 需要同步的线程数量
    private static final CyclicBarrier  cyclicBarrier = new CyclicBarrier(5, () -> {
        System.out.println("------当线程数到达时，优先执行------");
    });

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;

            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    test(threadNum);
                } catch (InterruptedException | TimeoutException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

        }
        executorService.shutdown();
        System.out.println("main finished");
    }

    private static void test(int threadNum) throws InterruptedException, TimeoutException, BrokenBarrierException {
        System.out.println("threadNum: " + threadNum + " is Ready");
        /**等待60秒，保证子线程完全执行结束*/
//        cyclicBarrier.await(60, TimeUnit.SECONDS);
        cyclicBarrier.await();
        System.out.println("threadNum: " + threadNum + " is finished");
    }

}

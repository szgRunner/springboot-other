package sun.zhao.guo.guo.guide.aqs.cyclicbarrier;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.aqs.cyclicbarrier
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/16/下午4:09
 * @Description: 测试 CyclicBarrier.await()带参数
 */
public class CyclicBarrierDemo1 {

    private static final int threadNum = 25;
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadNum; i++) {
            final int threadCount = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    test(threadCount);
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
        cyclicBarrier.await(60, TimeUnit.SECONDS);
        System.out.println("threadNum: " + threadNum + " is finished");
    }

}

package sun.zhao.guo.guide.aqs.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.aqs.countdownlatch
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/11/下午2:58
 * @Description:
 */


/**
 * CountDownLatch 的作用就是 允许 count 个线程阻塞在一个地方，直至所有线程的任务都执行完毕。
 * 之前在项目中，有一个使用多线程读取多个文件处理的场景，我用到了 CountDownLatch 。具体场景是下面这样的：
 *
 * 我们要读取处理 6 个文件，这 6 个任务都是没有执行顺序依赖的任务，但是我们需要返回给用户的时候将这几个文件的处理的结果进行统计整理。
 *
 * 为此我们定义了一个线程池和 count 为 6 的CountDownLatch对象 。
 * 使用线程池处理读取任务，每一个线程处理完之后就将 count-1，调用CountDownLatch对象的 await()方法，直到所有文件读取完之后，才会接着执行后面的逻辑。
 */
public class CountDownLatchDemo {

    // 线程数量 6
    private final static int threadCount = 6;

    public static void main(String[] args) throws InterruptedException {

         // 线程池创建 测试用
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
//            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    //处理文件的业务操作
                    //......
                    System.out.println(" do sth ");
                }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                finally {
                    //表示一个文件已经被完成
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("finished");
    }

}

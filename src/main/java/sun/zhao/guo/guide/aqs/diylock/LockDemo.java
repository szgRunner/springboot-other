package sun.zhao.guo.guide.aqs.diylock;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.aqs.diylock
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/18/下午3:47
 * @Description: 以下每次执行结果都为 20000 ，通过几行代码就能实现公布功能。这就是 AQS强大之处。
 */
public class LockDemo {

    private static final DiyLock diyLock = new DiyLock();
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
           try {
               diyLock.lock();
               for (int i = 0; i< 10000; i++) {
                   TimeUnit.NANOSECONDS.sleep(1); // 休眠1s
                   count++ ;
               }
           }catch (Exception e ) {
               e.printStackTrace();
           }finally {
               diyLock.unlock();
           }
        });

        Thread thread2 = new Thread(() -> {
            try {
                diyLock.lock();
                for (int i = 0; i< 10000; i++) {
//                    TimeUnit.SECONDS.sleep(1); // 休眠1s
                    count++ ;
                }
            }catch (Exception e ) {
                e.printStackTrace();
            }finally {
                diyLock.unlock();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("count = " + count);
    }
}

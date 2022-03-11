package sun.zhao.guo.guide.aqs.completefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @package: sun.zhao.guo.guide.aqs.completefuture
 * @Author: SzgStart-sunzg
 * @Date: 2022/03/11/下午3:07
 * @Description:
 */

/**
 * 可以使用 CompletableFuture 类来改进！
 * Java8 的 CompletableFuture 提供了很多对多线程友好的方法，使用它可以很方便地为我们编写多线程程序，
 * 什么异步、串行、并行或者等待所有线程执行完任务什么的都非常方便。
 */
public class CompleteFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<Void> task1 = CompletableFuture.supplyAsync(() -> {

            return null;
        });

        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(task1);

        try {
            completableFuture.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("all done. ");


        //文件夹位置
        //List<String> filePaths = Arrays.asList(...);
        // 异步处理所有文件
        //List<CompletableFuture<String>> fileFutures = filePaths.stream()
        //        .map(filePath -> doSomeThing(filePath))
        //        .collect(Collectors.toList());
        // 将他们合并起来
        //CompletableFuture<Void> allFutures = CompletableFuture.allOf(fileFutures.toArray(new CompletableFuture[fileFutures.size()]));



    }
}

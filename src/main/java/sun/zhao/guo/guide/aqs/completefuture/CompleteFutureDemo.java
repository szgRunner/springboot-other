package sun.zhao.guo.guide.aqs.completefuture;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {

            return "Hello";
        });

        CompletableFuture<Object> future = CompletableFuture.anyOf(task1);

        try {
//            completableFuture.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(task1.get());

        assertEquals("Hello", task1.get());

        System.out.println("completableFuture = " + future.get());


        //文件夹位置
        //List<String> filePaths = Arrays.asList(...);
        // 异步处理所有文件
        //List<CompletableFuture<String>> fileFutures = filePaths.stream()
        //        .map(filePath -> doSomeThing(filePath))
        //        .collect(Collectors.toList());
        // 将他们合并起来
        //CompletableFuture<Void> allFutures = CompletableFuture.allOf(fileFutures.toArray(new CompletableFuture[fileFutures.size()]));

        /**
         *
         * 处理异步结算的结果
         * 当我们获取到异步计算的结果之后，还可以对其进行进一步的处理，比较常用的方法有下面几个：
         *
         * thenApply()
         * thenAccept()
         * thenRun()
         * whenComplete()
         * thenApply() 方法接受一个 Function 实例，用它来处理结果。
         *
         */

        CompletableFuture<String> futureApply = CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!");
        assertEquals("hello!world!", futureApply.get());
// 这次调用将被忽略。
        future.thenApply(s -> s + "nice!");
        assertEquals("hello!world!", futureApply.get());

        CompletableFuture<String> future1 = CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!");
        assertEquals("hello!world!nice!", future1.get());

        /**
         * 如果你不需要从回调函数中获取返回结果，可以使用 thenAccept() 或者 thenRun()。
         * 这两个方法的区别在于 thenRun() 不能访问异步计算的结果。
         */

        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenAccept(System.out::println);//hello!world!nice!

        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenRun(() -> System.out.println("hello!"));//hello!


        /**
         * BiConsumer 可以接收 2 个输入对象然后进行“消费”。
         *
         * whenComplete() 使用示例如下
         */


        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello!")
                .whenComplete((res, ex) -> {
                    // res 代表返回的结果
                    // ex 的类型为 Throwable ，代表抛出的异常
                    System.out.println(res);
                    // 这里没有抛出异常所有为 null
                    assertNull(ex);
                });
        assertEquals("hello!", future2.get());


        /**
         *
         * 异常处理
         * 你可以通过 handle() 方法来处理任务执行过程中可能出现的抛出异常的情况。
         *
         * public <U> CompletableFuture<U> handle(
         *     BiFunction<? super T, Throwable, ? extends U> fn) {
         *     return uniHandleStage(null, fn);
         * }
         *
         * public <U> CompletableFuture<U> handleAsync(
         *     BiFunction<? super T, Throwable, ? extends U> fn) {
         *     return uniHandleStage(defaultExecutor(), fn);
         * }
         *
         * public <U> CompletableFuture<U> handleAsync(
         *     BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
         *     return uniHandleStage(screenExecutor(executor), fn);
         * }
         *
         */
        CompletableFuture<String> future4
                = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "hello!";
        }).handle((res, ex) -> {
            // res 代表返回的结果
            // ex 的类型为 Throwable ，代表抛出的异常
            return res != null ? res : "world!";
        });
        assertEquals("world!", future4.get());
        // 你还可以通过 exceptionally() 方法来处理异常情况。

        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "hello!";
        }).exceptionally(ex -> {
            System.out.println(ex.toString());// CompletionException
            return "world!";
        });

        System.out.println("future3 = " + future3.get());
        assertEquals("world!", future3.get());

        // 如果你想让 CompletableFuture 的结果就是异常的话，可以使用 completeExceptionally() 方法为其赋值。

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
// ...
        completableFuture.completeExceptionally(new RuntimeException("Calculation failed!"));
// ...
        completableFuture.get(); // ExecutionException


        /**
         *
         * 组合 CompletableFuture
         * 你可以使用 thenCompose() 按顺序链接两个 CompletableFuture 对象
         *
         */

        CompletableFuture<String> future5
                = CompletableFuture.supplyAsync(() -> "hello!")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world!"));
        assertEquals("hello!world!", future5.get());


        System.out.println("all done. ");
    }
}

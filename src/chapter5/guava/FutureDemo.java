package chapter5.guava;

import chapter5.future.RealData;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;

/**
 * @author suruomo
 * @date 2020/7/23 14:56
 * @description: 增强future,支持回调通知
 */
public class FutureDemo {
    public static void main(String args[]) throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<String> task = service.submit(new RealData("x"));

        task.addListener(() -> {
            System.out.print("异步处理成功:");
            try {
                System.out.println(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, MoreExecutors.directExecutor());
        System.out.println("main task done.....");
        Thread.sleep(3000);
    }
}

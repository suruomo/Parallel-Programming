package chapter5.guava;

import chapter5.future.RealData;
import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;

/**
 * @author suruomo
 * @date 2020/7/23 15:01
 * @description:
 */
public class FutureDemo2 {
    public static void main(String args[]) throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> task = service.submit(new RealData("x"));

        Futures.addCallback(task, new FutureCallback<String>() {
            @Override
            public void onSuccess(String o) {
                System.out.println("异步处理成功,result=" + o);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("异步处理失败,e=" + throwable);
            }
        }, MoreExecutors.newDirectExecutorService());

        System.out.println("main task done.....");
        Thread.sleep(3000);
    }
}

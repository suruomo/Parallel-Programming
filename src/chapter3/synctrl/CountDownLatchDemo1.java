package chapter3.synctrl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author suruomo
 * @date 2020/7/27 13:47
 * @description:
 */
public class CountDownLatchDemo1 {
    private static final int threadCount = 100;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(50);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    test(finalI);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        service.shutdown();
        System.out.println("Finish!");
    }
    private static void test(int i) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Thread"+i);
        Thread.sleep(1000);
    }

}

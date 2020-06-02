package chapter3.synctrl;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器：控制线程等待，直到计数器结束才开始执行。
 * @author 苏若墨
 */
public class CountDownLatchDemo implements Runnable{
/**
 * 等待10个线程计数结束才执行
 */
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo=new CountDownLatchDemo();
    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //            一个线程完成通知计数器减一
            end.countDown();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            exec.submit(demo);
        }
        //主线程等待检查
        end.await();
        //发射火箭
        System.out.println("Fire!");
        exec.shutdown();
    }
}

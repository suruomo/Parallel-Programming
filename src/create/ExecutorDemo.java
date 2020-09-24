package create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: suruomo
 * @date: 2020/9/23 15:16
 * @description: 使用Executors框架
 */
public class ExecutorDemo {
    public static class Demo implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":start");
        }
    }

    public static void main(String[] args){
       Demo demo=new Demo();
       ExecutorService executorService= Executors.newFixedThreadPool(5);
       for (int i = 0; i < 5; i++) {
            executorService.submit(demo);
        }
    }
}

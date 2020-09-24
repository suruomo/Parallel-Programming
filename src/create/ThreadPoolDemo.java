package create;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: suruomo
 * @date: 2020/9/24 10:58
 * @description: 按照阿里巴巴推荐的创建线程池方式创建
 */
public class ThreadPoolDemo implements Callable<String> {
    /**
     * 核心线程池大小
     */
    public static final int CORE_POOL_SIZE = 5;
    /**
     * 最大线程池大小
     */
    public static final int MAX_POOL_SIZE = 10;
    /**
     * 阻塞任务队列大小
     */
    public static final int QUEUE_CAPACITY = 100;
    /**
     * 空闲线程存活时间
     */
    public static final Long KEEP_ALIVE_TIME = 1L;

    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        List<Future<String>> futureList = new ArrayList<>();
        Callable<String> callable = new ThreadPoolDemo();
        for (int i = 0; i < 10; i++) {
            //提交任务到线程池
            Future<String> future = executor.submit(callable);
            //将返回值 future 添加到 list，我们可以通过 future 获得 执行 Callable 得到的返回值
            futureList.add(future);
        }
        //获取返回结果
        for (Future<String> fut : futureList) {
            try {
                System.out.println(new Date() + "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //关闭线程池
        executor.shutdown();
    }
}

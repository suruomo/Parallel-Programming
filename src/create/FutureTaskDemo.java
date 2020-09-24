package create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: suruomo
 * @date: 2020/9/23 15:27
 * @description: 使用Callable和FutureTask，可以实现有返回值的线程创建，并且可以抛出异常
 */
public class FutureTaskDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 计算1-100的和
        int sum = 0;

        for (int i = 1; i <= 100; i++) {
            sum += i;
        }

        return sum;
    }

    public static void main(String[] args) {
        FutureTaskDemo demo=new FutureTaskDemo();
        // 使用Callable方式创建线程，需要FutureTask类的支持，用于接收运算结果，可以使用泛型指定返回值的类型
        FutureTask<Integer> result=new FutureTask<>(demo);
        //开启线程
        new Thread(result).start();
        int sum = 0;

        // 接收运算结果
        // 只有当该线程执行完毕后才会获取到运算结果，等同于闭锁的效果
        try {
            sum = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("sum is " + sum);
    }
}

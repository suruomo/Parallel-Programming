package chapter4.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author suruomo
 * @date 2020/7/15 19:19
 * @description: 无锁整数数组
 * 一个数组十个元素，每个元素加1000次
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray array=new AtomicIntegerArray(10);

    public static class AddNum implements Runnable{
        @Override
        public void run() {
            for(int k=0;k<1000;k++){
                array.getAndIncrement(k%array.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads=new Thread[10];
        for(int i=0;i<10;i++){
            threads[i]=new Thread(new AddNum());
        }
        for(int j=0;j<10;j++){
            threads[j].start();
        }
        for(int j=0;j<10;j++){
            threads[j].join();
        }
        System.out.println(array);
    }
}

package chapter4.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author suruomo
 * @date 2020/7/14 16:46
 * @description: 无锁的线程安全整数，基于CAS指令
 * 10个线程增加一个整数到100000输出
 */
public class AtomicIntegerDemo {
    static AtomicInteger i=new AtomicInteger();
    public static class AddThread implements Runnable{
        @Override
        public void run(){
            for(int k=0;k<10000;k++) {
                //当前值加一并返回新值
                i.incrementAndGet();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts=new Thread[10];
        for(int k=0;k<10;k++){
            ts[k]=new Thread(new AddThread());
        }
        for(int k=0;k<10;k++){ts[k].start();}
        for(int k=0;k<10;k++){ts[k].join();}
        System.out.println(i);
    }
}

package chapter3.reenterlock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁，手动加锁解锁
 * @author 苏若墨
 */
public class ReenterLock implements Runnable{
    static ReentrantLock reenterLock=new ReentrantLock();
    static int count=0;
    @Override
    public void run() {
        for (int i=0;i<10000;i++){
//            加锁
            reenterLock.lock();
            try{
                count++;
            }finally {
//                解锁
                reenterLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock=new ReenterLock();
        Thread thread1=new Thread(reenterLock);
        Thread thread2=new Thread(reenterLock);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}

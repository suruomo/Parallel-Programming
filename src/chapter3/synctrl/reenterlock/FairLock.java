package chapter3.synctrl.reenterlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁，按顺序，效率低下，维护成本大
 * @author 苏若墨
 */
public class FairLock implements Runnable{
    static ReentrantLock reentrantLock=new ReentrantLock();

    @Override
    public void run() {
        while (true){
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock lock=new FairLock();
        Thread t1=new Thread(lock);
        Thread t2=new Thread(lock);
        t1.start();
        t2.start();
    }
}

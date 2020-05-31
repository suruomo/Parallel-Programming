package chapter3.synctrl.reenterlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 搭配ReentrantLock，线程等待和线程唤醒
 * @author 苏若墨
 */
public class ReenterLockCondition implements Runnable{
    static ReentrantLock reentrantLock=new ReentrantLock();
    static Condition condition=  reentrantLock.newCondition();


    @Override
    public void run() {
        reentrantLock.lock();
        try{
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition reenterLockCondition=new ReenterLockCondition();
        Thread thread=new Thread(reenterLockCondition);
        thread.start();
        Thread.sleep(1000);
//        先获得锁才能唤醒
        reentrantLock.lock();
        try{
            condition.signal();
        } finally {
            reentrantLock.unlock();
        }
    }
}

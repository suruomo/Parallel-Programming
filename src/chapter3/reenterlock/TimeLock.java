package chapter3.reenterlock;

import jdk.management.resource.internal.inst.ThreadRMHooks;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 限时等待锁，超时自动放弃锁
 * @author 苏若墨
 */
public class TimeLock implements Runnable{
    static ReentrantLock reentrantLock=new ReentrantLock();

    @Override
    public void run() {
        try{
            if(reentrantLock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(reentrantLock.isHeldByCurrentThread()){
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock=new TimeLock();
        Thread t1=new Thread(timeLock);
        Thread t2=new Thread(timeLock);
        t1.start();
        t2.start();
    }
}

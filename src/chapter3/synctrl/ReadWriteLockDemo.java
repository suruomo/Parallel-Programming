package chapter3.synctrl;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：读写阻塞，多个读线程并行，写时阻塞读
 * @author 苏若墨
 */
public class ReadWriteLockDemo {
    /**
     * 普通重入锁
     */
    static ReentrantLock lock=new ReentrantLock();
    static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    /**
     * 读锁
     */
    static Lock readLock=reentrantReadWriteLock.readLock();
    /**
     * 写锁
     */
    static Lock writeLock=reentrantReadWriteLock.writeLock();
    /**
     * 返回值
     */
    int value;

    /**
     * 模拟读操作
     * @param lock
     * @return
     */
    public Object handleRead(Lock lock){
        lock.lock();
        try{
           Thread.sleep(1000);
           return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return 0;
    }

    /**
     * 模拟写操作
     * @param lock
     * @param index
     */
    public void handleWrite(Lock lock,int index){
        lock.lock();
        try{
            Thread.sleep(1000);
            value=index;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        final ReadWriteLockDemo readWriteLockDemo=new ReadWriteLockDemo();
//        读线程
        Runnable readRunnable=new Runnable() {
            @Override
            public void run() {
                try {
                    //读锁
                    readWriteLockDemo.handleRead(readLock);
                    // 重入锁
//                readWriteLockDemo.handleRead(lock);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
 //        写线程
        Runnable writeRunnable=new Runnable() {
            @Override
            public void run() {
                try {
                    //读锁
                    readWriteLockDemo.handleWrite(writeLock,new Random().nextInt());
                    // 重入锁
//                readWriteLockDemo.handleWrite(lock,new Random().nextInt());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
//         创建18个读线程
        for(int i=0;i<18;i++){
            new Thread(readRunnable).start();
        }
//        创建2个写线程
        for(int i=18;i<20;i++){
            new Thread(writeRunnable).start();
        }
    }
}

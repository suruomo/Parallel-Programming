package chapter2.sync;


/**
 * synchronized加锁：对象，实例方法，类
 * @author 苏若墨
 */
public class AccountingSync implements Runnable{
    static AccountingSync instance=new AccountingSync();
    static int count=0;

    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            synchronized (instance){
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(instance);
        Thread thread2=new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}

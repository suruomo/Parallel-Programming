package chapter2.join;

/**
 * join:阻塞当前线程，等待目标线程执行完毕后再一起向下执行
 * @author 苏若墨
 */
public class JoinMain {
    public volatile static int i=0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i=0;i<10000000;i++) {
                ;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        AddThread at=new AddThread();
        at.start();
        //若不使用join,主线程可能还未等子线程i自增到10000000,就执行了输出i操作，此时i随机
        at.join();
        System.out.println(i);
    }
}


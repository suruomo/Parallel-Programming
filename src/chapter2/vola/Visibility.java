package chapter2.vola;

/**
 * volatile保证线程之间数据修改可见性
 * 若不用volatile修饰变量，由于系统优化其他线程无法看到变量修改结果
 * @author 苏若墨
 */
public class Visibility {
    private volatile static int number=0;
    private volatile static boolean ready;
    private static class ReaderThread implements Runnable{

        @Override
        public void run() {
            while(!ready) {
                System.out.println(System.currentTimeMillis()+":未修改");
            }
            System.out.println(System.currentTimeMillis()+":"+number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ReaderThread()).start();
        Thread.sleep(1);
        number=666;
        ready=true;
    }
}

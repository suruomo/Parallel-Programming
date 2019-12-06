package chapter2.wait;

/**
 * 线程wait和线程notify
 * @author 苏若墨
 */
public class Test {
    public static Object object=new Object();
    public static class T1 implements Runnable{

        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T1 start! ");
                try{
                    System.out.println(System.currentTimeMillis()+":T1 wait for object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T1 end!");
            }
        }
    }
    public static class T2 implements Runnable{

        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T2 start! notify one object");
                object.notify();
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T2 end!");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1=new Thread(new T1());
        Thread thread2=new Thread(new T2());
        thread1.start();
        thread2.start();
    }
}

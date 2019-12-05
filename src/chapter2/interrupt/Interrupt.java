package chapter2.interrupt;

/**
 * 中断线程
 * @author 苏若墨
 */
public class Interrupt implements Runnable{
    public static Integer num=0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new Interrupt());
        thread.start();
        Thread.sleep(20);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupt!");
                break;
            }
            System.out.println("当前计数："+(num++));
        }
    }
}

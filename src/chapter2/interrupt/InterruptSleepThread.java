package chapter2.interrupt;

/**
 * sleep中断后 抛出异常被重置中断状态
 * 如果希望sleep后可以判断中断状态，则必须在sleep的异常处理中，在设置中断
 * @author 苏若墨
 */
public class InterruptSleepThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interruted!");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("Interruted When Sleep");
                        //设置中断状态
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        //主线程休眠1秒保证子线程当前已经进入子线程休眠状态中，再执行中断操作，否则主线程执行太快无法引发try方法块内异常操作
        Thread.sleep(1000);
        t1.interrupt();
    }
}


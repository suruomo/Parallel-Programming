package chapter2.priority;

/**
 * 线程优先级
 * @author 苏若墨
 */
public class PriorityDemo {
    public static class HighPriority extends Thread{
        static int count=0;
        @Override
        public void run() {
            while (true){
                synchronized (HighPriority.class){
                   count++;
                   if(count>100000){
                       System.out.println("HighPriority is completed");
                       break;
                   }
                }
            }
        }
    }
    public static class LowPriority extends Thread{
        static int count=0;
        @Override
        public void run() {
            while (true){
                synchronized (LowPriority.class){
                    count++;
                    if(count>100000){
                        System.out.println("LowPriority is completed");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HighPriority highPriority=new HighPriority();
        LowPriority lowPriority=new LowPriority();
        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        highPriority.start();
        lowPriority.start();
    }
}

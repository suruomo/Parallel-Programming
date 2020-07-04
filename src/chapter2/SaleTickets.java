package chapter2;



/**
 * 多个窗口两种方式交替卖票
 * @author 苏若墨
 */
public class SaleTickets {
    /**
     * 共享变量：决定线程结束
     */
    public  static boolean flag=false;
    /**
     * 方法一：三个窗口自由竞争卖完一定量的票，每个窗口卖的票数不同
     */
    static class SaleTicket implements Runnable{
         static int tickets = 20;
        @Override
        public void run(){
            while((tickets > 0)&&(!flag)){
                //同步锁内代码执行完成后当前持有线程释放锁，然后所有线程开始重新竞争锁
                synchronized (this){
                    if(tickets > 0){
                        System.out.println(Thread.currentThread().getName() + "卖出 第 "+ (tickets--)+"张票");
                        try{
                            Thread.sleep(100);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 方法二：三个窗口交替轮流卖票
     */
    static class SaleTicket1 implements Runnable{
        static int tickets = 100;
        @Override
        public void run(){
            while((tickets > 0)&&(!flag)){
                synchronized (this){
                    //唤醒等待线程
                    notify();
                    if(tickets > 0){
                        System.out.println(Thread.currentThread().getName() + "卖出 第 "+ (tickets--)+"张票");
                        try{
                            Thread.sleep(10);
                            //若还未卖完票则等待，让出锁资源，否则结束
                            if(tickets>0) {
                                wait();
                            }
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        SaleTicket st = new SaleTicket();
        SaleTicket1 st1 = new SaleTicket1();
        System.out.println("交替卖票结果：");
        Thread t1 = new Thread(st1, "一号窗口");
        Thread t2 = new Thread(st1, "二号窗口");
        Thread t3 = new Thread(st1, "三号窗口");
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(5000);
        System.out.println("自由卖票结果：");
        Thread t4 = new Thread(st, "一号窗口");
        Thread t5 = new Thread(st, "二号窗口");
        Thread t6 = new Thread(st, "三号窗口");
        t4.start();
        t5.start();
        t6.start();
        Thread.sleep(5000);
        // 令线程结束
        flag=true;
    }
}
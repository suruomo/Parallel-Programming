package chapter2;



/**
 * 卖票
 * @author 苏若墨
 */
public class SaleTickets {
    static class SaleTicket implements Runnable{
         static int tickets = 20;
        @Override
        public void run(){
            while(tickets > 0){
                synchronized (SaleTicket.class){
                    if(tickets > 0){
                        System.out.println(Thread.currentThread().getName() + "卖出 第 "+ (tickets--)+"张票");
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        SaleTicket st = new SaleTicket();
        Thread t1 = new Thread(st, "一号窗口");
        Thread t2 = new Thread(st, "二号窗口");
        Thread t3 = new Thread(st, "三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}
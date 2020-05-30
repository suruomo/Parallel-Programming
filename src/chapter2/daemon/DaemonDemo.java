package chapter2.daemon;


/**
 * 守护线程：垃圾回收线程等，只有守护线程时系统退出
 * @author 苏若墨
 */
public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("alive");
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
          DaemonT t=new DaemonT();
          t.setDaemon(true);
          t.start();
          Thread.sleep(  1000);
    }

}

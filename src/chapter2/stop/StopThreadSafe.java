package chapter2.stop;

/**
 * 使用stop终止线程安全示例1：自行决定何时终止
 * @author 苏若墨
 */

public class StopThreadSafe {
    public static User u=new User();
    public static class User{
        private int id;
        private String name;
        public User(){
            id=0;
            name="0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static class WriteThread extends Thread{
        //自行设置停止标志位
        volatile boolean stopMe=false;
        public void setStopMe(){
            stopMe=true;
        }
        @Override
        public void run() {
            while (true){
                if(stopMe){
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u){
                    int v=(int) (System.currentTimeMillis()/1000);
                    u.setId(v);
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                //使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。
                Thread.yield();
            }
        }
    }

    public static class ReadThread implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId()!=Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ReadThread()).start();
        while (true){
            WriteThread thread=new WriteThread();
            thread.start();
            Thread.sleep(150);
            thread.setStopMe();
        }
    }

}


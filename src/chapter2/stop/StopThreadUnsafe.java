package chapter2.stop;

/**
 * 使用stop终止线程不安全示例：造成读写不一致
 * @author 苏若墨
 */

public class StopThreadUnsafe {
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
    public static class WriteThread implements Runnable{
        @Override
        public void run() {
            while (true){
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
            Thread thread=new Thread(new WriteThread());
            thread.start();
            Thread.sleep(150);
            thread.stop();
        }
    }

}


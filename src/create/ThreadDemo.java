package create;

/**
 * @author: suruomo
 * @date: 2020/9/23 15:01
 * @description: 继承Thread类创建线程
 */
public class ThreadDemo {

    public static class Demo extends Thread {
        @Override
        public void run() {
            System.out.println("start");
        }
    }

    public static void main(String[] args){
        Demo t = new Demo();
        t.start();
    }
}

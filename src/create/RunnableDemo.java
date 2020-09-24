package create;

/**
 * @author: suruomo
 * @date: 2020/9/23 15:05
 * @description: 实现Runnable接口创建线程
 **/
public class RunnableDemo {
    public static class Demo implements Runnable {
        @Override
        public void run() {
            System.out.println("start");
        }
    }

    public static void main(String[] args){
        Thread thread=new Thread(new Demo());
        thread.start();
    }
}

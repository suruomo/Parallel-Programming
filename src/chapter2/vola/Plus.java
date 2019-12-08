package chapter2.vola;

/**
 * volatile不保证i++操作的原子性，而Synchronized可以
 *
 * @author 苏若墨
 */
public class Plus {
    private volatile static int i = 0;

    private static class PlusBySynchronized implements Runnable {

        @Override
        public void run() {
            synchronized (PlusBySynchronized.class) {
                for (int k = 0; k < 1000; k++) {
                    i++;
                }
            }
        }
    }

    private static class PlusByVolatile implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 1000; k++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new PlusByVolatile());
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }
        System.out.println("Volatile后i的值是：" + i);
        i=0;
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new PlusBySynchronized());
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }
        System.out.println("Synchronized后i的值是：" + i);
    }
}

package chapter3.collections;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author 苏若墨
 */
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> q=new ConcurrentLinkedQueue<String>();
        q.add("1");
        q.poll();
        q.add("3");
    }
}

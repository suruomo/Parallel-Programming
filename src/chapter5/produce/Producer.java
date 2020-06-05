package chapter5.produce;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 * @author 苏若墨
 */
public class Producer implements Runnable {
	private volatile boolean isRunning = true;
	private BlockingQueue<PCData> queue;
	/**
	 * 原子操作总数
	 */
	private static AtomicInteger count = new AtomicInteger();
	private static final int SLEEPTIME = 1000;

	public Producer(BlockingQueue<PCData> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		PCData data = null;
//		随机等待时间
		Random r = new Random();

		System.out.println("start producer id="+Thread.currentThread().getId());
		try {
			while (isRunning) {
				Thread.sleep(r.nextInt(SLEEPTIME));
				data = new PCData(count.incrementAndGet());
				System.out.println(data+" is put into queue");
				if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
					System.err.println("failed to put data：" + data);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	public void stop() {
		isRunning = false;
	}
}
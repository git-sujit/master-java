package com.sks.learn.java.mthreadNpcomp;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

	final static Object lock = new Object();
	final static int LIMIT = 5;
	static List<Integer> records = new ArrayList<Integer>();
	static int startValue = 1000;

	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pc.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pc.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Inside Main: ProCon done...");
	}

	/**
	 * PRODUCER
	 * 
	 * @throws InterruptedException
	 */
	public void producer() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (records.size() == LIMIT) {
					// Releases the lock to consumer
					lock.wait();
				} else {
					System.out.println("Producing: " + startValue);
					records.add(startValue++);
					// Because of while loop notify will keep running till LIMIT reached
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}

	/**
	 * CONSUMER
	 * 
	 * @throws InterruptedException
	 */
	public void consumer() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (records.size() == 0) {
					// Releases the lock to producer
					lock.wait();
				} else {
					System.out.println("Consuming: " + records.remove(records.size() - 1));
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
}

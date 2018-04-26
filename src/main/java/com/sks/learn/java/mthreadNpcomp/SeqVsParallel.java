package com.sks.learn.java.mthreadNpcomp;

public class SeqVsParallel {

	public static void main(String[] args) {
		// introToSequentialPgm();
		introToParallelPgm();
	}

	public static void introToSequentialPgm() {
		System.out.println("Sequential running of Runner-1 & Runner-2");
		Runner1 run1 = new Runner1();
		Runner2 run2 = new Runner2();
		run1.startRunning();
		run2.startRunning();
	}

	public static void introToParallelPgm() {
		System.out.println("Parallel running of Runner-3 & Runner-4");
		Thread t1 = new Thread(new Runner3());
		Thread t2 = new Thread(new Runner4());
		t1.start();
		t2.start();
		// Three threads will run in parallel: t1, t2 & main thread
		// To wait for t1 and t2, use join
		// First t1, t2 will be completed then main thread will start
		try {
			t1.join(); // waits for t1 to Die on which its called
			t2.join();// waits for t2 to Die on which its called
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("FINISHED THE TASKS...");
	}

}

/**
 * VARIOUS CLASSES
 */
class Runner1 {
	public void startRunning() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner1: " + i);
		}
	}
}

class Runner2 {
	public void startRunning() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner2: " + i);
		}
	}
}

class Runner3 implements Runnable {
	// TASK FOR THREAD
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner3: " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Runner4 implements Runnable {
	// TASK FOR THREAD
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner4: " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.sks.learn.java.mthreadNpcomp;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockTest {

	public static void main(String[] args) {
		String resource1 = "SUJIT";
		String resource2 = "SINGH";

		// FIRST THREAD
		Thread t1 = new Thread(new Runnable() {
			// Gets lock for res1 and waits lock for res2
			@Override
			public void run() {
				synchronized (resource1) {
					System.out.println("Got lock for resource1 = " + resource1);
					// No need to sleep
					synchronized (resource2) {
						System.out.println("Wait lock for resource2 = " + resource2);
					}
				}
			}
		});

		// SECOND THREAD
		Thread t2 = new Thread(new Runnable() {
			// Gets lock for res2 and waits lock for res1
			@Override
			public void run() {
				synchronized (resource2) {
					System.out.println("Got lock for resource2 = " + resource2);
					// No need to sleep
					synchronized (resource1) {
						System.out.println("Wait lock for resource1 = " + resource1);
					}
				}
			}
		});

		// THIRD THREAD TO DETECT IF ANY DEADLOCK
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					detectDeadlock();
				}
			}
		});
		// Start threads
		t1.start();
		t2.start();
		t3.start();
	}

	public static void detectDeadlock() {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		long ids[] = bean.findMonitorDeadlockedThreads();
		if (ids != null) {
			System.out.println("********* START :: DEADLOCK DETECTED... EXITTING *************");
			ThreadInfo[] threadInfoArray = bean.getThreadInfo(ids);
			// Prints the name of deadlocked thread
			for (ThreadInfo threadInfo : threadInfoArray) {
				System.out.println("ThreadName = " + threadInfo.getThreadName());
				System.out.println("ThreadState = " + threadInfo.getThreadState());
				System.out.println("ThreadId = " + threadInfo.getThreadId());
				System.out.println("LockName = " + threadInfo.getLockName());
				System.out.println("LockOwnerName = " + threadInfo.getLockOwnerName());
			}
			System.out.println("********* END :: DEADLOCK DETECTED... EXITTING *************");
			System.exit(0);
		}
	}
}

package com.sks.learn.java.memmgmt.GarbageCollection;

public class TestCustomer {

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();

		long availableBytes = runtime.freeMemory();
		System.out.println("Available memory: " + availableBytes / (Math.pow(2d, 20d)) + " MegaBytes");

		// let's create a ton of garbage....
		long millionUsers = 1000000;
		long hundredUsers = 100;
		for (int i = 0; i < hundredUsers; i++) {
			Customer c = new Customer("Sujit");
		}

		availableBytes = runtime.freeMemory();
		System.out.println("Available memory: " + availableBytes / (Math.pow(2d, 20d)) + " MegaBytes");

		System.gc();

		availableBytes = runtime.freeMemory();
		System.out.println("Available memory: " + availableBytes / (Math.pow(2d, 20d)) + " MegaBytes");
	}

}

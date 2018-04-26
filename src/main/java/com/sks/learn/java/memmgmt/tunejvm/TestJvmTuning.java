package com.sks.learn.java.memmgmt.tunejvm;

import java.util.ArrayList;
import java.util.List;

import com.sks.learn.java.memmgmt.SoftLeaks.Customer;

public class TestJvmTuning {

	public static void main(String[] args) {
		System.out.println("Tersting JVM Tuning");
		Runtime rt = Runtime.getRuntime();
		long availMem = rt.freeMemory();
		System.out.println("1. Available/Free memory = " + availMem + " KB");
		List<Customer> customers = new ArrayList<Customer>();
		while (true) {
			Customer c = new Customer("Sujit K Singh");
			customers.add(c);
			if (customers.size() >= 1000) {
				availMem = rt.freeMemory();
				System.out.println("AFTER CREATING 100 CUSTOMERS :: Total no of customers = " + customers.size());
				System.out.println("2. Available/Free memory = " + availMem);
				System.out.println("Total no of customers = " + customers.size());
				for (int i = 0; i < 10; i++) {
					customers.remove(0);
				}
				System.out.println("AFTER REMOVING :: Total no of customers = " + customers.size());
				System.out.println("3. Available/Free memory = " + availMem);

			}
		}
	}
}

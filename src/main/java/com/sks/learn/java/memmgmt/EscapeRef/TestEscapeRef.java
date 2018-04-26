package com.sks.learn.java.memmgmt.EscapeRef;

public class TestEscapeRef {

	public static void main(String[] args) {
		CustomerRecords records = new CustomerRecords();
		records.addCustomer(new Customer("John"));
		records.addCustomer(new Customer("Harry"));

		// NOT SAFE: Using Map.values()
		// Thru Map we get Escaping Reference and object can be modified
		for (CustomerReadOnly c : records.getCustomers().values()) {
			System.out.println(c);
			// Misuse of escaping reference
			// records.getCustomers().clear();
		}
		// Using Iterable
		for (CustomerReadOnly c : records) {
			System.out.println(c);
		}
		// NOT SAFE: Misuse of Iterator, can mutate the underlying collection
		// records.iterator().remove();

		// Better option: Return new HashMap <br>
		// But still underlying objects can be changed
		for (CustomerReadOnly c : records.getCustomers_NEW_COPY().values()) {
			System.out.println(c);
			// Misuse of escaping reference
			// records.getCustomers().clear();
		}
		/**
		 * ANOTHER SOLUTION: Immutable collection
		 */
		System.out.println("Using Immutable Collection: To avoid Escaping Reference");
		for (Customer c : records.getCustomers_IMMUTABLE_COLLECTION().values()) {
			System.out.println(c);
		}
		// Throws java.lang.UnsupportedOperationException
		// records.getCustomers_IMMUTABLE_COLLECTION().clear();
		/**
		 * FINAL SOLUTION: Return Interface having Read only Access to Customer
		 */
		System.out.println("Using Interface: Having read only access To Customer");
		for (CustomerReadOnly c : records.getCustomers_IMMUTABLE_COLLECTION().values()) {
			System.out.println(c);
		}
	}

}

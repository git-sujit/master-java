package com.sks.learn.java.memmgmt.EscapeRef;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CustomerRecords implements Iterable<Customer> {
	private Map<String, Customer> records;

	public CustomerRecords() {
		this.records = new HashMap<String, Customer>();
	}

	public void addCustomer(Customer c) {
		this.records.put(c.getName(), c);
	}

	public Map<String, Customer> getCustomers() {
		return this.records;
	}

	public Map<String, Customer> getCustomers_NEW_COPY() {
		return new HashMap<String, Customer>(this.records);
	}

	/**
	 * @return Collection.
	 */
	public Map<String, Customer> getCustomers_IMMUTABLE_COLLECTION() {
		return Collections.unmodifiableMap(this.records);
	}

	public Customer getCustomerByName(String name) {
		// Not safe because we are returning escaping reference of customer to
		// caller so provide read only copy of customer
		// Leverage Copy Constructor
		return new Customer(this.records.get(name));
	}

	public CustomerReadOnly getCustomerReadOnly(String name) {
		return new Customer(this.records.get(name));
	}

	@Override
	public Iterator<Customer> iterator() {
		return records.values().iterator();
	}
}

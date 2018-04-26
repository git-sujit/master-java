package com.sks.learn.java.memmgmt.EscapeRef;

public class Customer implements CustomerReadOnly {
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	/**
	 * Copy Constructor
	 */
	public Customer(Customer c) {
		// Copy each attribute one by one
		this.name = c.name;
	}

	/* (non-Javadoc)
	 * @see com.sks.learn.java.memmgmt.EscapeRef.CustomerReadOnly#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.sks.learn.java.memmgmt.EscapeRef.CustomerReadOnly#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

}

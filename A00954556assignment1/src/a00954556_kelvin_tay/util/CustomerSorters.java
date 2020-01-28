/**
 * Project: A00123456Lab4
 * File: CompareByJoinedDate.java
 * Copyright 2017 Sam Cirka. All rights reserved.
 */

package a00954556_kelvin_tay.util;

import java.util.Comparator;

import a00954556_kelvin_tay.data.Customer;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class CustomerSorters {

	public static class CompareByJoinedDate implements Comparator<Customer> {
		@Override
		public int compare(Customer customer1, Customer customer2) {
			return customer1.getJoinedDate().compareTo(customer2.getJoinedDate());
		}
	}
}

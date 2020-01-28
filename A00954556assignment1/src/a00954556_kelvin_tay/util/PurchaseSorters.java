/**
 * Project: A00954556assignment1
 * File: PurchaseSorters.java
 * Date: Oct 18, 2019
 * Time: 11:36:27 AM
 */
package a00954556_kelvin_tay.util;

import java.util.Comparator;
import java.util.Map;

import a00954556_kelvin_tay.data.Book;
import a00954556_kelvin_tay.data.Customer;
import a00954556_kelvin_tay.data.Purchase;

/**
 * Allows for sorting of purchases.
 * 
 * @author Kelvin Tay, A00954556
 *
 */
public class PurchaseSorters {

	private static Map<Long, Customer> customers;
	private static Map<Long, Book> books;

	/**
	 * Allow map of customers and books to be accessed inside PurchaseSorters class.
	 * 
	 * @param customers
	 *            a map of customers
	 * @param books
	 *            a map of books
	 */
	public static void setCustomersAndBooks(Map<Long, Customer> customers, Map<Long, Book> books) {
		PurchaseSorters.customers = customers;
		PurchaseSorters.books = books;
	}

	/**
	 * Compare by customer's last name.
	 * 
	 * @author Kelvin Tay, A00954556
	 *
	 */
	public static class CompareByLastName implements Comparator<Purchase>

	{

		/*
		 * (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Purchase purchase1, Purchase purchase2) {
			String customer1LastName = customers.get(purchase1.getCustomerId()).getLastName();
			String customer2LastName = customers.get(purchase2.getCustomerId()).getLastName();

			return customer1LastName.compareTo(customer2LastName);
		}

	}

	/**
	 * Compare by book's title.
	 * 
	 * @author Kelvin Tay, A00954556
	 *
	 */
	public static class CompareByTitle implements Comparator<Purchase> {

		/*
		 * (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Purchase purchase1, Purchase purchase2) {
			String book1Title = books.get(purchase1.getBookId()).getOriginalTitle();
			String book2Title = books.get(purchase2.getBookId()).getOriginalTitle();

			return book1Title.compareTo(book2Title);
		}

	}
}

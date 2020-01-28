/**
 * Project: A00954556assignment1
 * File: BookSorters.java
 * Date: Oct 18, 2019
 * Time: 11:36:04 AM
 */
package a00954556_kelvin_tay.util;

import java.util.Comparator;

import a00954556_kelvin_tay.data.Book;

/**
 * @author Kelvin Tay, A00954556
 *
 */
public class BookSorters {
	public static class CompareByAuthor implements Comparator<Book> {

		/*
		 * (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Book book1, Book book2) {
			return book1.getAuthors().compareTo(book2.getAuthors());
		}

	}
}

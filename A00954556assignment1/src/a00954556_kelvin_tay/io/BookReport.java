/**
 * Project: A00954556assignment1
 * File: BookReport.java
 * Date: Oct 17, 2019
 * Time: 3:11:57 PM
 */
package a00954556_kelvin_tay.io;

import java.io.PrintStream;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00954556_kelvin_tay.data.Book;

/**
 * @author Kelvin Tay, A00954556
 *
 */
public class BookReport {

	public static final String HORIZONTAL_LINE = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%3s. %-8s %-12s %-30s %-6s %-50s %-7s %-15s %-30s";
	public static final String BOOK_FORMAT = "%3d. %08d %-12s %-30.30s %-6s %-50.50s %-7s %-15s %-30s";

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private BookReport() {
	}

	/**
	 * Print the book report.
	 * 
	 * @param books
	 *            the books to print.
	 * @param out
	 *            the output stream to print to.
	 */
	public static void write(Map<Long, Book> books, PrintStream out) {

		LOG.debug("Printing the book report");
		println("Book Report", out);
		println(HORIZONTAL_LINE, out);
		String text = String.format(HEADER_FORMAT, "#", "Book ID", "ISBN", "Authors", "Year", "Title", "Rating", "Ratings Count", "Image URL");
		println(text, out);
		println(HORIZONTAL_LINE, out);

		int i = 0;
		for (Book book : books.values()) {
			text = String.format(BOOK_FORMAT, ++i, book.getBookId(), book.getIsbn(), book.getAuthors(), book.getOriginalPublicationYear(),
					book.getOriginalTitle(), book.getAverageRating(), book.getRatingsCount(), book.getImageURL());
			println(text, out);
		}
	}

	private static void println(String text, PrintStream out) {
		out.println(text);
		LOG.info(text);
	}

}

/**
 * Project: A00954556assignment1
 * File: PurchaseReport.java
 * Date: Oct 17, 2019
 * Time: 3:12:50 PM
 */
package a00954556_kelvin_tay.io;

import java.io.PrintStream;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00954556_kelvin_tay.data.Book;
import a00954556_kelvin_tay.data.Customer;
import a00954556_kelvin_tay.data.Purchase;

/**
 * @author Kelvin Tay, A00954556
 *
 */
public class PurchaseReport {

	public static final String HORIZONTAL_LINE = "----------------------------------------------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%-14s %-20s %-100s %-8s";
	public static final String PURCHASE_FORMAT = "%08d       %-20s %-100s $%-8.2f";

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private PurchaseReport() {
	}

	/**
	 * Print the purchase report.
	 * 
	 * @param purchases
	 *            the purchases to print.
	 * @param out
	 *            the output stream to print to.
	 */
	public static void write(Map<Long, Purchase> purchases, Map<Long, Customer> customers, Map<Long, Book> books, boolean totalOptionSet,
			PrintStream out) {

		LOG.debug("Printing purchase report");
		println("Purchase Report", out);
		println(HORIZONTAL_LINE, out);
		String text = String.format(HEADER_FORMAT, "Purchase ID", "Customer", "Book Title", "Price");
		println(text, out);
		println(HORIZONTAL_LINE, out);
		double sum = 0;

		for (Purchase purchase : purchases.values()) {

			long purchaseID = purchase.getPurchaseId();
			String fullName = customers.get(purchase.getCustomerId()).getFirstName() + " " + customers.get(purchase.getCustomerId()).getLastName();
			String title = books.get(purchase.getBookId()).getOriginalTitle();

			text = String.format(PURCHASE_FORMAT, purchaseID, fullName, title, purchase.getPrice());
			println(text, out);

			sum += purchase.getPrice();
		}

		if (totalOptionSet) {
			LOG.debug("Printing purchase total");
			println("Total value of purchases: $" + String.format("%.2f", sum), out);
		}

	}

	private static void println(String text, PrintStream out) {
		out.println(text);
		LOG.info(text);
	}

}

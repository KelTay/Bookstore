package a00954556_kelvin_tay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import a00954556_kelvin_tay.data.Book;
import a00954556_kelvin_tay.data.Customer;
import a00954556_kelvin_tay.data.Purchase;
import a00954556_kelvin_tay.io.BookReader;
import a00954556_kelvin_tay.io.BookReport;
import a00954556_kelvin_tay.io.CustomerReader;
import a00954556_kelvin_tay.io.CustomerReport;
import a00954556_kelvin_tay.io.PurchaseReader;
import a00954556_kelvin_tay.io.PurchaseReport;
import a00954556_kelvin_tay.util.BookOptions;
import a00954556_kelvin_tay.util.BookSorters;
import a00954556_kelvin_tay.util.CustomerSorters;
import a00954556_kelvin_tay.util.PurchaseSorters;

/**
 * Project: Book
 * File: BookStore.java
 * Date: October, 2017
 * Time: 1:22:25 PM
 */

/**
 * @author Sam Cirka, A00123456
 * @author Kelvin Tay, A00954556
 */
public class BookStore {

	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	private static final String CUSTOMER_DATA_FILENAME = "customers.dat";
	private static final String BOOK_DATA_FILENAME = "books500.csv";
	private static final String PURCHASE_DATA_FILENAME = "purchases.csv";
	private static final String CUSTOMER_REPORT_FILENAME = "customers_report.txt";
	private static final String BOOK_REPORT_FILENAME = "book_report.txt";
	private static final String PURCHASE_REPORT_FILENAME = "purchases_report.txt";
	private Map<Long, Customer> customers;
	private Map<Long, Book> books;
	private Map<Long, Purchase> purchases;

	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Bcmc Constructor. Processes the commandline arguments
	 * ex. -inventory -make=honda -by_count -desc -total -service
	 * 
	 * @throws ApplicationException
	 * @throws ParseException
	 */
	public BookStore(String[] args, File customerDataFile, File bookDataFile, File purchaseDataFile) throws ApplicationException, ParseException {
		LOG.info("Create BookStore");

		BookOptions.process(args);
		customers = CustomerReader.read(customerDataFile);
		books = BookReader.read(bookDataFile);
		purchases = PurchaseReader.read(purchaseDataFile);
	}

	/**
	 * Entry point to GIS
	 * 
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) {
		Instant startTime = Instant.now();
		LOG.info(startTime);

		File customerDataFile = new File(CUSTOMER_DATA_FILENAME);
		File bookDataFile = new File(BOOK_DATA_FILENAME);
		File purchaseDataFile = new File(PURCHASE_DATA_FILENAME);

		// start the Book System
		try {
			BookStore book = new BookStore(args, customerDataFile, bookDataFile, purchaseDataFile);
			if (BookOptions.isHelpOptionSet()) {
				BookOptions.Value[] values = BookOptions.Value.values();
				System.out.format("%-5s %-15s %-10s %s%n", "Option", "Long Option", "Has Value", "Description");
				for (BookOptions.Value value : values) {
					System.out.format("-%-5s %-15s %-10s %s%n", value.getOption(), ("-" + value.getLongOption()), value.isHasArg(),
							value.getDescription());
				}

				return;
			}

			book.run();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}

		Instant endTime = Instant.now();
		LOG.info(endTime);
		LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}

	/**
	 * Configures log4j2 from the external configuration file specified in LOG4J_CONFIG_FILENAME.
	 * If the configuration file isn't found then log4j2's DefaultConfiguration is used.
	 */
	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println(String.format("WARNING! Can't find the log4j logging configuration file %s; using DefaultConfiguration for logging.",
					LOG4J_CONFIG_FILENAME));
			Configurator.initialize(new DefaultConfiguration());
		}
	}

	/**
	 * @throws ApplicationException
	 * @throws FileNotFoundException
	 * 
	 */
	private void run() throws ApplicationException, FileNotFoundException {
		LOG.debug("run()");

		generateReports();
	}

	/**
	 * Generate the reports from the input data
	 * 
	 * @throws FileNotFoundException
	 */
	private void generateReports() throws FileNotFoundException {
		LOG.debug("generating the reports");

		// Generate customer report
		if (BookOptions.isCustomersOptionSet()) {
			LOG.debug("generating the customer report");

			// for program args: -c -J -d
			LOG.info("Show Customer Report: " + BookOptions.isCustomersOptionSet());
			LOG.info("Sort Customers by Join Date: " + BookOptions.isByJoinDateOptionSet());
			LOG.info("Customer Join Date DESCENDING: " + BookOptions.isDescendingOptionSet());

			File customerReportFile = new File(CUSTOMER_REPORT_FILENAME);

			// Check if sort by join date is true
			if (BookOptions.isByJoinDateOptionSet()) {
				LOG.debug("Sort customers by join date in ascending order");

				// Sort by join date
				List<Customer> custs = new ArrayList<>(customers.values());
				Collections.sort(custs, new CustomerSorters.CompareByJoinedDate());

				// Create new LinkedHashMap of customers, sorted by join date
				Map<Long, Customer> sortedCustomers = new LinkedHashMap<>();

				// Check if sort in descending order is true
				if (BookOptions.isDescendingOptionSet()) {
					LOG.debug("Sort customers in descending order");

					for (int i = custs.size() - 1; i >= 0; --i) {
						sortedCustomers.put(custs.get(i).getId(), custs.get(i));
					}
				} else {
					for (Customer customer : custs) {
						sortedCustomers.put(customer.getId(), customer);
					}
				}

				try {
					PrintStream out = new PrintStream(new FileOutputStream(customerReportFile));
					CustomerReport.write(sortedCustomers, out);
				} catch (FileNotFoundException e) {
					LOG.error(e.getMessage());
				}

			} else {
				try {
					PrintStream out = new PrintStream(new FileOutputStream(customerReportFile));
					CustomerReport.write(customers, out);
				} catch (FileNotFoundException e) {
					LOG.error(e.getMessage());
				}
			}

		}

		// Generate book report
		if (BookOptions.isBooksOptionSet()) {
			LOG.debug("generating the book report");

			// for program args: -b -A -d
			LOG.info("Show Book Report: " + BookOptions.isBooksOptionSet());
			LOG.info("Sort Books by Authors: " + BookOptions.isByAuthorOptionSet());
			LOG.info("Book Authors DESCENDING: " + BookOptions.isDescendingOptionSet());

			File bookReportFile = new File(BOOK_REPORT_FILENAME);

			// Check if sort by author is true
			if (BookOptions.isByAuthorOptionSet()) {
				LOG.debug("Sort books by author in ascending order");

				// Sort by author
				List<Book> booksList = new ArrayList<>(books.values());
				Collections.sort(booksList, new BookSorters.CompareByAuthor());

				// Create new LinkedHashMap of books, sorted by author
				Map<Long, Book> sortedBooks = new LinkedHashMap<>();

				// Check if sort in descending order is true
				if (BookOptions.isDescendingOptionSet()) {
					LOG.debug("Sort books in descending order");

					for (int i = booksList.size() - 1; i >= 0; --i) {
						sortedBooks.put(booksList.get(i).getBookId(), booksList.get(i));
					}
				} else {
					for (Book book : booksList) {
						sortedBooks.put(book.getBookId(), book);
					}
				}

				try {
					PrintStream out = new PrintStream(new FileOutputStream(bookReportFile));
					BookReport.write(sortedBooks, out);
				} catch (FileNotFoundException e) {
					LOG.error(e.getMessage());
				}

			} else {

				try {
					PrintStream out = new PrintStream(new FileOutputStream(bookReportFile));
					BookReport.write(books, out);
				} catch (FileNotFoundException e) {
					LOG.error(e.getMessage());
				}
			}
		}

		// Generate purchase report
		if (BookOptions.isPurchasesOptionSet()) {
			LOG.debug("generating the inventory report");

			// for program args: -p -t -L -T -C -d
			LOG.info("Show Purchase Report: " + BookOptions.isPurchasesOptionSet());
			LOG.info("Show Total Value: " + BookOptions.isTotalOptionSet());
			LOG.info("Sort Purchases by Customer Last Name: " + BookOptions.isByLastnameOptionSet());
			LOG.info("Sort Purchases by Book Title: " + BookOptions.isByTitleOptionSet());

			if (BookOptions.isByLastnameOptionSet()) {
				System.out.println("Purchases by Last Name DESCENDING: " + BookOptions.isDescendingOptionSet());
			}

			if (BookOptions.isByTitleOptionSet()) {
				System.out.println("Purchases by Book Title DESCENDING: " + BookOptions.isDescendingOptionSet());
			}

			File purchaseReportFile = new File(PURCHASE_REPORT_FILENAME);
			List<Purchase> purchasesList = new ArrayList<>(purchases.values()); // All purchase items
			List<Purchase> selectedPurchases; // List of purchase items that user wants to print out
			Map<Long, Purchase> purchasesToPrint = new LinkedHashMap<>(); // Map of purchase items to print out

			// Filter by customer ID if provided
			if (BookOptions.getCustomerId() != null) {
				LOG.debug("Filtering by customer ID");
				long customerID = Long.parseLong(BookOptions.getCustomerId());
				selectedPurchases = new ArrayList<>();
				long id;

				for (Purchase purchase : purchasesList) {
					id = purchase.getCustomerId();

					if (id == customerID) {
						selectedPurchases.add(purchase); // Only want to select purchases with matching customer ID
					}
				}

			} else {
				LOG.debug("Selecting all purchases for printing");
				selectedPurchases = purchasesList; // Select all purchases
			}

			// Make the maps of customers and books available to the PurchaseSorters class, so that we can sort
			// by book title and customer last name.
			PurchaseSorters.setCustomersAndBooks(customers, books);

			// Check if sort by last name is true
			if (BookOptions.isByLastnameOptionSet()) {
				LOG.debug("Sort purchases by customer's last name in ascending order");

				// Sort by customer's last name
				Collections.sort(selectedPurchases, new PurchaseSorters.CompareByLastName());
			}

			// Check if sort by title is true
			if (BookOptions.isByTitleOptionSet()) {
				LOG.debug("Sort purchases by book title in ascending order");

				// Sort by book title
				Collections.sort(selectedPurchases, new PurchaseSorters.CompareByTitle());
			}

			// Check if sort in descending order is true
			if (BookOptions.isDescendingOptionSet()) {
				LOG.debug("Sort purchases in descending order");

				for (int i = selectedPurchases.size() - 1; i >= 0; --i) {
					purchasesToPrint.put(selectedPurchases.get(i).getPurchaseId(), selectedPurchases.get(i));
				}
			} else {
				for (Purchase purchase : selectedPurchases) {
					purchasesToPrint.put(purchase.getPurchaseId(), purchase);
				}
			}

			try {
				PrintStream out = new PrintStream(new FileOutputStream(purchaseReportFile));
				PurchaseReport.write(purchasesToPrint, customers, books, BookOptions.isTotalOptionSet(), out);

			} catch (FileNotFoundException e) {
				LOG.error(e.getMessage());
			}

		}
	}

}

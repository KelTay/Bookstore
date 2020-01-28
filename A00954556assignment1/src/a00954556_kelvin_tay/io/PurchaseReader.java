/**
 * Project: A00954556assignment1
 * File: PurchaseReader.java
 * Date: Oct 17, 2019
 * Time: 11:51:45 AM
 */
package a00954556_kelvin_tay.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00954556_kelvin_tay.ApplicationException;
import a00954556_kelvin_tay.data.Purchase;

/**
 * @author Kelvin Tay, A00954556
 *
 */
public class PurchaseReader {

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private PurchaseReader() {
	}

	/**
	 * Reads in purchase input
	 * 
	 * @param bookDataFile
	 *            the input data.
	 * @return a Map of purchases.
	 * @throws ApplicationException
	 *             if invalid data encountered.
	 */
	public static Map<Long, Purchase> read(File purchaseDataFile) throws ApplicationException {
		File file = purchaseDataFile;
		FileReader in;
		Iterable<CSVRecord> records;

		try {
			in = new FileReader(file);
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new ApplicationException(e);
		}

		Map<Long, Purchase> purchases = new HashMap<>();

		LOG.debug("Reading " + file.getAbsolutePath());
		for (CSVRecord record : records) {
			long purchaseId = Long.parseLong(record.get("id"));
			long customerId = Long.parseLong(record.get("customer_id"));
			long bookId = Long.parseLong(record.get("book_id"));
			double price = Double.parseDouble(record.get("price"));

			Purchase purchase = new Purchase(purchaseId, customerId, bookId, price);

			purchases.put(purchase.getPurchaseId(), purchase);
		}
		LOG.debug("Finished reading " + file.getAbsolutePath());

		return purchases;
	}
}

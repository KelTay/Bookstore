/**
 * Project: A00954556assignment1
 * File: BookReader.java
 * Date: Oct 16, 2019
 * Time: 12:44:54 PM
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
import a00954556_kelvin_tay.data.Book;

/**
 * Reads Book input from a file.
 * 
 * @author Kelvin Tay, A00954556
 *
 */
public class BookReader {

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private BookReader() {
	}

	/**
	 * Read book input data.
	 * 
	 * @param bookDataFile
	 *            the input data.
	 * @return A Map of Books.
	 * @throws ApplicationException
	 *             if invalid data encountered.
	 */
	public static Map<Long, Book> read(File bookDataFile) throws ApplicationException {
		File file = bookDataFile;
		FileReader in;
		Iterable<CSVRecord> records;

		try {
			in = new FileReader(file);
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new ApplicationException(e);
		}

		Map<Long, Book> books = new HashMap<>();

		LOG.debug("Reading " + file.getAbsolutePath());
		for (CSVRecord record : records) {
			long bookId = Long.parseLong(record.get("book_id"));
			String isbn = record.get("isbn");
			String authors = record.get("authors");
			String originalPublicationYear = record.get("original_publication_year");
			String originalTitle = record.get("original_title");
			String averageRating = record.get("average_rating");
			String ratingsCount = record.get("ratings_count");
			String imageURL = record.get("image_url");

			Book book = new Book.Builder(bookId, isbn).setAuthors(authors).setOriginalPublicationYear(originalPublicationYear)
					.setOriginalTitle(originalTitle).setAverageRating(averageRating).setRatingsCount(ratingsCount).setImageURL(imageURL).build();

			books.put(book.getBookId(), book);
		}
		LOG.debug("Finished reading " + file.getAbsolutePath());

		return books;
	}

}

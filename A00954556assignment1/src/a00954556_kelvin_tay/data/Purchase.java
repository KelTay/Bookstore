/**
 * Project: A00954556assignment1
 * File: Purchase.java
 * Date: Oct 12, 2019
 * Time: 4:35:09 PM
 */
package a00954556_kelvin_tay.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00954556_kelvin_tay.util.Validator;

/**
 * Represents a book purchase for a customer.
 * 
 * @author Kelvin Tay, A00954556
 * @version 1.0
 */
public class Purchase {
	private long purchaseId;
	private long customerId;
	private long bookId;
	private double price;

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructor
	 * 
	 * @param purchaseId
	 *            the purchase id
	 * @param customerId
	 *            the customer id
	 * @param bookId
	 *            the book id
	 * @param price
	 *            the book price, must be greater than or equal to zero.
	 */
	public Purchase(long purchaseId, long customerId, long bookId, double price) {
		this.purchaseId = purchaseId;
		this.customerId = customerId;
		this.bookId = bookId;

		if (Validator.validatePrice(price)) {
			this.price = price;
		} else {
			LOG.error("Price is negative.");
		}
	}

	/**
	 * @param purchaseId
	 *            the purchaseId to set
	 */
	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @param bookId
	 *            the bookId to set
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @param price
	 *            the price to set, must not be negative
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the purchaseId
	 */
	public long getPurchaseId() {
		return purchaseId;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @return the bookId
	 */
	public long getBookId() {
		return bookId;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", customerId=" + customerId + ", bookId=" + bookId + ", price=" + price + "]";
	}

}

/**
 * Project: A00954556assignment1
 * File: Book.java
 * Date: Oct 12, 2019
 * Time: 4:18:53 PM
 */
package a00954556_kelvin_tay.data;

/**
 * Represents a Book.
 * 
 * @author Kelvin Tay, A00954556
 *
 */
public class Book {
	private long bookId;
	private String isbn;
	private String authors;
	private String originalPublicationYear;
	private String originalTitle;
	private String averageRating;
	private String ratingsCount;
	private String imageURL;

	/**
	 * Inner Builder class uses the Builder design pattern
	 * 
	 * @author Kelvin Tay, A00954556
	 *
	 */
	public static class Builder {
		// Required parameters
		private final long bookId;
		private final String isbn;

		// Optional parameters
		private String authors;
		private String originalPublicationYear;
		private String originalTitle;
		private String averageRating;
		private String ratingsCount;
		private String imageURL;

		/**
		 * Builder Constructor
		 * 
		 * @param bookId
		 *            the book's id
		 * @param isbn
		 *            the book's isbn
		 */
		public Builder(long bookId, String isbn) {
			this.bookId = bookId;
			this.isbn = isbn;
		}

		/**
		 * @param authors
		 *            the authors to set
		 */
		public Builder setAuthors(String authors) {
			this.authors = authors;
			return this;
		}

		/**
		 * @param originalPublicationYear
		 *            the original publication year to set
		 */
		public Builder setOriginalPublicationYear(String originalPublicationYear) {
			this.originalPublicationYear = originalPublicationYear;
			return this;
		}

		/**
		 * @param originalTitle
		 *            the original title to set
		 */
		public Builder setOriginalTitle(String originalTitle) {
			this.originalTitle = originalTitle;
			return this;
		}

		/**
		 * @param averageRating
		 *            the average rating to set
		 */
		public Builder setAverageRating(String averageRating) {
			this.averageRating = averageRating;
			return this;
		}

		/**
		 * @param ratingsCount
		 *            the ratings count to set
		 */
		public Builder setRatingsCount(String ratingsCount) {
			this.ratingsCount = ratingsCount;
			return this;
		}

		/**
		 * @param imageURL
		 *            the image URL to set
		 */
		public Builder setImageURL(String imageURL) {
			this.imageURL = imageURL;
			return this;
		}

		public Book build() {
			return new Book(this);
		}
	}

	/*
	 * Private Book Constructor
	 */
	private Book(Builder builder) {
		bookId = builder.bookId;
		isbn = builder.isbn;
		authors = builder.authors;
		originalPublicationYear = builder.originalPublicationYear;
		originalTitle = builder.originalTitle;
		averageRating = builder.averageRating;
		ratingsCount = builder.ratingsCount;
		imageURL = builder.imageURL;
	}

	/**
	 * @param bookId
	 *            the bookId to set
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @param authors
	 *            the authors to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * @param originalPublicationYear
	 *            the originalPublicationYear to set
	 */
	public void setOriginalPublicationYear(String originalPublicationYear) {
		this.originalPublicationYear = originalPublicationYear;
	}

	/**
	 * @param originalTitle
	 *            the originalTitle to set
	 */
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	/**
	 * @param averageRating
	 *            the averageRating to set
	 */
	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}

	/**
	 * @param ratingsCount
	 *            the ratingsCount to set
	 */
	public void setRatingsCount(String ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	/**
	 * @param imageURL
	 *            the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * @return the bookId
	 */
	public long getBookId() {
		return bookId;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @return the authors
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * @return the originalPublicationYear
	 */
	public String getOriginalPublicationYear() {
		return originalPublicationYear;
	}

	/**
	 * @return the originalTitle
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}

	/**
	 * @return the averageRating
	 */
	public String getAverageRating() {
		return averageRating;
	}

	/**
	 * @return the ratingsCount
	 */
	public String getRatingsCount() {
		return ratingsCount;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", isbn=" + isbn + ", authors=" + authors + ", originalPublicationYear=" + originalPublicationYear
				+ ", originalTitle=" + originalTitle + ", averageRating=" + averageRating + ", ratingsCount=" + ratingsCount + ", imageURL="
				+ imageURL + "]";
	}

}

package com.epam.lab.news.exception;

/**
 * The Class NoSuchEntityException.
 */
public class NoSuchEntityException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7700132352741741604L;

	/**
	 * Instantiates a new no such entity exception.
	 */
	public NoSuchEntityException() {
		super();
	}

	/**
	 * Instantiates a new no such entity exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public NoSuchEntityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new no such entity exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public NoSuchEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new no such entity exception.
	 *
	 * @param message the message
	 */
	public NoSuchEntityException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new no such entity exception.
	 *
	 * @param cause the cause
	 */
	public NoSuchEntityException(Throwable cause) {
		super(cause);
	}

}

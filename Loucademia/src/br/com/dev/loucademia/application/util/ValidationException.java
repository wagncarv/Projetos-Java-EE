package br.com.dev.loucademia.application.util;

import javax.ejb.ApplicationException;

/**
 * 
 * @author Wagner Carvalho
 * Exception class
 *
 */
@ApplicationException
public class ValidationException extends RuntimeException {

	public ValidationException() {

	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);

	}

	public ValidationException(String message) {
		super(message);

	}

	public ValidationException(Throwable cause) {
		super(cause);

	}

}

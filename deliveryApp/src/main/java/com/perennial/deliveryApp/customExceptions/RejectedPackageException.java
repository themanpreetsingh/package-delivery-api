package com.perennial.deliveryApp.customExceptions;

public class RejectedPackageException extends RuntimeException {

	private static final long serialVersionUID = 7696059589959623048L;

	public RejectedPackageException(String message) {
        super(message);
    }

    public RejectedPackageException(String message, Throwable cause) {
        super(message, cause);
    }
}

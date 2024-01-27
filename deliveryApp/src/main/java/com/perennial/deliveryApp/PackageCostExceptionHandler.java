package com.perennial.deliveryApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.perennial.deliveryApp.customExceptions.RejectedPackageException;

@ControllerAdvice
public class PackageCostExceptionHandler {
	@ExceptionHandler(RejectedPackageException.class)
    public ResponseEntity<String> handleRejectedParcelException(RejectedPackageException ex) {
        // Handle rejected package exception
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // Handle other generic exceptions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    }
}

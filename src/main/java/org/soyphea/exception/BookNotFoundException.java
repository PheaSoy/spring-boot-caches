package org.soyphea.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}

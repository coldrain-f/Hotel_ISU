package edu.coldrain.hotel_isu.exception;

public class DuplicateUserException extends Exception {

    public DuplicateUserException() {
        super();
    }

    public DuplicateUserException(String message) {
        super(message);
    }
}

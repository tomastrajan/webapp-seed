package com.trajan.seed.webapp.rest.exception;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-06
 */
public class OperationOnNonexistentResourceException extends Exception {

    public OperationOnNonexistentResourceException() {
        super("Cannot perform operation on nonexistent resource");
    }
}

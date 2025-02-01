package com.kebapi.kebapiapi.exception;

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException() {
        super("This customer does not exist");
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}

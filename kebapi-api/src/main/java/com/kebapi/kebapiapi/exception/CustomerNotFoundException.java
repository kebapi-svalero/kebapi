package com.kebapi.kebapiapi.exception;

public class CustomerNotFoundException extends Exception {

  public CustomerNotFoundException() {
    super("This customer does not exist");
  }

  public CustomerNotFoundException(String message) {
    super(message);
  }
}

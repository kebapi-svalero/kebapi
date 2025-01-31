package org.kebapi.kebapi.exception;

public class CustomerNotFoundException extends Exception {

  public CustomerNotFoundException() {
    super("This customer does not exist");
  }

  public CustomerNotFoundException(String message) {
    super(message);
  }
}

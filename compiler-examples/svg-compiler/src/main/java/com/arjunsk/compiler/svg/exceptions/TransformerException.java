package com.arjunsk.compiler.svg.exceptions;

public class TransformerException extends RuntimeException {

  public TransformerException() {
    super("Exception While Transforming");
  }

  public TransformerException(String message) {
    super(message);
  }

  public TransformerException(String message, Throwable cause) {
    super(message, cause);
  }
}

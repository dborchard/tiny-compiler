package com.arjunsk.compiler.ck.exceptions;

public class SemanticException extends RuntimeException {

  public SemanticException() {
    super("Exception While performing semantic checks.");
  }

  public SemanticException(String message) {
    super(message);
  }

  public SemanticException(String message, Throwable cause) {
    super(message, cause);
  }
}

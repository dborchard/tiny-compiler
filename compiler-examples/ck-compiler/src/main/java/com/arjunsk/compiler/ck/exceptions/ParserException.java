package com.arjunsk.compiler.ck.exceptions;

public class ParserException extends RuntimeException {

  public ParserException() {
    super("Exception While Parsing");
  }

  public ParserException(String message) {
    super(message);
  }

  public ParserException(String message, Throwable cause) {
    super(message, cause);
  }
}

package com.arjunsk.compiler.svg.exceptions;

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

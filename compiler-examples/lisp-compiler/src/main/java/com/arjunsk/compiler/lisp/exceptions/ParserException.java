package com.arjunsk.compiler.lisp.exceptions;

public class ParserException extends RuntimeException {

  public ParserException() {
    super("Exception in Parsing");
  }

  public ParserException(String message) {
    super(message);
  }

  public ParserException(String message, Throwable cause) {
    super(message, cause);
  }
}

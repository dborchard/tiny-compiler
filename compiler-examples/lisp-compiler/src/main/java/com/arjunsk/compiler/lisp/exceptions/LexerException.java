package com.arjunsk.compiler.lisp.exceptions;

public class LexerException extends RuntimeException {

  public LexerException() {
    super("Exception in Lexer");
  }

  public LexerException(String message) {
    super(message);
  }

  public LexerException(String message, Throwable cause) {
    super(message, cause);
  }
}

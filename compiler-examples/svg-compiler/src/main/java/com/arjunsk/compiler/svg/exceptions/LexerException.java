package com.arjunsk.compiler.svg.exceptions;

public class LexerException extends RuntimeException {

  public LexerException() {
    super("Exception While Lexical Tokenizing");
  }

  public LexerException(String message, Throwable cause) {
    super(message, cause);
  }
}

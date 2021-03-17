package com.arjunsk.compiler.lisp.domain.lexer;

import com.arjunsk.compiler.lisp.domain.lexer.support.TokenType;

public class Token {

  private final TokenType type;

  private final String value;

  public Token(TokenType type, String value) {
    this.type = type;
    this.value = value;
  }

  public TokenType getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return this.type + " " + this.value;
  }
}

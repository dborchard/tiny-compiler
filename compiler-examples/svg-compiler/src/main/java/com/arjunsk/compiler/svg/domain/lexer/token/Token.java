package com.arjunsk.compiler.svg.domain.lexer.token;

import com.arjunsk.compiler.svg.domain.lexer.token.support.TokenType;

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
}

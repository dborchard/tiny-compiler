package com.arjunsk.compiler.ck.domain.token;

import com.arjunsk.compiler.ck.domain.token.support.TokenType;

/** Output of Lexer. Contains TokenType and Token Value. */
public class Token {

  private final TokenType type;

  private final String value;

  public Token(TokenType type) {
    this.type = type;
    this.value = null;
  }

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

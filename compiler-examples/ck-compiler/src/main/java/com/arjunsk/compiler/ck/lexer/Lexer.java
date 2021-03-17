package com.arjunsk.compiler.ck.lexer;

import com.arjunsk.compiler.ck.domain.token.Token;
import com.arjunsk.compiler.ck.domain.token.support.TokenType;
import com.arjunsk.compiler.ck.exceptions.LexerException;
import java.util.Arrays;

public class Lexer {

  private final String code;
  private final int codeLength;

  private int currentIndex;

  private Token currentToken;
  private Token previousToken;

  public Lexer(String code) {
    this.code = code;
    this.currentIndex = 0;
    this.codeLength = code.length();
  }

  public boolean nextToken() {

    while (!isEndOfCode()) { // while loop is to fetch nextToken, if a skipWS occurs.

      previousToken = currentToken; // in case you need the previous token

      final char currentChar = code.charAt(currentIndex);

      if (Arrays.asList(' ', '\r', '\t', '\n').contains(currentChar)) { // 1. WS
        skipWhiteSpace();
        continue;
      } else if (currentChar == '=') { // 2. LET
        currentToken = new Token(TokenType.EQUALS_OPERATOR);
        currentIndex++;
      } else if (Character.isDigit(currentChar)) { // 3. INT
        currentToken = new Token(TokenType.NUMBER, readNumber());
      } else if (Character.isLetter(currentChar)) {
        String variableName = readVariable();
        if (variableName.equalsIgnoreCase("show")) { // 4. SHOW
          currentToken = new Token(TokenType.SHOW);
        } else { // 5. VAR
          currentToken = new Token(TokenType.VARIABLE, variableName);
        }
      } else {
        throw new LexerException("Token not defined.");
      }
      return true;
    }
    return false;
  }

  /**
   * Read Integer as String
   *
   * @return String value of Integer Number.
   */
  private String readNumber() {
    StringBuilder sb = new StringBuilder();
    char currentChar = code.charAt(currentIndex);
    while (!isEndOfCode() && Character.isDigit(currentChar)) {
      sb.append(currentChar);
      currentIndex++;
      if (isEndOfCode()) break;
      currentChar = code.charAt(currentIndex);
    }
    return sb.toString();
  }

  /** @return String read from current index. */
  private String readVariable() {
    StringBuilder sb = new StringBuilder();
    char currentChar = code.charAt(currentIndex);
    while (!isEndOfCode() && Character.isLetter(currentChar)) {
      sb.append(currentChar);
      currentIndex++;
      if (isEndOfCode()) break;
      currentChar = code.charAt(currentIndex);
    }
    return sb.toString();
  }

  /** Skip WhiteSpace(WS) */
  private void skipWhiteSpace() {
    while (!isEndOfCode()) {
      if (Arrays.asList(' ', '\r', '\t', '\n').contains(code.charAt(currentIndex))) {
        currentIndex++;
      } else {
        break;
      }
    }
  }

  /** Check if End of Code is reached. */
  private boolean isEndOfCode() {
    return currentIndex >= codeLength;
  }

  /**
   * Get previous Token.
   *
   * <p>NOTE: for SimplerLang grammar we don't have much use of previous token. But it will be
   * useful when implementing complex Grammar.
   */
  public Token getPreviousToken() {
    return previousToken;
  }

  /** Get current Token. */
  public Token getCurrentToken() {
    return currentToken;
  }
}
